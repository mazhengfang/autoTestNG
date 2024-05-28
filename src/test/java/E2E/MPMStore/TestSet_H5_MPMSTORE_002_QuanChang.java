package E2E.MPMStore;

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
import org.testng.annotations.*;
import xProject.flowFunction;
import xProject.testCase.backOfficeTestCase;
import xProject.testCase.testCaseTemplateOne;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.alibaba.fastjson.JSON.parseObject;
import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;

public class TestSet_H5_MPMSTORE_002_QuanChang {
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
    JSONObject actCartEstimatePricesResponseData;
    private String cartEstimatePricesExpectData = "src/main/resources/ConfigInfo/EasyPromo/cartEstimatePricesResponse.json";
    private String cartEstimatePricesBasicInfoExpectDataKey = "cartEstimatePrices_ResponseData_BasicInfo_";
    private String cartEstimatePricesCouponPlanExpectDataKey = "cartEstimatePrices_ResponseData_CouponPlans_";
    private String bodyNumber = "1";
    private String bodyNumber2 = "2";
    private String cartEstimatePricesTCPath = "src/main/resources/ConfigInfo/TestCase/cartEstimatePricesResponseTestCaseData.json";
    private String cartEstimatePricesTCBasicInfoKey = "cartEstimatePrices_ResponseData_BasicInfo_";

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

    //    @BeforeSuite
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
    public void test003_创建活动页全场券() throws InterruptedException {
        boolean newCouponResult = createActivity.createH5NormalActivity(driver,"[Auto]H5全", "noStack",15,"398","360");
        assertEquals(newCouponResult, true, "BackOffice创建全场券失败");
    }

    @Test(dependsOnMethods = {"test003_创建活动页全场券"}, enabled = true)
    public void test004_活动页全场券页面获取() throws InterruptedException {
        activityInfo = validateActivity.validateActivity(driver,"normal","noStack");
    }

    @Test(dependsOnMethods = {"test004_活动页全场券页面获取"}, enabled = true)
    public void test005_活动页全场券页面获取() throws InterruptedException {
        activityInfo = validateActivity.UpdateActivity(driver, activityInfo, "normal", "noStack",130000);
        System.out.println("Part 1 "+activityInfo.toString());
        assertEquals(activityInfo.get("Active Status"), "Take effect", "活动状态变更错误");
    }

    @Test(dependsOnMethods = {"test005_活动页全场券页面获取"}, enabled = true)
    public void test009_loggedUserReceiveCoupon() throws InterruptedException {
        httpResponse rs = loggedUserReceiveCoupon.loggedUserReceiveCoupon_StockID(Env, facadePromoHost,"MPMSTORE", personNumber, activityInfo.get("StockID"));
        int rsCode = rs.getCode();
        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        couponCodeByStockID = ((JSONObject) rsData.getJSONObject(0)).getString("coupon_code");
        System.out.println("Part 1 receive code "+couponCodeByStockID);
        assertEquals(rsCode, 201, "会员领券失败");
    }

    @Test(dependsOnMethods = {"test009_loggedUserReceiveCoupon"}, enabled = true)
    public void test006_cartEstimatePrices() throws InterruptedException {
        httpResponse rs = cartEstimatePrices.cartEstimatePrices(easyPromoHost,"4");
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "商品预估价格接口返回失败");

