package E2E;

import APITestCase.EasyPromo.*;
import APITestCase.FacadePromo.crossFunctions;
import APITestCase.FacadePromo.loggedUserReceiveCoupon;
import APITestCase.FacadePromo.queryUserCoupon;
import APITestCase.FacadePromo.userAvailableCoupon;
import UITestCase.BackOffice.createActivity;
import UITestCase.BackOffice.login;
import UITestCase.BackOffice.newActivity;
import UITestCase.BackOffice.validateActivity;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.dbConnect.mySQL;
import com.http.httpResponse;
import com.ssh.App;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import xProject.flowFunction;
import xProject.testCase.backOfficeTestCase;
import xProject.testCase.testCaseTemplateOne;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.alibaba.fastjson.JSON.parseObject;
import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;

public class TestSet_H5_MPMSTORE_006_ShangPin {
    APITestCase.EasyPromo.plans plans;
    APITestCase.EasyPromo.repricing repricing;
    APITestCase.EasyPromo.applyPromotionPlans applyPromotionPlans;
    APITestCase.EasyPromo.promotionDetails promotionDetails;
    APITestCase.EasyPromo.appendPromotion appendPromotion;
    APITestCase.EasyPromo.productEstimatePrices productEstimatePrices;
    APITestCase.EasyPromo.cartEstimatePrices cartEstimatePrices;
    APITestCase.FacadePromo.userAvailableCoupon userAvailableCoupon;
    APITestCase.FacadePromo.queryUserCoupon queryUserCoupon;
    APITestCase.FacadePromo.crossFunctions crossFunctions;
    APITestCase.FacadePromo.loggedUserReceiveCoupon loggedUserReceiveCoupon;
    APITestCase.EasyPromo.redeemCoupon redeemCoupon;
    com.dbConnect.mySQL mySQL;
    App app;


    private xProject.flowFunction flowBaseAPICommon;
    private String easyPromoHost;
    private String facadePromoHost;
    private String Env;
    private String[] planIndexList;
    private String planIndex;
    private String mirrorCode;
    private String tcPath = "src/main/resources/ConfigInfo/TestCase/backOfficeTestCaseData.json";
    private String tcKey = "H5ProductActivity";
    private String promoDetailsTCPath = "src/main/resources/ConfigInfo/TestCase/promotionDetailsResponseTestCaseData.json";
    private String promoDetailsTCKey = "orderPromo_ResponseData_";
    private String orderItemPromosTCKey = "orderItemPromos_ResponseData_";
    private String couponSQL1 = "select * from coupon where code =\"";
    private String couponSQL2 = "\" and person_id =\"15005083203\"";
    private HashMap<String, String> activityInfo;
    WebDriver driver;
    UITestCase.BackOffice.login login;
    UITestCase.BackOffice.newActivity newActivity;
    UITestCase.BackOffice.createActivity createActivity;
    UITestCase.BackOffice.validateActivity validateActivity;
    backOfficeTestCase tc;
    testCaseTemplateOne tcOne;
    APITestCase.EasyPromo.refundCoupon refundCoupon;
    private String personNumber = "13916485978";
    private String couponCodeByStockID;
    private String plansExpectData = "src/main/resources/ConfigInfo/EasyPromo/plansResponse.json";
    private String repricingPromotionDetailExpectData = "src/main/resources/ConfigInfo/EasyPromo/repricingResponse.json";
    private String PromotionDetailsExpectData = "src/main/resources/ConfigInfo/EasyPromo/promotionDetailsResponse.json";
    private String plansPPKey = "plans_ResponseData_PromotionPlan_";
    private String repricingPromotionDetailKey = "repricing_ResponseData_PromotionDetail_";
    private String promotionDetailsKey = "promotionDetails_ResponseData_";
    private String couponUseDetailsKey = "couponUseDetails_ResponseData_";
    private String bodyNumber = "1";
    private String bodyNumber2 = "2";
    private String orderIDTicket;
    private JSONObject actOrderPromo;
    private JSONObject expOrderPromo;
    private JSONArray actOrderItemPromos;
    private JSONArray expOrderItemPromos;
    private JSONArray actCouponUseDetails;
    private JSONArray expCouponUseDetails;

