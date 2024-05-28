package NewBackOffice;

import APITestCase.FacadePromo.crossFunctions;
import UITestCase.NewBackOffice.activityList;
import UITestCase.NewBackOffice.loginPage;
import UITestCase.NewBackOffice.menuBar;
import UITestCase.NewBackOffice.onlineActivityPage;
import UITestCase.JumpServer.Connection;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.http.httpResponse;
import com.ssh.App;
import com.ssh.jumpServer;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import APITestCase.FacadePromo.activityPageAvailableCoupons;
import APITestCase.FacadePromo.queryUserCoupon;
import APITestCase.BackOffice.V2.PromotionPage;

@Slf4j
public class test {
    UITestCase.NewBackOffice.loginPage login;
    UITestCase.NewBackOffice.activityList activityPageList;
    WebDriver driver;
    UITestCase.NewBackOffice.menuBar menuBar;
    UITestCase.NewBackOffice.onlineActivityPage onlineAtyPage;
    com.dbConnect.mySQL mySQL;
    App app;
    jumpServer js;
    Connection conn;
    activityPageAvailableCoupons h5;
    private String couponSQL1 = "select * from coupon where code =\"";
    private String couponSQL2 = "\" and person_id =\"15005083203\"";
    private String userName = "zma23";
    private String password = "Shanghai=2024";
    private String url;
    APITestCase.FacadePromo.crossFunctions crossFunctions;
    APITestCase.FacadePromo.queryUserCoupon queryUserCoupon;
    PromotionPage promotionPage;

    public String rhKey = "common";
    private String promotionPageHeaderConfigPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\RequestHeader.json";
    private String ReqBodyPath = "src\\main\\resources\\ConfigInfo\\BackOffice\\PromotionPageRequestBody.json";
    private String promotionPageRequestBodyKey = "PromotionPage_Create_TestBody_1";
    private String promotionInfoRequestBodyKey = "PromotionPage_Info_TestBody_1";

    @Parameters({"URL"})
    public void test1(String URL) throws InterruptedException {
        login = new loginPage();
        menuBar = new menuBar();
        activityPageList = new activityList();
        onlineAtyPage = new onlineActivityPage();
        crossFunctions = new crossFunctions();
        this.url = URL;
        driver = login.init(url);

        boolean success = login.signOn(driver, userName, password);
        assertEquals(success, true, "Backoffice登录失败");

        boolean newActivityActionResult = activityPageList.createOnlineActivity(driver);
        assertEquals(newActivityActionResult, true, "新建线上活动页面打开失败");
    }

    @Test
    public void test2() throws Exception {
        String transactionId = "2092832308476" ;
        int dbCt = Math.abs(transactionId.hashCode()) % 2;
        int tableCt = Math.abs(transactionId.hashCode()) % 3;
        System.out.println("DB: " + dbCt);
        System.out.println("Table: " + tableCt);
    }



    public void test() throws Exception {

        crossFunctions = new crossFunctions();
        promotionPage = new PromotionPage();
        String token ="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MTMxNDY5ODUsImV4cCI6MTcxMzE1NDE4NSwicHJvZmlsZV9pZCI6IlpNQTIzIiwibmFtZSI6IiJ9.vRMcgx0ZObKUshcBG2MUqqoEytJqrJ5alDxRzzXSrmM";
//        httpResponse hs = promotionPage.create("https://easypromo.pp.dktapp.cloud",token,rhKey,promotionPageHeaderConfigPath,"2404150021100002",promotionPageRequestBodyKey,ReqBodyPath);
//        JSONObject rsData = hs.getBody(JSONObject.class).getJSONObject("data");
//        String pageCode = rsData.getString("page_code");

        httpResponse rs = promotionPage.info("https://easypromo.pp.dktapp.cloud",token,rhKey,promotionPageHeaderConfigPath,promotionInfoRequestBodyKey,ReqBodyPath,"574B51BBCD");
        JSONObject rsData = rs.getBody(JSONObject.class).getJSONObject("data");
        JSONArray stocks =  (rsData.getJSONObject("promotion_info")).getJSONArray("stocks");

        boolean rlt = crossFunctions.couponEffective(stocks,"2404150021100002");
        String claimableStatus = promotionPage.claimableStatus(stocks,"2403040081100002");


        //        h5 = new activityPageAvailableCoupons();
//        httpResponse rs = h5.h("PP","https://facade-api.pp.dktapp.cloud","13916485978");
//        long currentTime = commonFunction.getCurrentTimeUnix();
//        System.out.println(currentTime);

//        int rsCode = rs.getCode();
//        assertEquals(rsCode, 200, "H5渠道查询接口返回报错");
//
//        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
//        boolean couponNotEffective = crossFunctions.couponEffective(rsData, "2404050061100002");
//        boolean couponEffective = crossFunctions.couponEffective(rsData, "2404020041100002");
//        String sts = crossFunctions.couponStatus(rsData, "2404020041100002");
    }

}
