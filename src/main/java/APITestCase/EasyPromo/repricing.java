package APITestCase.EasyPromo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;

import static com.alibaba.fastjson.JSON.parseObject;

public class repricing {
    public String rhKey = "common";
    private String repricingHeaderConfigPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\RequestHeader.json";
    private String rType = "post";
    private String sPath = "/api/v4/quotation/repricing";
    private String repricingRequestBodyKey = "Repricing_TestBody_";
    private String ReqBodyPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\RepricingRequestBody.json";


    public httpResponse repricing(String easyPromoHost, String planIndex, String bodyNumber) {
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, repricingHeaderConfigPath);

        String URL = easyPromoHost + sPath;
        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(repricingRequestBodyKey + bodyNumber);
        //        "plan_index":"13125-13127-13133",
        requestBody.put("plan_index", planIndex);
        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }

    public boolean promotionDetailValidation(JSONObject promotionDetail, JSONObject expPromotionDetail, String expCode, String expStockID) {
        int cnt = 0;
        int itemCnt = 0;
        int couponCnt = 0;
        String actSubtotalAmount = promotionDetail.getString("subtotal_amount");
        String actTotalDiscountAmount = promotionDetail.getString("total_discount_amount");
        String actCouponDiscountAmount = promotionDetail.getString("coupon_discount_amount");
        String actProductDiscountAmount = promotionDetail.getString("product_discount_amount");
        String actShippingFee = promotionDetail.getString("shipping_fee");
        String actActualAmount = promotionDetail.getString("actual_amount");

        String expSubtotalAmount = expPromotionDetail.getString("subtotal_amount");
        String expTotalDiscountAmount = expPromotionDetail.getString("total_discount_amount");
        String expCouponDiscountAmount = expPromotionDetail.getString("coupon_discount_amount");
        String expProductDiscountAmount = expPromotionDetail.getString("product_discount_amount");
        String expShippingFee = expPromotionDetail.getString("shipping_fee");
        String expActualAmount = expPromotionDetail.getString("actual_amount");
        if (
                actSubtotalAmount.equals(expSubtotalAmount)
                        && actTotalDiscountAmount.equals(expTotalDiscountAmount)
                        && actCouponDiscountAmount.equals(expCouponDiscountAmount)
                        && actProductDiscountAmount.equals(expProductDiscountAmount)
                        && actShippingFee == expShippingFee
                        && actActualAmount.equals(expActualAmount)
        ) {
            cnt++;
        }

        JSONArray actPDItems = promotionDetail.getJSONArray("items");
        JSONArray expPDItems = expPromotionDetail.getJSONArray("items");
        for (Object actPDItem : actPDItems) {
            String actID = ((JSONObject) actPDItem).getString("id");
            for (Object expPDItem : expPDItems) {
                String expID = ((JSONObject) expPDItem).getString("id");
                if (actID.equals(expID)) {
                    String actSubTotalAmountItem = ((JSONObject) actPDItem).getString("subtotal_amount");
                    String actTotalDiscountAmountItem = ((JSONObject) actPDItem).getString("total_discount_amount");
                    String actCouponDiscountAmountItem = ((JSONObject) actPDItem).getString("coupon_discount_amount");
                    String actProductDiscountAmountItem = ((JSONObject) actPDItem).getString("product_discount_amount");
                    String actActualAmountItem = ((JSONObject) actPDItem).getString("actual_amount");

                    String expSubTotalAmountItem = ((JSONObject) expPDItem).getString("subtotal_amount");
                    String expTotalDiscountAmountItem = ((JSONObject) expPDItem).getString("total_discount_amount");
                    String expCouponDiscountAmountItem = ((JSONObject) expPDItem).getString("coupon_discount_amount");
                    String expProductDiscountAmountItem = ((JSONObject) expPDItem).getString("product_discount_amount");
                    String epActualAmountItem = ((JSONObject) expPDItem).getString("actual_amount");
                    if (
                            actSubTotalAmountItem.equals(expSubTotalAmountItem)
                                    && actTotalDiscountAmountItem.equals(expTotalDiscountAmountItem)
                                    && actCouponDiscountAmountItem.equals(expCouponDiscountAmountItem)
                                    && actProductDiscountAmountItem.equals(expProductDiscountAmountItem)
                                    && actActualAmountItem.equals(epActualAmountItem)
                    ) {
                        itemCnt++;
                    }
                }
            }
        }

        JSONArray actCoupons = promotionDetail.getJSONArray("coupons");
//        JSONArray expCoupons = expPromotionDetail.getJSONArray("coupons");
        for (Object actCoupon : actCoupons) {
            String code = ((JSONObject) actCoupon).getString("code");
            String stock_id = ((JSONObject) actCoupon).getString("stock_id");
            if (code.equals(expCode)
                    && stock_id.equals(expStockID))
                couponCnt++;

        }

        if (cnt == 1 && itemCnt == expPDItems.size() && couponCnt == 1)
            return true;
        else return false;
    }


}