    @BeforeSuite
    @Parameters({"ENV"})
    public void init(String ENV) {
        this.Env = ENV;
        this.flowBaseAPICommon = new flowFunction();
        flowBaseAPICommon.init(ENV);
        this.easyPromoHost = flowBaseAPICommon.easyPromoInternalHost;
        this.facadePromoHost = flowBaseAPICommon.facadeBenefitHost;
        repricing = new repricing();
        applyPromotionPlans = new applyPromotionPlans();
        promotionDetails = new promotionDetails();
        appendPromotion = new appendPromotion();
        productEstimatePrices = new productEstimatePrices();
        cartEstimatePrices = new cartEstimatePrices();
        userAvailableCoupon = new userAvailableCoupon();
        queryUserCoupon = new queryUserCoupon();
        crossFunctions = new crossFunctions();
        loggedUserReceiveCoupon = new loggedUserReceiveCoupon();
        plans = new plans();
        redeemCoupon = new redeemCoupon();
        refundCoupon = new refundCoupon();
        login = new login();
        newActivity = new newActivity();
        createActivity = new createActivity();
        validateActivity = new validateActivity();
        tc = new backOfficeTestCase();
        tcOne = new testCaseTemplateOne();
        app = new App();
        mySQL = new mySQL();
    }

    @BeforeSuite
    public void closeWindows() throws IOException {
        login.closeWindow();
    }

    @Test(enabled = true)
    public void test001_启动后台() {
        driver = login.init("https://easypromo-front.pp.dktapp.cloud/login");
        boolean loginResult = login.loginOn(driver);
        assertEquals(loginResult, true, "BackOffice登入失败");
    }

    @Test(dependsOnMethods = {"test001_启动后台"}, enabled = true)
    public void test002_创建活动页面() throws InterruptedException {
        boolean newActivityResult = newActivity.newActivity(driver);
        assertEquals(newActivityResult, true, "BackOffice新建活动页面失败");
    }

    @Test(dependsOnMethods = {"test002_创建活动页面"})
    public void test003_创建活动页商品券() throws InterruptedException {
        boolean newCouponResult = createActivity.createH5ProductActivity(driver,"[Auto]H5单","noStack",15,"105","5");
        assertEquals(newCouponResult, true, "BackOffice创建单品券失败");
    }

    //
    @Test(dependsOnMethods = {"test003_创建活动页商品券"}, enabled = true)
    public void test004_活动页商品券页面获取() throws InterruptedException {
        activityInfo = validateActivity.validateActivity(driver,"product","noStack");
    }

    @Test(dependsOnMethods = {"test004_活动页商品券页面获取"}, enabled = true)
    public void test005_校验活动页商品券() {
        assertEquals(activityInfo.get("Activity Name"), createActivity.AtyName, "Activity name is not correct");
        assertEquals(commonFunction.toFormatDate(activityInfo.get("Distribute Time").substring(0, 10)), createActivity.StartDate, "distribute start date is not correct");
        assertEquals(commonFunction.toFormatDate(activityInfo.get("Distribute Time").substring(21, 32)), createActivity.EndDate, "distribute start time is not correct");
        assertEquals(activityInfo.get("Distribute Time").substring(11, 19), createActivity.StartTime, "distribute end date is not correct");
        assertEquals(activityInfo.get("Distribute Time").substring(33, 41), createActivity.EndTime, "distribute end time is not correct");

        assertEquals(commonFunction.toFormatDate(activityInfo.get("Effective Time").substring(0, 10)), createActivity.StartDate, "Effective start date is not correct");
        assertEquals(activityInfo.get("Effective Time").substring(11, 19), createActivity.StartTime, "Effective Start Time  is not correct");
        assertEquals(commonFunction.toFormatDate(activityInfo.get("Effective Time").substring(21, 32)), createActivity.EndDate, "Effective End Time  is not correct");
        assertEquals(activityInfo.get("Effective Time").substring(33, 41), createActivity.EndTime, "Effective End Time  is not correct");

//        assertEquals(activityInfo.get("Product Checked"), "true", "Coupon Stacking Status is not correct");
//        assertEquals(activityInfo.get("Stacking Checked"), "false", "Coupon Stacking Status is not correct");
    }

