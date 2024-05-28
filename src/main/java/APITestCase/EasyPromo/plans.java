package APITestCase.EasyPromo;

import APITestCase.FacadePromo.authenticationToken;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import xProject.flowFunction;
import xProject.httpData;

import java.util.HashMap;

import static com.alibaba.fastjson.JSON.parseObject;

public class plans {
    public String rhKey = "common";
    private String planHeaderConfigPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\RequestHeader.json";
    private String rType = "post";
    private String sPath = "/api/v4/quotation/plans";
    private String plansRequestBodyKey = "Plans_TestBody_";
    private String ReqBodyPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\PlanRequestBody.json";
    public String planIndex;


    public httpResponse plans(String ENV, String easyPromoHost, String bodyNumber,String personPhone) {
        authenticationToken authenticationToken = new authenticationToken();
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, planHeaderConfigPath);
        String token = null;
        httpResponse tokenResponse = authenticationToken.getAuthenticationToken(ENV,personPhone);
        token = tokenResponse.getBody(JSONObject.class).getString("token_type") + tokenResponse.getBody(JSONObject.class).getString("user_token");
        String key = "Authorization";
        String value = token;
        requestHeader.Add(key, value);
        String URL = easyPromoHost + sPath;
        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(plansRequestBodyKey+bodyNumber);
        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }
  

    public boolean promotionPlansValidation(JSONArray promotionPlansList, String expStockID, JSONObject expPromotionPlans) {
        int cnt = 0;
        int itemCnt = 0;

        for (Object promotionPlans : promotionPlansList) {
            JSONObject couponPlans = (JSONObject) ((JSONObject) promotionPlans).getJSONArray("coupon_plans").get(0);
            String actStockID = couponPlans.getString("stock_id");
            if (actStockID.equals(expStockID)) {

                String actualSubtotalAmount = ((JSONObject) promotionPlans).getString("subtotal_amount");
                String actualTotalDiscountAmount = ((JSONObject) promotionPlans).getString("total_discount_amount");
                String actualCouponDiscountAmount = ((JSONObject) promotionPlans).getString("coupon_discount_amount");
                String actualProductDiscountAmount = ((JSONObject) promotionPlans).getString("product_discount_amount");
                String actualShippingFee = ((JSONObject) promotionPlans).getString("shipping_fee");
                String actualActualAmount = ((JSONObject) promotionPlans).getString("actual_amount");

                String expectedSubtotalAmount = ((JSONObject) expPromotionPlans).getString("subtotal_amount");
                String expectedTotalDiscountAmount = ((JSONObject) expPromotionPlans).getString("total_discount_amount");
                String expectedCouponDiscountAmount = ((JSONObject) expPromotionPlans).getString("coupon_discount_amount");
                String expectedProductDiscountAmount = ((JSONObject) expPromotionPlans).getString("product_discount_amount");
                String expectedShippingFee = ((JSONObject) expPromotionPlans).getString("shipping_fee");
                String expectedActualAmount = ((JSONObject) expPromotionPlans).getString("actual_amount");

                if (actualSubtotalAmount.equals(expectedSubtotalAmount)
                        && actualTotalDiscountAmount.equals(expectedTotalDiscountAmount)
                        && actualCouponDiscountAmount.equals(expectedCouponDiscountAmount)
                        && actualProductDiscountAmount.equals(expectedProductDiscountAmount)
                        && actualShippingFee == expectedShippingFee
                        && actualActualAmount.equals(expectedActualAmount)
                ) {
                    cnt++;
                    planIndex = ((JSONObject) promotionPlans).getString("plan_index");
                }

                JSONArray itemPlans = ((JSONObject) promotionPlans).getJSONArray("item_plans");
                JSONArray expItemPlans = ((JSONObject) expPromotionPlans).getJSONArray("item_plans");


                for (Object itemPlan : itemPlans) {
                    String id = ((JSONObject) itemPlan).getString("id");
                    for (Object expItemPlan : expItemPlans) {
                        String expID = ((JSONObject) expItemPlan).getString("id");
                        if (expID.equals(id)) {
                            String subtotalAmount = ((JSONObject) itemPlan).getString("subtotal_amount");
                            String totalDiscountAmount = ((JSONObject) itemPlan).getString("total_discount_amount");
                            String couponDiscountAmount = ((JSONObject) itemPlan).getString("coupon_discount_amount");
                            String productDiscountAmount = ((JSONObject) itemPlan).getString("product_discount_amount");
                            String actualAmount = ((JSONObject) itemPlan).getString("actual_amount");

                            String expSubtotalAmount = ((JSONObject) expItemPlan).getString("subtotal_amount");
                            String expTotalDiscountAmount = ((JSONObject) expItemPlan).getString("total_discount_amount");
                            String expCouponDiscountAmount = ((JSONObject) expItemPlan).getString("coupon_discount_amount");
                            String expProductDiscountAmount = ((JSONObject) expItemPlan).getString("product_discount_amount");
                            String expActualAmount = ((JSONObject) expItemPlan).getString("actual_amount");

                            if (subtotalAmount.equals(expSubtotalAmount)
                                    && totalDiscountAmount.equals(expTotalDiscountAmount)
                                    && couponDiscountAmount.equals(expCouponDiscountAmount)
                                    && productDiscountAmount.equals(expProductDiscountAmount)
                                    && actualAmount.equals(expActualAmount)
                            ) itemCnt++;
                        }

                    }


                }


            }
        }
        if (cnt == 1 && itemCnt == 2) {
            return true;
        } else return false;
    }

    public boolean couponsValidation(JSONArray couponsList, HashMap<String, String> activityInfo) {
        int cnt = 0;
        String expCouponType = null;
        for (Object coupons : couponsList) {
            String actStockID = ((JSONObject) coupons).getString("stock_id");
            if (actStockID.equals(activityInfo.get("StockID"))) {
                String actStockName = ((JSONObject) coupons).getString("stock_name");
                String actCouponType = ((JSONObject) coupons).getString("coupon_type");
                String actScope = ((JSONObject) coupons).getString("scope");
                String actAmount = ((JSONObject) coupons).getJSONObject("coupon_value").getString("amount");
                String expAmount = activityInfo.get("Discount Price").substring(0,activityInfo.get("Discount Price").length()-2);
                String actTransactionMinimum = ((JSONObject) coupons).getJSONObject("coupon_condition").getString("transaction_minimum");
                String expTransactionMinimum =activityInfo.get("Consumption Threshold").substring(0,activityInfo.get("Consumption Threshold").length()-2);
                if (activityInfo.get("Applicable Goods Type").equals("单品券")) {
                    expCouponType = "PRODUCT";
                }
                if (activityInfo.get("Applicable Goods Type").equals("全场券")) {
                    expCouponType = "ALL";
                }
                if (actStockName.equals(activityInfo.get("Activity Name"))
                        && actCouponType.equals("MERCHANT")
                        && actScope.equals(expCouponType)
                        && actAmount.contains(expAmount)
                        && actTransactionMinimum.contains(expTransactionMinimum)
                ) cnt++;
            }
        }
        if (cnt == 1) return true;
        else return false;
    }
}
