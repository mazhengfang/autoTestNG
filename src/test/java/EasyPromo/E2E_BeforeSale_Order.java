package EasyPromo;

import APITestCase.EasyPromo.*;
import APITestCase.FacadePromo.queryUserCoupon;
import APITestCase.FacadePromo.userAvailableCoupon;
import APITestCase.FacadePromo.crossFunctions;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.http.httpResponse;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import xProject.flowFunction;

import static org.testng.Assert.assertEquals;

public class E2E_BeforeSale_Order {

    plans plans;
    repricing repricing;
    applyPromotionPlans applyPromotionPlans;
    promotionDetails promotionDetails;
    appendPromotion appendPromotion;
    productEstimatePrices productEstimatePrices;
    cartEstimatePrices cartEstimatePrices;
    userAvailableCoupon userAvailableCoupon;
    queryUserCoupon queryUserCoupon;
    crossFunctions crossFunctions;
    private xProject.flowFunction flowBaseAPICommon;
    private String easyPromoHost;
    private String facadePromoHost;
    private String Env;
    private String[] planIndexList;
    private String mirrorCode;
    private String bodyNumber = "1";
    private String personNumber = "13916485978";

    @BeforeSuite
    @Parameters({"ENV"})
    public void init(String ENV) {
        this.Env = ENV;
        this.flowBaseAPICommon = new flowFunction();
        flowBaseAPICommon.init(ENV);
        this.easyPromoHost = flowBaseAPICommon.easyPromoInternalHost;
        this.facadePromoHost = flowBaseAPICommon.facadeBenefitHost;
        plans = new plans();
        repricing = new repricing();
        applyPromotionPlans = new applyPromotionPlans();
        promotionDetails = new promotionDetails();
        appendPromotion = new appendPromotion();
        productEstimatePrices = new productEstimatePrices();
        cartEstimatePrices = new cartEstimatePrices();
        userAvailableCoupon = new userAvailableCoupon();
        queryUserCoupon = new queryUserCoupon();
        crossFunctions = new crossFunctions();
//        ProductActivity_TestSet001 productActivity_TestSet001 = new ProductActivity_TestSet001();
//        String StockID = productActivity_TestSet001.activityInfo.get("StockID");
    }

    @Test
    public void orderPreview_Plan() {
        httpResponse rs = plans.plans(Env, easyPromoHost,bodyNumber,personNumber);
        JSONObject rsData = rs.getBody(JSONObject.class).getJSONObject("data");
        JSONArray promoPlan = rsData.getJSONArray("promotion_plans");
        planIndexList = new String[promoPlan.size()];
        for (int i = 0; i < promoPlan.size(); i++) {
            planIndexList[i] = ((JSONObject) promoPlan.get(i)).getString("plan_index");
        }
        System.out.println(planIndexList);
    }

    @Test(dependsOnMethods = "orderPreview_Plan")
    public void orderPreview_Repricing() {
        httpResponse rs = repricing.repricing(easyPromoHost, planIndexList[0],bodyNumber);
        JSONObject rsData = rs.getBody(JSONObject.class).getJSONObject("data");
        mirrorCode = rsData.getString("mirror_code");
        System.out.println(mirrorCode);
    }

    @Test(dependsOnMethods = "orderPreview_Repricing")
    public void submitOrder_ApplyPromotionPlans() {
        httpResponse rs = applyPromotionPlans.applyPromotionPlans(easyPromoHost, mirrorCode,"1");
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "Apply Promotion Plans response returns incorrect");
    }

    ;

    @Test
    public void submitOrder_PromotionDetails() {
        httpResponse rs = promotionDetails.promotionDetails(easyPromoHost, applyPromotionPlans.orderID,"1");
        JSONObject rsData = rs.getBody(JSONObject.class).getJSONObject("data");
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "Promotion details response returns incorrect");
    }

    ;

    @Test
    public void orderPayment_appendPromotion() {
        httpResponse rs = appendPromotion.appendPromotion(easyPromoHost, "test20230816005q");
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "Append promotion response returns incorrect");
    }

    ;

    @Test
    public void productEstimatePrices() {
        httpResponse rs = productEstimatePrices.productEstimatePrices(easyPromoHost,"1");
        int rsCode = rs.getCode();
        JSONObject rsData = rs.getBody(JSONObject.class).getJSONObject("data");
        assertEquals(rsCode, 200, "Product estimate prices response returns incorrect");
    }

    ;

    @Test
    public void cartEstimatePrices() {
        httpResponse rs = cartEstimatePrices.cartEstimatePrices(easyPromoHost,"1");
        int rsCode = rs.getCode();
        JSONObject rsData = rs.getBody(JSONObject.class).getJSONObject("data");
        assertEquals(rsCode, 200, "Cart estimate prices response returns incorrect");
    }

    ;

    @Test
    public void queryUserCoupon() {
        String expStockID = "2309050031100002";
        JSONArray couponList;
        httpResponse rs = queryUserCoupon.queryUserCoupon(Env, facadePromoHost, "AVAILABLE", "ALL", 1, 5,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "查询用户领券接口返回报错");

        couponList = queryUserCoupon.couponList(rs, Env, facadePromoHost,personNumber);
        boolean couponNotEffective = crossFunctions.couponNotEffective(couponList,expStockID);
        assertEquals(couponNotEffective, true, "未生效活动在查询用户接口展示活动数据，期望结果不展示");
    }

    @Test
    public void userAvailableCoupon() {
        JSONArray couponList;
        String expStockID = "2309050031100002";
        httpResponse rs = userAvailableCoupon.userAvailableCoupon(Env, facadePromoHost,bodyNumber,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "我的优惠券接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponNotEffective = crossFunctions.couponNotEffective(rsData,expStockID);
        assertEquals(couponNotEffective, true, "未生效活动在我的优惠券展示活动数据，期望结果不展示");
    }

    @Test
    public void receiveCoupon() {

    }
}