    @Test(dataProvider = "test005_校验活动页商品券", dependsOnMethods = {"test005_校验活动页商品券"}, enabled = true)
    public void test006_校验活动页商品券(backOfficeTestCase tc) {
        String sKey = tc.getKey();
        String sValue = tc.getValue();
        String actualValue = activityInfo.get(sKey);
        assertEquals(actualValue, sValue, sKey + " value is not correct");
    }

    @DataProvider(name = "test005_校验活动页商品券")
    private Object[][] test005_校验活动页商品券_TestData() {
        JSONArray json = tc.getCaseByPath(tcPath, "$." + tcKey);
        List<backOfficeTestCase> cases = json.toJavaList(backOfficeTestCase.class);
        final Object[][] objects = new Object[cases.size()][];
        for (int i = 0; i < cases.size(); i++) {
            objects[i] = new Object[]{cases.get(i)};
        }
        return objects;
    }

    @Test(dependsOnMethods = {"test006_校验活动页商品券"}, enabled = true)
    public void test007_queryUserCoupon() {
        JSONArray couponList;
        httpResponse rs = queryUserCoupon.queryUserCoupon(Env, facadePromoHost, "AVAILABLE", "ALL", 1, 5,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "查询用户领券接口返回报错");

        couponList = queryUserCoupon.couponList(rs, Env, facadePromoHost,personNumber);
        boolean couponNotEffective = crossFunctions.couponNotEffective(couponList, activityInfo.get("StockID"));
        assertEquals(couponNotEffective, true, "未生效活动在我的优惠券接口展示活动数据，期望结果不展示");
    }