        actCartEstimatePricesResponseData = rs.getBody(JSONObject.class).getJSONObject("data");
        JSONArray couponPlan = actCartEstimatePricesResponseData.getJSONArray("coupon_plans") ;
        assertEquals(couponPlan,null,"未达到门槛,购物车没有匹配的优惠,实际返回了优惠");
    }


    @Test(dependsOnMethods = {"test006_cartEstimatePrices"}, enabled = true)
    public void test010_cartEstimatePrices() throws InterruptedException {
        httpResponse rs = cartEstimatePrices.cartEstimatePrices(easyPromoHost,"1");
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "商品预估价格接口返回失败");

        actCartEstimatePricesResponseData = rs.getBody(JSONObject.class).getJSONObject("data");
        JSONObject actCouponPlan = (JSONObject) actCartEstimatePricesResponseData.getJSONArray("coupon_plans").get(0);
        String actCouponCode = (actCouponPlan.getJSONArray("instances").getJSONObject(0)).getString("code");
        assertEquals(actCouponCode,couponCodeByStockID,couponCodeByStockID +"对应的值不一致");
    }


    @Test(dataProvider = "test011_cartEstimatePrices", dependsOnMethods = {"test010_cartEstimatePrices"}, enabled = true)
    public void test011_cartEstimatePrices(testCaseTemplateOne tcOne) {
        String sKey = tcOne.getKey();

        JSONObject expCartEstimatePricesResponseData = parseObject(commonFunction.readJsonFile(cartEstimatePricesExpectData)).getJSONObject(cartEstimatePricesBasicInfoExpectDataKey + bodyNumber);
        String actData = actCartEstimatePricesResponseData.getString(sKey);
        String expData = expCartEstimatePricesResponseData.getString(sKey);
        System.out.println(sKey + " " + actData + " " +expData  );
        assertEquals(actData,expData,sKey+"对应的值不一致");
    }

    @DataProvider(name = "test011_cartEstimatePrices")
    private Object[][] test011_cartEstimatePrices_TestData() {
        JSONArray json = tcOne.getCaseByPath(cartEstimatePricesTCPath, "$." + cartEstimatePricesTCBasicInfoKey + bodyNumber);
        List<testCaseTemplateOne> cases = json.toJavaList(testCaseTemplateOne.class);
        final Object[][] objects = new Object[cases.size()][];
        for (int i = 0; i < cases.size(); i++) {
            objects[i] = new Object[]{cases.get(i)};
        }
        return objects;
    }

    @Test(dataProvider = "test012_cartEstimatePrices", dependsOnMethods = {"test011_cartEstimatePrices"}, enabled = true)
    public void test012_cartEstimatePrices(testCaseTemplateOne tcOne) {
        String sKey = tcOne.getKey();
        JSONObject expCouponPlan = parseObject(commonFunction.readJsonFile(cartEstimatePricesExpectData)).getJSONObject(cartEstimatePricesCouponPlanExpectDataKey + bodyNumber);
        JSONObject actCouponPlan = (JSONObject) actCartEstimatePricesResponseData.getJSONArray("coupon_plans").get(0);
        String actData = actCouponPlan.getString(sKey);
        if(sKey.equals("stock_name")){
            System.out.println(sKey + " " + actData + " " +activityInfo.get("Activity Name")  );
            assertEquals(actData,activityInfo.get("Activity Name"),sKey+"对应的值不一致");
        } else if (sKey.equals("stock_id")){
            System.out.println(sKey + " " + actData + " " +activityInfo.get("StockID")  );
            assertEquals(actData,activityInfo.get("StockID"),sKey+"对应的值不一致");
        }else{
            String expData = expCouponPlan.getString(sKey);
            System.out.println(sKey + " " + actData + " " +expData  );
            assertEquals(actData,expData,sKey+"对应的值不一致");
        }
    }

    @DataProvider(name = "test012_cartEstimatePrices")
    private Object[][] test012_cartEstimatePrices_TestData() {
        JSONArray json = tcOne.getCaseByPath(cartEstimatePricesTCPath, "$." + cartEstimatePricesCouponPlanExpectDataKey + bodyNumber);
        List<testCaseTemplateOne> cases = json.toJavaList(testCaseTemplateOne.class);
        final Object[][] objects = new Object[cases.size()][];
        for (int i = 0; i < cases.size(); i++) {
            objects[i] = new Object[]{cases.get(i)};
        }
        return objects;
    }



    @Test(dependsOnMethods = {"test012_cartEstimatePrices"}, enabled = true)
    public void test013_创建活动页面() throws InterruptedException {
        boolean newActivityResult = newActivity.newActivity(driver);
        assertEquals(newActivityResult, true, "BackOffice新建活动页面失败");
    }

    @Test(dependsOnMethods = {"test013_创建活动页面"})
    public void test014_创建活动页全场券() throws InterruptedException {
        boolean newCouponResult = createActivity.createH5NormalActivity(driver,"[Auto]H5全", "noStack",3,"398","370");
        assertEquals(newCouponResult, true, "BackOffice创建全场券失败");
    }

    @Test(dependsOnMethods = {"test014_创建活动页全场券"}, enabled = true)
    public void test015_活动页全场券页面获取() throws InterruptedException {
        activityInfo = validateActivity.validateActivity(driver,"normal","noStack");
    }

    @Test(dependsOnMethods = {"test015_活动页全场券页面获取"}, enabled = true)
    public void test016_活动页全场券页面获取() throws InterruptedException {
        activityInfo = validateActivity.UpdateActivity(driver, activityInfo, "normal", "noStack",120000);
        System.out.println("Part 2 "+activityInfo.toString());
        assertEquals(activityInfo.get("Active Status"), "Take effect", "活动状态变更错误");
    }

    @Test(dependsOnMethods = {"test016_活动页全场券页面获取"}, enabled = true)
    public void test017_loggedUserReceiveCoupon() throws InterruptedException {
        httpResponse rs = loggedUserReceiveCoupon.loggedUserReceiveCoupon_StockID(Env, facadePromoHost,"MPMSTORE", personNumber, activityInfo.get("StockID"));
        int rsCode = rs.getCode();
        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        couponCodeByStockID = ((JSONObject) rsData.getJSONObject(0)).getString("coupon_code");
        System.out.println("Part 2 receive code"+ couponCodeByStockID);
        assertEquals(rsCode, 201, "会员领券失败");
    }


    @Test(dependsOnMethods = {"test017_loggedUserReceiveCoupon"}, enabled = true)
    public void test018_cartEstimatePrices() throws InterruptedException {
        httpResponse rs = cartEstimatePrices.cartEstimatePrices(easyPromoHost,"1");
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "商品预估价格接口返回失败");

        actCartEstimatePricesResponseData = rs.getBody(JSONObject.class).getJSONObject("data");
        JSONObject actCouponPlan = (JSONObject) actCartEstimatePricesResponseData.getJSONArray("coupon_plans").get(0);
        String actCouponCode = (actCouponPlan.getJSONArray("instances").getJSONObject(0)).getString("code");
        assertEquals(actCouponCode,couponCodeByStockID,couponCodeByStockID +"对应的值不一致");
    }


    @Test(dataProvider = "test019_cartEstimatePrices", dependsOnMethods = {"test018_cartEstimatePrices"}, enabled = true)
    public void test019_cartEstimatePrices(testCaseTemplateOne tcOne) {
        String sKey = tcOne.getKey();

        JSONObject expCartEstimatePricesResponseData = parseObject(commonFunction.readJsonFile(cartEstimatePricesExpectData)).getJSONObject(cartEstimatePricesBasicInfoExpectDataKey + bodyNumber2);
        String actData = actCartEstimatePricesResponseData.getString(sKey);
        String expData = expCartEstimatePricesResponseData.getString(sKey);
        System.out.println(sKey + " " + actData + " " +expData  );
        assertEquals(actData,expData,sKey+"对应的值不一致");
    }

    @DataProvider(name = "test019_cartEstimatePrices")
    private Object[][] test019_cartEstimatePrices_TestData() {
        JSONArray json = tcOne.getCaseByPath(cartEstimatePricesTCPath, "$." + cartEstimatePricesTCBasicInfoKey + bodyNumber);
        List<testCaseTemplateOne> cases = json.toJavaList(testCaseTemplateOne.class);
        final Object[][] objects = new Object[cases.size()][];
        for (int i = 0; i < cases.size(); i++) {
            objects[i] = new Object[]{cases.get(i)};
        }
        return objects;
    }

    @Test(dataProvider = "test020_cartEstimatePrices", dependsOnMethods = {"test019_cartEstimatePrices"}, enabled = true)
    public void test020_cartEstimatePrices(testCaseTemplateOne tcOne) {
        String sKey = tcOne.getKey();

        JSONObject expCouponPlan = parseObject(commonFunction.readJsonFile(cartEstimatePricesExpectData)).getJSONObject(cartEstimatePricesCouponPlanExpectDataKey + bodyNumber2);
        JSONObject actCouponPlan = (JSONObject) actCartEstimatePricesResponseData.getJSONArray("coupon_plans").get(0);
        String actData = actCouponPlan.getString(sKey);
        if(sKey.equals("stock_name")){
            System.out.println(sKey + " " + actData + " " +activityInfo.get("Activity Name")  );
            assertEquals(actData,activityInfo.get("Activity Name"),sKey+"对应的值不一致");
        } else if (sKey.equals("stock_id")){
            System.out.println(sKey + " " + actData + " " +activityInfo.get("StockID")  );
            assertEquals(actData,activityInfo.get("StockID"),sKey+"对应的值不一致");
        }else{
            String expData = expCouponPlan.getString(sKey);
            System.out.println(sKey + " " + actData + " " +expData  );
            assertEquals(actData,expData,sKey+"对应的值不一致");
        }

    }

    @DataProvider(name = "test020_cartEstimatePrices")
    private Object[][] test020_cartEstimatePrices_TestData() {
        JSONArray json = tcOne.getCaseByPath(cartEstimatePricesTCPath, "$." + cartEstimatePricesCouponPlanExpectDataKey + bodyNumber);
        List<testCaseTemplateOne> cases = json.toJavaList(testCaseTemplateOne.class);
        final Object[][] objects = new Object[cases.size()][];
        for (int i = 0; i < cases.size(); i++) {
            objects[i] = new Object[]{cases.get(i)};
        }
        return objects;
    }

    @Test(dependsOnMethods = {"test020_cartEstimatePrices"}, enabled = true)
    public void test021_loggedUserReceiveCoupon() throws InterruptedException {
        sleep(420000);
        httpResponse rs = loggedUserReceiveCoupon.loggedUserReceiveCoupon_StockID(Env, facadePromoHost,"MPMSTORE", personNumber, activityInfo.get("StockID"));
        int rsCode = rs.getCode();
        String titleData = rs.getBody(JSONObject.class).getString("title");
        assertEquals(rsCode, 400, "会员领券失败返回的code信息不对");
        assertEquals(titleData, "批次无效或暂未开始", "活动过期，会员领券返回的title信息不对");
    }

    // job 未开启，需要通知开发设置