    @Test(dependsOnMethods = {"test007_queryUserCoupon"}, enabled = true)
    public void test008_userAvailableCoupon() {
        httpResponse rs = userAvailableCoupon.userAvailableCoupon(Env, facadePromoHost, bodyNumber2,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "我的优惠券接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponNotEffective = crossFunctions.couponNotEffective(rsData, activityInfo.get("StockID"));
        assertEquals(couponNotEffective, true, "未生效活动在PDP页面接口展示活动数据，期望结果不展示");
    }


    @Test(dependsOnMethods = {"test008_userAvailableCoupon"}, enabled = true)
    public void test013_loggedUserReceiveCoupon() throws InterruptedException {
        httpResponse rs = loggedUserReceiveCoupon.loggedUserReceiveCoupon_StockID(Env, facadePromoHost, personNumber, activityInfo.get("StockID"));
        int rsCode = rs.getCode();
        String titleInfo = rs.getBody(JSONObject.class).getString("title");
        assertEquals(rsCode, 400, "会员领券返回code异常");
        assertEquals(titleInfo, "批次无效或暂未开始", "会员领券返回title异常");
    }


    @Test(dependsOnMethods = {"test013_loggedUserReceiveCoupon"}, enabled = true)
    public void test009_活动页商品券页面获取() throws InterruptedException {
        activityInfo = validateActivity.UpdateActivity(driver, activityInfo, "normal", "noStack",120000);
        assertEquals(activityInfo.get("Active Status"), "Take effect", "活动状态变更错误");
    }


    @Test(dependsOnMethods = {"test009_活动页商品券页面获取"}, enabled = true)
    public void test010_queryUserCoupon() throws InterruptedException {
        JSONArray couponList;
        httpResponse rs = queryUserCoupon.queryUserCoupon(Env, facadePromoHost, "AVAILABLE", "ALL", 1, 5,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "查询用户领券接口返回报错");

        couponList = queryUserCoupon.couponList(rs, Env, facadePromoHost,personNumber);
        boolean couponNotEffective = crossFunctions.couponEffective(couponList, activityInfo.get("StockID"));
        assertEquals(couponNotEffective, false, "生效活动在我的优惠券接口展示活动数据，期望结果未展示");
    }

    @Test(dependsOnMethods = {"test010_queryUserCoupon"}, enabled = true)
    public void test011_userAvailableCoupon() throws InterruptedException {
        sleep(60000);
        httpResponse rs = userAvailableCoupon.userAvailableCoupon(Env, facadePromoHost, bodyNumber2,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "我的优惠券接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponNotEffective = crossFunctions.couponEffective(rsData, activityInfo.get("StockID"));
        assertEquals(couponNotEffective, false, "生效活动在PDP页面接口展示活动数据，期望结果未展示");
    }

    @Test(dependsOnMethods = {"test011_userAvailableCoupon"}, enabled = true)
    public void test012_loggedUserReceiveCoupon() throws InterruptedException {
        httpResponse rs = loggedUserReceiveCoupon.loggedUserReceiveCoupon_StockID(Env, facadePromoHost, personNumber, activityInfo.get("StockID"));
        int rsCode = rs.getCode();
        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        couponCodeByStockID = ((JSONObject) rsData.getJSONObject(0)).getString("coupon_code");
        System.out.println("StockId-Code: " + activityInfo.get("StockID") + "-" +couponCodeByStockID);
        assertEquals(rsCode, 201, "会员领券失败");
    }

    @Test(dependsOnMethods = {"test012_loggedUserReceiveCoupon"}, enabled = true)
    public void test018_queryUserCoupon() throws InterruptedException {
        JSONArray couponList;
        httpResponse rs = queryUserCoupon.queryUserCoupon(Env, facadePromoHost, "AVAILABLE", "ALL", 1, 5,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "查询用户领券接口返回报错");
        couponList = queryUserCoupon.couponList(rs, Env, facadePromoHost,personNumber);
        boolean couponCodeStatus = crossFunctions.couponCodeValidation(couponList, activityInfo.get("StockID"), couponCodeByStockID,"AVAILABLE");
        assertEquals(couponCodeStatus, true, "领券后优惠券状态异常，期望结果是AVAILABLE");
    }

    @Test(dependsOnMethods = {"test018_queryUserCoupon"}, enabled = true)
    public void test019_userAvailableCoupon() throws InterruptedException {
        sleep(10000);
        httpResponse rs = userAvailableCoupon.userAvailableCoupon(Env, facadePromoHost, bodyNumber2,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "我的优惠券接口返回报错");
        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponCodeStatus = crossFunctions.couponCodeValidation(rsData, activityInfo.get("StockID"), couponCodeByStockID,"AVAILABLE");
        assertEquals(couponCodeStatus, true, "领券后优惠券状态异常，期望结果是AVAILABLE");
    }


    @Test(dependsOnMethods = {"test019_userAvailableCoupon"}, enabled = true)
    public void test029_plans_NonAssignedProducts() throws InterruptedException {
        httpResponse rs = plans.plans(Env, easyPromoHost, bodyNumber,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "Plans接口返回报错");

        JSONObject rsData = rs.getBody(JSONObject.class).getJSONObject("data");
        JSONArray couponsList = rsData.getJSONArray("coupons");
        assertEquals(couponsList.size(),0,"未指定商品返回的促销信息，期待结果是不返回促销信息");

        JSONArray promotionPlansList = rsData.getJSONArray("promotion_plans");
        assertEquals(promotionPlansList.size(),0,"未指定商品返回的促销信息，期待结果是不返回促销信息");
    }

    @Test(dependsOnMethods = {"test029_plans_NonAssignedProducts"}, enabled = true)
    public void test015_plans_AssignedProducts() throws InterruptedException {
        httpResponse rs = plans.plans(Env, easyPromoHost, bodyNumber2,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "Plans接口返回报错");

        JSONObject rsData = rs.getBody(JSONObject.class).getJSONObject("data");
        JSONArray couponsList = rsData.getJSONArray("coupons");
        JSONArray promotionPlansList = rsData.getJSONArray("promotion_plans");
        JSONObject expPromotionPlans = parseObject(commonFunction.readJsonFile(plansExpectData)).getJSONObject(plansPPKey + bodyNumber2);
        boolean promotionResult = plans.promotionPlansValidation(promotionPlansList, activityInfo.get("StockID"), expPromotionPlans);
        assertEquals(promotionResult, true, "指定商品促销优惠信息校验出错");

        boolean couponResult = plans.couponsValidation(couponsList, activityInfo);
        assertEquals(couponResult, true, "指定促销优惠券的基本信息校验出错");

        planIndex = plans.planIndex;
    }

    @Test(dependsOnMethods = {"test015_plans_AssignedProducts"}, enabled = true)
    public void test015_repricing() throws InterruptedException {
        httpResponse rs = repricing.repricing(easyPromoHost, planIndex, bodyNumber2);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "Repricing接口返回报错");

        JSONObject rsData = rs.getBody(JSONObject.class).getJSONObject("data");
        mirrorCode = rsData.getString("mirror_code");
        JSONObject promotionDetail = rsData.getJSONObject("promotion_detail");
        JSONObject expPromotionDetail = parseObject(commonFunction.readJsonFile(repricingPromotionDetailExpectData)).getJSONObject(repricingPromotionDetailKey + bodyNumber2);
        boolean promotionDetailValidationResult = repricing.promotionDetailValidation(promotionDetail, expPromotionDetail, couponCodeByStockID, activityInfo.get("StockID"));
        assertEquals(promotionDetailValidationResult, true, "Repricing优惠信息计算错误");
    }

    @Test(dependsOnMethods = {"test015_repricing"}, enabled = true)
    public void test016_applyPromotionPlans() throws Exception {
        httpResponse rs = applyPromotionPlans.applyPromotionPlans(easyPromoHost, mirrorCode, bodyNumber2);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "Apply Promotion Plan接口返回报错");
        orderIDTicket = applyPromotionPlans.orderID;
        System.out.println("Order: "+ orderIDTicket);

        app = new App();
        app.connect();
        JSONObject dbRecord =mySQL.record("epo_ep_pp","coupon",couponSQL1+couponCodeByStockID+couponSQL2);
        assertEquals(dbRecord.getString("status"), "FROZEN", "Redeem Coupon接口返回报错");
    }

    @Test(dependsOnMethods = {"test016_applyPromotionPlans"}, enabled = true)
    public void test020_queryUserCoupon() throws InterruptedException {
        JSONArray couponList;
        httpResponse rs = queryUserCoupon.queryUserCoupon(Env, facadePromoHost, "USED", "ALL", 1, 5,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "查询用户领券接口返回报错");
        couponList = queryUserCoupon.couponList(rs, Env, facadePromoHost,personNumber);
        boolean couponCodeStatus = crossFunctions.couponCodeValidation(couponList, activityInfo.get("StockID"), couponCodeByStockID,"USED");
        assertEquals(couponCodeStatus, true, "核券后优惠券状态异常，期望结果是USED");
    }


    @Test(dependsOnMethods = {"test020_queryUserCoupon"}, enabled = true)
    public void test021_userAvailableCoupon() throws InterruptedException {
        httpResponse rs = userAvailableCoupon.userAvailableCoupon(Env, facadePromoHost, bodyNumber2,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "我的优惠券接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponNotEffective = crossFunctions.couponNotEffective(rsData, activityInfo.get("StockID"));
        assertEquals(couponNotEffective, true, "生效活动在PDP页面接口展示活动数据，期望结果不展示");
    }

    //  "test20230816005q1" orderIDTicket
    @Test(dependsOnMethods = {"test021_userAvailableCoupon"}, enabled = true)
    public void test017_promotionDetails_001() throws InterruptedException {
        httpResponse rs = promotionDetails.promotionDetails(easyPromoHost, orderIDTicket, bodyNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "Promotion Details接口返回报错");
        JSONObject rsData = rs.getBody(JSONObject.class).getJSONObject("data");
        JSONObject expPromotionDetailsData = parseObject(commonFunction.readJsonFile(PromotionDetailsExpectData)).getJSONObject(promotionDetailsKey + bodyNumber2);
        actOrderPromo = rsData.getJSONObject("order_promo");
        expOrderPromo = expPromotionDetailsData.getJSONObject("order_promo");

        actOrderItemPromos = rsData.getJSONArray("order_item_promos");
        expOrderItemPromos = expPromotionDetailsData.getJSONArray("order_item_promos");

        actCouponUseDetails = rsData.getJSONArray("coupon_use_details");
        expCouponUseDetails = expPromotionDetailsData.getJSONArray("coupon_use_details");
    }

    @Test(dataProvider = "test017_promotionDetails_002", dependsOnMethods = {"test017_promotionDetails_001"}, enabled = true)
    public void test017_promotionDetails_002(testCaseTemplateOne tcOne) {
        String sKey = tcOne.getKey();
        String actData = actOrderPromo.getString(sKey);
        String expData = expOrderPromo.getString(sKey);
        System.out.println("Key2 " + sKey + ":" + actData + ":" + expData);
        assertEquals(actData, expData, sKey + " value is not correct");
    }

    @DataProvider(name = "test017_promotionDetails_002")
    private Object[][] test018_promotionDetails_002_TestData() {
        JSONArray json = tcOne.getCaseByPath(promoDetailsTCPath, "$." + promoDetailsTCKey + bodyNumber);
        List<testCaseTemplateOne> cases = json.toJavaList(testCaseTemplateOne.class);
        final Object[][] objects = new Object[cases.size()][];
        for (int i = 0; i < cases.size(); i++) {
            objects[i] = new Object[]{cases.get(i)};
        }
        return objects;
    }

    @Test(dataProvider = "test017_promotionDetails_003", dependsOnMethods = {"test017_promotionDetails_002"}, enabled = true)
    public void test017_promotionDetails_003(testCaseTemplateOne tcOne) {
        String sKey = tcOne.getKey();
        for (Object actData : actOrderItemPromos) {
            String actSkuID = ((JSONObject) actData).getString("skuId");
            for (Object expData : expOrderItemPromos) {
                String expSkuID = ((JSONObject) expData).getString("skuId");
                if (actSkuID.equals(expSkuID)) {
                    String actItemData = ((JSONObject) actData).getString(sKey);
                    String expItemData = ((JSONObject) expData).getString(sKey);
                    System.out.println("Key3 " + sKey + ":" + actItemData + ":" + expItemData);
                    assertEquals(actItemData, expItemData, actSkuID + "对应的" + sKey + " value is not correct");
                }
            }
        }
    }

    @DataProvider(name = "test017_promotionDetails_003")
    private Object[][] test017_promotionDetails_003_TestData() {
        JSONArray json = tcOne.getCaseByPath(promoDetailsTCPath, "$." + orderItemPromosTCKey + bodyNumber);
        List<testCaseTemplateOne> cases = json.toJavaList(testCaseTemplateOne.class);
        final Object[][] objects = new Object[cases.size()][];
        for (int i = 0; i < cases.size(); i++) {
            objects[i] = new Object[]{cases.get(i)};
        }
        return objects;
    }

    @Test(dataProvider = "test017_promotionDetails_004", dependsOnMethods = {"test017_promotionDetails_003"}, enabled = true)
    public void test017_promotionDetails_004(testCaseTemplateOne tcOne) {
        String sKey = tcOne.getKey();
        for (Object actData : actCouponUseDetails) {
            String actCode = ((JSONObject) actData).getString("code");
            for (Object expData : expCouponUseDetails) {
//                String expCode = couponCodeByStockID;
                if (actCode.equals(couponCodeByStockID)) {
                    String actItemData = ((JSONObject) actData).getString(sKey);
                    String expItemData = ((JSONObject) expData).getString(sKey);
                    System.out.println("Key4 " + sKey + ":" + actItemData + ":" + expItemData);
                    assertEquals(actItemData, expItemData, actCode + "对应的" + sKey + " value is not correct");
                }
            }
        }
    }

    @DataProvider(name = "test017_promotionDetails_004")
    private Object[][] test017_promotionDetails_004_TestData() {
        JSONArray json = tcOne.getCaseByPath(promoDetailsTCPath, "$." + couponUseDetailsKey + bodyNumber);
        List<testCaseTemplateOne> cases = json.toJavaList(testCaseTemplateOne.class);
        final Object[][] objects = new Object[cases.size()][];
        for (int i = 0; i < cases.size(); i++) {
            objects[i] = new Object[]{cases.get(i)};
        }
        return objects;
    }

    @Test(dependsOnMethods = {"test017_promotionDetails_004"}, enabled = true)
    public void test022_redeemCoupon() throws Exception {
        httpResponse rs = redeemCoupon.redeemCoupon(easyPromoHost, orderIDTicket, bodyNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "Redeem Coupon接口返回报错");

        JSONObject dbRecord =mySQL.record("epo_ep_pp","coupon",couponSQL1+couponCodeByStockID+couponSQL2);
        assertEquals(dbRecord.getString("status"), "USED", "Redeem Coupon接口返回报错");
    }

    @Test(dependsOnMethods = {"test022_redeemCoupon"}, enabled = true)
    public void test023_refundCoupon() throws Exception {
        httpResponse rs = refundCoupon.refundCoupon(easyPromoHost, orderIDTicket, bodyNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "Refund Coupon接口返回报错");

        JSONObject dbRecord =mySQL.record("epo_ep_pp","coupon",couponSQL1 + couponCodeByStockID + couponSQL2);
        assertEquals(dbRecord.getString("status"), "EXCHANGED", "Refund Coupon接口返回报错");
        app.disconnect();
    }

    @Test(dependsOnMethods = {"test023_refundCoupon"}, enabled = true)
    public void test024_queryUserCoupon() throws InterruptedException {
        JSONArray couponList;
        httpResponse rs = queryUserCoupon.queryUserCoupon(Env, facadePromoHost, "AVAILABLE", "ALL", 1, 5,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "查询用户领券接口返回报错");
        couponList = queryUserCoupon.couponList(rs, Env, facadePromoHost,personNumber);
        boolean couponCodeStatus = crossFunctions.couponCodeValidation(couponList, activityInfo.get("StockID"), couponCodeByStockID,"AVAILABLE");
        assertEquals(couponCodeStatus, true, "退券后优惠券状态异常，期望结果是AVAILABLE");
    }


    @Test(dependsOnMethods = {"test024_queryUserCoupon"}, enabled = true)
    public void test025_userAvailableCoupon() throws InterruptedException {
        sleep(10000);
        httpResponse rs = userAvailableCoupon.userAvailableCoupon(Env, facadePromoHost, bodyNumber2,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "我的优惠券接口返回报错");
        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponCodeStatus = crossFunctions.couponCodeValidation(rsData, activityInfo.get("StockID"), couponCodeByStockID,"AVAILABLE");
        assertEquals(couponCodeStatus, true, "退券后优惠券状态异常，期望结果是AVAILABLE");
    }

    @Test(dependsOnMethods = {"test025_userAvailableCoupon"}, enabled = true)
    public void test029_活动页商品券活动关闭() throws InterruptedException {
        String activityStatus = validateActivity.closeActivity(driver,  activityInfo.get("Activity ID"));
        assertEquals(activityStatus, "Closed", "活动状态变更错误");
    }

    @Test(dependsOnMethods = {"test029_活动页商品券活动关闭"}, enabled = true)
    public void test026_loggedUserReceiveCoupon() throws InterruptedException {
        httpResponse rs = loggedUserReceiveCoupon.loggedUserReceiveCoupon_StockID(Env, facadePromoHost, personNumber, activityInfo.get("StockID"));
        int rsCode = rs.getCode();
        String titleData = rs.getBody(JSONObject.class).getString("title");
        assertEquals(rsCode, 400, "会员领券失败返回的code信息不对");
        assertEquals(titleData, "批次无效或暂未开始", "活动关闭后，会员领券返回的title信息不对");
    }

    @Test(dependsOnMethods = {"test026_loggedUserReceiveCoupon"}, enabled = true)
    public void test028_userAvailableCoupon() {
        httpResponse rs = userAvailableCoupon.userAvailableCoupon(Env, facadePromoHost, bodyNumber,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "我的优惠券接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponNotEffective = crossFunctions.couponNotEffective(rsData, activityInfo.get("StockID"));
        assertEquals(couponNotEffective, true, "活动关闭后，活动在PDP页面接口展示活动数据，期望结果不展示");
    }

    //    @AfterSuite
    public void loginOut() {
        login.loginOut();
    }

}