//    @Test(dependsOnMethods = {"test021_loggedUserReceiveCoupon"}, enabled = true)
    public void test022_queryUserCoupon() throws InterruptedException {
        JSONArray couponList;
        httpResponse rs = queryUserCoupon.queryUserCoupon(Env, facadePromoHost, "MPMSTORE", "ALL", "ALL", 1, 5,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "查询用户领券接口返回报错");
        couponList = queryUserCoupon.couponList(rs, Env, facadePromoHost, "MPMSTORE",personNumber,"ALL");
        boolean couponCodeStatus = crossFunctions.couponCodeValidation(couponList, activityInfo.get("StockID"), couponCodeByStockID,"EXPIRED");
        assertEquals(couponCodeStatus, true, "活动过期，领券后优惠券状态异常，期望结果是EXPIRED");
    }


    @Test(dependsOnMethods = {"test021_loggedUserReceiveCoupon"}, enabled = true)
    public void test023_userAvailableCoupon() {
        httpResponse rs = userAvailableCoupon.userAvailableCoupon(Env, facadePromoHost, "MPMSTORE", bodyNumber,personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "我的优惠券接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponNotEffective = crossFunctions.couponNotEffective(rsData, activityInfo.get("StockID"));
        assertEquals(couponNotEffective, true, "活动过期，活动在PDP页面接口展示活动数据，期望结果不展示");
    }

//    @AfterSuite
    public void loginOut() {
        login.loginOut();
    }
}
