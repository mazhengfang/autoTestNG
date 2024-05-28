package EasyPromo;

import APITestCase.BackOffice.V2.ActivitiesDetail;
import APITestCase.BackOffice.V2.AuditTask;
import APITestCase.BackOffice.V2.DB.Table;
import APITestCase.BackOffice.V2.OnlineMerchantCoupon;
import APITestCase.BackOffice.V2.ConfigFunc;
import APITestCase.EasyPromo.*;
import APITestCase.FacadePromo.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.dbConnect.mySQL;
import com.http.httpResponse;
import com.report.severityType;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import xProject.testCase.testCaseTemplateOne;

import java.util.List;
import com.excel.WriteExcel;
import static com.alibaba.fastjson.JSON.parseObject;
import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;

@Slf4j
public class OnlineCoupon_DistributeChanneLH5_UseChannelMPM {
    private com.excel.WriteExcel writeExl;
    private String rhKey ="online_merchant_coupon";
    private String rhPath ="src/main/resources/ConfigInfo/BackOffice/createOnlineMerchantCoupon/requestHeader.json";
    private String ReqByKey= "createDistChlH5_PPage-UseMPM";
    private String managerAuditReqByKey= "manager_audit";
    private String BUAuditReqByKey="BU_audit";
    private String ReqBodyPath ="src/main/resources/ConfigInfo/BackOffice/createOnlineMerchantCoupon/requestBody.json";
    public String activityID;
    public String flowID;
    public String stockID;
    private String sql_ep_workflow_instance = "select * from ep_workflow_instance ewi where ewi.business_id =" ;
    private String ep_coupon_stock = "select * from ep_coupon_stock ea where ea.activity_id =" ;
    private String sql_ep_workflow_instance_audit_item = "select * from ep_workflow_instance_audit_item ewiai where node = \"MANAGER_AUDIT\" and profile_id = \"ZMA23\" and ewiai.flow_id = ";
    private String sql_ep_workflow_instance_audit_item_BUAudit = "select * from ep_workflow_instance_audit_item ewiai where node = \"BU_AUDIT\" and profile_id = \"ZMA23\" and ewiai.flow_id = ";
    public ConfigFunc config;
    private OnlineMerchantCoupon onlineMerchantCoupon;
    private ActivitiesDetail activitiesDetail;
    public AuditTask auditTask;
    public Table table;
    public severityType styType = severityType.High;
    APITestCase.FacadePromo.queryUserCoupon queryUserCoupon;
    APITestCase.FacadePromo.crossFunctions crossFunctions;
    APITestCase.FacadePromo.userAvailableCoupon userAvailableCoupon;
    loggedUserReceiveCoupon loggedUserReceiveCoupon;
    APITestCase.EasyPromo.plans plans;
    APITestCase.EasyPromo.repricing repricing;
    APITestCase.EasyPromo.applyPromotionPlans applyPromotionPlans;
    APITestCase.EasyPromo.promotionDetails promotionDetails;
    APITestCase.EasyPromo.appendPromotion appendPromotion;
    APITestCase.EasyPromo.productEstimatePrices productEstimatePrices;
    APITestCase.EasyPromo.cartEstimatePrices cartEstimatePrices;
    private String couponCodeByStockID;
    private String bodyNumber = "1";
    private String plansExpectData = "src/main/resources/ConfigInfo/EasyPromo/plansResponse.json";
    private String plansPPKey = "plans_ResponseData_PromotionPlan_";
    private String planIndex;
    private String mirrorCode;
    private String repricingPromotionDetailExpectData = "src/main/resources/ConfigInfo/EasyPromo/repricingResponse.json";
    private String repricingPromotionDetailKey = "repricing_ResponseData_PromotionDetail_";
    private String orderID;
    private String couponSQL1 ="select * from coupon where code =\"";
    private String couponSQL2 ="\" and person_id =\"15005083203\"";
    private String PromotionDetailsExpectData = "src/main/resources/ConfigInfo/EasyPromo/promotionDetailsResponse.json";
    private String promotionDetailsKey = "promotionDetails_ResponseData_";
    private String clientName = "MPMSTORE";

    private JSONObject actOrderPromo;
    private JSONObject expOrderPromo;
    private JSONArray actOrderItemPromos;
    private JSONArray expOrderItemPromos;
    private JSONArray actCouponUseDetails;
    private JSONArray expCouponUseDetails;

    testCaseTemplateOne tcOne;
    private String promoDetailsTCPath = "src/main/resources/ConfigInfo/TestCase/promotionDetailsResponseTestCaseData.json";
    private String promoDetailsTCKey = "orderPromo_ResponseData_";
    private String orderItemPromosTCKey = "orderItemPromos_ResponseData_";
    private String couponUseDetailsKey = "couponUseDetails_ResponseData_";
    com.dbConnect.mySQL mySQL;

    redeemCoupon redeemCoupon;
    refundCoupon refundCoupon;
    private String couponSQL3 ="select * from ep_activity ea where ea.id = ";

    activityPageAvailableCoupons h5;

    @Parameters({"ENV"})
    @Test
    public void init(String ENV) {
        config = new ConfigFunc();
        config.init(ENV);
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "测试数据准备";
        config.expRlt = "PP";
        config.actRlt = ENV;
        config.testData = ENV;
        queryUserCoupon = new queryUserCoupon();
        crossFunctions = new crossFunctions();
        userAvailableCoupon = new userAvailableCoupon();
        loggedUserReceiveCoupon = new loggedUserReceiveCoupon();
        plans = new plans();
        repricing = new repricing();
        applyPromotionPlans = new applyPromotionPlans();
        promotionDetails = new promotionDetails();
        appendPromotion = new appendPromotion();
        productEstimatePrices = new productEstimatePrices();
        tcOne = new testCaseTemplateOne();
        mySQL = new mySQL();
        redeemCoupon = new redeemCoupon();
        refundCoupon = new refundCoupon();
        h5 = new activityPageAvailableCoupons();
        writeExl = new WriteExcel();
        System.out.println(
                String.format("%-50s","Test Case")
                        + String.format("%-50s","Test Description")
                        + String.format("%-20s","Severity")
                        + String.format("%-20s","Step Result")
                        + String.format("%-20s","Test Data")
                        + String.format("%-50s","Expected Result")
                        + String.format("%-50s","Actual Result")
                        + String.format("%-30s","Execution Time")
        );
        config.testRltData= config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt, config.testData,config.testRltData);
    }


    @Test(dependsOnMethods = "init")
    public void TC001_createStock(){
        onlineMerchantCoupon = new OnlineMerchantCoupon();
        httpResponse createResponse = onlineMerchantCoupon.create(config.URL,config.authorization,rhKey,rhPath,ReqByKey,ReqBodyPath);
        String rpsCode = createResponse.getBody(JSONObject.class).getString("code");
        activityID = ((JSONObject)createResponse.getBody(JSONObject.class).getJSONObject("data")).getString("activity_id");
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<BackOffice>接口创建活动";
        config.expRlt = "200";
        config.actRlt = rpsCode;
        config.testData = activityID;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.testData,config.testRltData);
        assertEquals( config.actRlt, config.expRlt , "线上全场券创建失败 返回的信息是:" + createResponse.getBody(JSONObject.class).toString());
    }

    @Test(dependsOnMethods = "TC001_createStock")
    public void TC002_queryStockID() throws Exception {
        table = new Table();
        JSONObject qryResult = table.ep_coupon_stock(config.DB, config.DBUser,config.DBPassword,ep_coupon_stock + activityID);
        stockID = qryResult.getString("id");

        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<DB>查询活动Id";
        config.actRlt = stockID;
        config.testData = stockID;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType," ",config.actRlt,config.testData,config.testRltData);
    }

    @Test(dependsOnMethods = "TC002_queryStockID")
    public void TC003_getStockInitialStatus(){
        activitiesDetail = new ActivitiesDetail();
        httpResponse atyDtl = activitiesDetail.get(config.URL,config.authorization,rhKey,rhPath,activityID);
        String activitySts = ((JSONObject)atyDtl.getBody(JSONObject.class).getJSONObject("data")).getJSONObject("activity").getString("activity_status");

        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<BackOffice>校验ActivitiesDetail接口查询活动状态-CREATED";
        config.expRlt = "CREATED";
        config.actRlt = activitySts;
        config.testData = activitySts;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt, config.testData,config.testRltData);
        assertEquals( config.actRlt, config.expRlt, "线上全场券创建状态错误");
    }

    @Test(dependsOnMethods = "TC003_getStockInitialStatus")
    public void TC004_queryFlowID() throws Exception {
        table = new Table();
        JSONObject qryResult = table.ep_workflow_instance(config.DB, config.DBUser,config.DBPassword, sql_ep_workflow_instance + activityID);
        flowID = qryResult.getString("id");

        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<DB>查询待办任务Id";
        config.actRlt = flowID;
        config.testData = flowID;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType," ",config.actRlt,config.testData,config.testRltData);
    }

    @Test(dependsOnMethods = "TC004_queryFlowID")
    public void TC005_queryApproveStatus() throws Exception {
        table = new Table();
        JSONObject qryResult = table.ep_workflow_instance_audit_item(config.DB, config.DBUser,config.DBPassword, sql_ep_workflow_instance_audit_item + flowID);
        String approveRlt = qryResult.getString("approve_result");
        String approveSts = qryResult.getString("status");

        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<DB>校验未审核待办任务MANAGER_AUDIT的approve_result初始值";
        config.actRlt = approveRlt;
        config.expRlt = null;
        config.testData = "null";
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.testData,config.testRltData);
        assertEquals( config.actRlt, config.expRlt, "activityID/flowID " + activityID+"/"+ flowID + " approve_result/manager_audit 未审核校验失败");

        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<DB>校验未审核待办任务MANAGER_AUDIT的status初始值";
        config.expRlt = "1";
        config.actRlt = approveSts;
        config.testData = approveSts;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.testData,config.testRltData);
        assertEquals(config.actRlt, config.expRlt, "activityID/flowID " + activityID +"/"+ flowID  + " status/manager_audit 未审核校验失败");
    }

    @Test(dependsOnMethods = "TC005_queryApproveStatus")
    public void TC006_managerAuditTask(){
        auditTask = new AuditTask();
        httpResponse managerAuditRps = auditTask.approve(config.URL,config.authorization,rhKey,rhPath,flowID,managerAuditReqByKey,ReqBodyPath);
        String rpsCode = managerAuditRps.getBody(JSONObject.class).getString("code");

        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<BackOffice>校验Manger_Audit审核成功";
        config.expRlt = "200";
        config.actRlt = rpsCode;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.actRlt,config.testRltData);
        assertEquals( config.actRlt, config.expRlt, "线上全场券manager_audit失败 返回的信息是:" + managerAuditRps.getBody(JSONObject.class).toString());
    }

    @Test(dependsOnMethods = "TC006_managerAuditTask")
    public void TC007_queryManagerAuditApproveStatus() throws Exception {
        table = new Table();
        JSONObject qryMgtAuditResult = table.ep_workflow_instance_audit_item(config.DB, config.DBUser,config.DBPassword, sql_ep_workflow_instance_audit_item + flowID );
        String approveRlt = qryMgtAuditResult.getString("approve_result");
        String approveSts = qryMgtAuditResult.getString("status");
        String approveInt = qryMgtAuditResult.getString("introduce");
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<DB>校验Manger_Audit审核后的approve_result值";
        config.expRlt = "1";
        config.actRlt = approveRlt;
        config.testData = approveRlt;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.testData,config.testRltData);
        assertEquals(config.actRlt, config.expRlt, "activityID/flowID " + activityID+"/"+ flowID + " approve_result/manager_audit 审核校验失败");

        config.stepDescription = "<DB>校验Manger_Audit审核后的status值";
        config.expRlt = "2";
        config.actRlt = approveSts;
        config.testData = approveSts;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.testData,config.testRltData);
        assertEquals( config.actRlt, config.expRlt, "activityID/flowID " + activityID +"/"+ flowID  + " status/manager_audit 审核校验失败");

        config.stepDescription = "<DB>校验Manger_Audit审核后的introduce值";
        config.expRlt = "auto script approve for level 1";
        config.actRlt = approveInt;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt," ",config.testRltData);
        assertEquals( config.actRlt, config.expRlt, "activityID/flowID " + activityID +"/"+ flowID  + " introduce/manager_audit 审核校验失败");

        JSONObject qryBUAuditResult = table.ep_workflow_instance_audit_item(config.DB, config.DBUser,config.DBPassword, sql_ep_workflow_instance_audit_item_BUAudit + flowID );
        String approveBUAuditRlt = qryBUAuditResult.getString("approve_result");
        String approveBUAuditSts = qryBUAuditResult.getString("status");

        config.stepDescription = "<DB>校验BU_Audit的初始approve_result初始值";
        config.actRlt = approveBUAuditRlt;
        config.expRlt = null;
        config.testData = approveBUAuditRlt;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.testData,config.testRltData);
        assertEquals(config.actRlt, config.expRlt, "activityID/flowID " + activityID+"/"+ flowID + " approve_result/BU_audit 未审核校验失败");

        config.stepDescription = "<DB>校验BU_Audit的status初始值";
        config.expRlt = "1";
        config.actRlt = approveBUAuditSts;
        config.testData = approveBUAuditSts;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.testData,config.testRltData);
        assertEquals( config.actRlt, config.expRlt, "activityID/flowID " + activityID +"/"+ flowID  + " status/BU_audit 未审核校验失败");
    }

    @Test(dependsOnMethods = "TC007_queryManagerAuditApproveStatus")
    public void  TC008_BUAuditTask(){
        auditTask = new AuditTask();
        httpResponse buAuditRps = auditTask.approve(config.URL,config.authorization,rhKey,rhPath,flowID,BUAuditReqByKey,ReqBodyPath);
        String rpsCode = buAuditRps.getBody(JSONObject.class).getString("code");
        config.stepDescription = "<BackOffice>校验auditTask接口BU_Audit审核成功";
        config.expRlt = "200";
        config.actRlt = rpsCode;
        config.testData = rpsCode;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.testData,config.testRltData);
        assertEquals(config.actRlt, config.expRlt, "线上全场券BU_audit失败 返回的信息是:" + buAuditRps.getBody(JSONObject.class).toString());
    }

    @Test(dependsOnMethods = {"TC008_BUAuditTask"}, enabled = true)
    public void TC009_queryUserCoupon() {
        JSONArray couponList;
        httpResponse rs = queryUserCoupon.queryUserCoupon(config.env, config.facadeURL,clientName, "AVAILABLE", "ALL", 1, 5,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "查询用户优惠券接口返回报错");
        couponList = queryUserCoupon.couponList(rs, config.env,  config.facadeURL,clientName,config.personNumber,"AVAILABLE");
        boolean couponNotEffective = crossFunctions.couponNotEffective(couponList, stockID);
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验未生效活动在queryUserCoupon接口不展示";
        config.expRlt = "true";
        config.actRlt = String.valueOf(couponNotEffective);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt," ",config.testRltData);
        assertEquals(config.actRlt, config.expRlt, "未生效活动在我的优惠券接口展示活动数据，期望结果不展示");
    }

    @Test(dependsOnMethods = {"TC009_queryUserCoupon"}, enabled = true)
    public void TC010_userAvailableCoupon() {
        httpResponse rs = userAvailableCoupon.userAvailableCoupon(config.env, config.facadeURL,clientName, bodyNumber,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "指定商品用户拥有的优惠券接口返回报错");
        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponNotEffective = crossFunctions.couponNotEffective(rsData, stockID);
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验未生效活动在userAvailableCoupon接口不展示";
        config.expRlt = "true";
        config.actRlt = String.valueOf(couponNotEffective);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt," ",config.testRltData);
        assertEquals(config.actRlt, config.expRlt, "未生效活动在PDP页面接口展示活动数据，期望结果不展示");
    }

    @Test(dependsOnMethods = {"TC009_queryUserCoupon"}, enabled = true)
    public void TC033_H5DistributeChannel() {
        httpResponse rs = h5.distributeChannelSearch(config.env,config.facadeURL,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "H5渠道查询接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponNotEffective = true;
        if(rsData.size() != 0){
            couponNotEffective = crossFunctions.couponNotEffective(rsData, stockID);
        }
       
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验未生效活动在activityPageAvailableCoupons接口不展示";
        config.expRlt = "true";
        config.actRlt = String.valueOf(couponNotEffective);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt," ",config.testRltData);
        assertEquals(config.actRlt,config.expRlt, "未生效活动H5投放渠道展示活动数据，期望结果不展示");
    }


    @Test(dependsOnMethods = {"TC033_H5DistributeChannel"}, enabled = true)
    public void TC011_loggedUserReceiveCoupon() throws InterruptedException {
        httpResponse rs = loggedUserReceiveCoupon.loggedUserReceiveCoupon_StockID(config.env,  config.facadeURL,clientName, config.personNumber, stockID);
        int rsCode = rs.getCode();
        String titleInfo = rs.getBody(JSONObject.class).getString("title");
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验未生效活动在loggedUserReceiveCoupon接口领券失败";
        config.expRlt = "批次无效或暂未开始";
        config.actRlt = titleInfo;
        config.testData = titleInfo;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.testData,config.testRltData);

        assertEquals(rsCode, 400, "会员领券返回code异常");
        assertEquals(config.actRlt,config.expRlt, "会员领券返回title异常");
    }

    @Test(dependsOnMethods = "TC011_loggedUserReceiveCoupon")
    public void TC012_getStockApproveStatus() throws InterruptedException {
        sleep(60000);
        activitiesDetail = new ActivitiesDetail();
        httpResponse atyDtl = activitiesDetail.get(config.URL,config.authorization,rhKey,rhPath,activityID);
        String activitySts = ((JSONObject)atyDtl.getBody(JSONObject.class).getJSONObject("data")).getJSONObject("activity").getString("activity_status");
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<BackOffice>校验线上全场券状态-已审核";
        config.expRlt = "APPROVED";
        config.actRlt = activitySts;
        config.testData = activitySts;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.testData,config.testRltData);
        assertEquals(config.actRlt, config.expRlt, "线上全场券已审核状态错误");
    }

    @Test(dependsOnMethods = "TC012_getStockApproveStatus")
    public void TC013_queryBUAuditApproveStatus() throws Exception {
        table = new Table();
        JSONObject qryBUAuditResult = table.ep_workflow_instance_audit_item(config.DB, config.DBUser,config.DBPassword, sql_ep_workflow_instance_audit_item_BUAudit + flowID );
        String approveBUAuditRlt = qryBUAuditResult.getString("approve_result");
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<DB>校验BU待办任务的approve_result值";
        config.expRlt = "1";
        config.actRlt = approveBUAuditRlt;
        config.testData = approveBUAuditRlt;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.testData,config.testRltData);
        assertEquals(config.actRlt, config.expRlt , "activityID/flowID " + activityID+"/"+ flowID + " approve_result/BU_audit 审核校验失败");

        String approveBUAuditSts = qryBUAuditResult.getString("status");
        config.stepDescription = "<DB>校验BU待办任务的status值";
        config.expRlt = "2";
        config.actRlt = approveBUAuditSts;
        config.testData = approveBUAuditSts;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.testData,config.testRltData);
        assertEquals( config.actRlt,  config.expRlt, "activityID/flowID " + activityID +"/"+ flowID  + " status/BU_audit 审核校验失败");

        String approveBUAuditInt = qryBUAuditResult.getString("introduce");
        config.stepDescription = "<DB>校验BU待办任务的introduce值";
        config.expRlt = "auto script approve for level 2";
        config.actRlt = approveBUAuditInt;
        config.testData = approveBUAuditInt;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.testData,config.testRltData);
        assertEquals(config.actRlt,config.expRlt, "activityID/flowID " + activityID +"/"+ flowID  + " introduce/BU_audit 审核校验失败");
    }

    @Test(dependsOnMethods = "TC013_queryBUAuditApproveStatus")
    public void TC014_getStockActiveStatus() throws InterruptedException {
        sleep(180000);
        activitiesDetail = new ActivitiesDetail();
        httpResponse atyDtl = activitiesDetail.get(config.URL,config.authorization,rhKey,rhPath,activityID);
        String activitySts = ((JSONObject)atyDtl.getBody(JSONObject.class).getJSONObject("data")).getJSONObject("activity").getString("activity_status");
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<BackOffice>校验全场券状态-生效中";
        config.expRlt = "ACTIVE";
        config.actRlt = activitySts;
        config.testData = activitySts;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.testData,config.testRltData);
        assertEquals(config.actRlt, config.expRlt, "线上全场券创建状态错误");
    }

    @Test(dependsOnMethods = "TC014_getStockActiveStatus")
    public void test015_queryUserCoupon() throws InterruptedException {
        JSONArray couponList;
        httpResponse rs = queryUserCoupon.queryUserCoupon(config.env, config.facadeURL,clientName, "AVAILABLE", "ALL", 1, 5,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "查询我的优惠券接口返回报错");

        couponList = queryUserCoupon.couponList(rs, config.env, config.facadeURL,clientName,config.personNumber,"AVAILABLE");
        boolean couponNotEffective = crossFunctions.couponEffective(couponList, stockID);

        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验生效活动在queryUserCoupon接口不展示";
        config.expRlt = String.valueOf(false);
        config.actRlt = String.valueOf(couponNotEffective);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt," ",config.testRltData);
        assertEquals(config.actRlt, config.expRlt, "生效活动在我的优惠券接口展示活动数据，期望结果不展示");
    }

    @Test(dependsOnMethods = {"test015_queryUserCoupon"}, enabled = true)
    public void test016_userAvailableCoupon() throws InterruptedException {
        sleep(10000);
        httpResponse rs = userAvailableCoupon.userAvailableCoupon(config.env,config.facadeURL,clientName, bodyNumber,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "指定商品用户拥有的优惠券接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponNotEffective = crossFunctions.couponEffective(rsData, stockID);
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验生效活动在userAvailableCoupon不展示";
        config.expRlt = String.valueOf(false);
        config.actRlt = String.valueOf(couponNotEffective);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt," ",config.testRltData);
        assertEquals(config.actRlt, config.expRlt , "生效活动在PDP页面接口展示活动数据，期望结果不展示");
    }

    @Test(dependsOnMethods = {"test016_userAvailableCoupon"}, enabled = true)
    public void TC034_H5DistributeChannel() {
        httpResponse rs = h5.distributeChannelSearch(config.env,config.facadeURL,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "H5渠道查询接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponNotEffective = crossFunctions.couponEffective(rsData, stockID);

        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验生效活动在H5activityPageAvailableCoupons接口展示";
        config.expRlt = "true";
        config.actRlt = String.valueOf(couponNotEffective);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt," ",config.testRltData);
        assertEquals( config.actRlt, config.expRlt, "生效活动H5投放渠道未展示活动数据，期望结果展示");

        String actCouponStatus = crossFunctions.couponStatus(rsData, stockID);
        config.stepDescription = "<Facade>校验H5生效活动券初始状态-UNCLAIMED";
        config.expRlt = "UNCLAIMED";
        config.actRlt = actCouponStatus;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.actRlt,config.testRltData);
        assertEquals( config.actRlt, config.expRlt, "<Facade>校验H5生效活动券初始状态-UNCLAIMED");
    }

    @Test(dependsOnMethods = {"TC034_H5DistributeChannel"}, enabled = true)
    public void test017_loggedUserReceiveCoupon() throws InterruptedException {
        httpResponse rs = loggedUserReceiveCoupon.loggedUserReceiveCoupon_StockID(config.env, config.facadeURL,clientName, config.personNumber, stockID);
        int rsCode = rs.getCode();
        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        couponCodeByStockID = ((JSONObject) rsData.getJSONObject(0)).getString("coupon_code");
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验loggedUserReceiveCoupon接口领券成功";
        config.expRlt = "201";
        config.actRlt = String.valueOf(rsCode);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,couponCodeByStockID,config.testRltData);
        assertEquals(config.actRlt , config.expRlt, "会员领券失败");
    }

    @Test(dependsOnMethods = {"test017_loggedUserReceiveCoupon"}, enabled = true)
    public void test018_queryUserCoupon() throws InterruptedException {
        JSONArray couponList;
        httpResponse rs = queryUserCoupon.queryUserCoupon(config.env, config.facadeURL,clientName, "AVAILABLE", "ALL", 1, 5,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "查询用户领券接口返回报错");
        couponList = queryUserCoupon.couponList(rs, config.env, config.facadeURL,clientName,config.personNumber,"AVAILABLE");
        boolean couponCodeStatus = crossFunctions.couponCodeValidation(couponList, stockID, couponCodeByStockID,"AVAILABLE");

        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验queryUserCoupon接口券状态-AVAILABLE";
        config.expRlt = String.valueOf(true);
        config.actRlt = String.valueOf(couponCodeStatus);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,"AVAILABLE",config.testRltData);
        assertEquals(config.actRlt, config.expRlt , "领券后优惠券状态异常，期望结果是AVAILABLE");
    }

    @Test(dependsOnMethods = {"test018_queryUserCoupon"}, enabled = true)
    public void test019_userAvailableCoupon() throws InterruptedException {
        sleep(10000);
        httpResponse rs = userAvailableCoupon.userAvailableCoupon(config.env,config.facadeURL,clientName, bodyNumber,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "指定商品用户拥有的优惠券接口返回报错");
        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponCodeStatus = crossFunctions.couponCodeValidation(rsData, stockID, couponCodeByStockID,"AVAILABLE");

        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验userAvailableCoupon接口券状态-AVAILABLE";
        config.expRlt = String.valueOf(true);
        config.actRlt = String.valueOf(couponCodeStatus);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,"AVAILABLE",config.testRltData);
        assertEquals( config.actRlt, config.expRlt, "领券后优惠券状态异常，期望结果是AVAILABLE");
    }

    @Test(dependsOnMethods = {"test019_userAvailableCoupon"}, enabled = true)
    public void TC035_H5DistributeChannel() {
        httpResponse rs = h5.distributeChannelSearch(config.env,config.facadeURL,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "H5渠道查询接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        String actCouponStatus = crossFunctions.couponStatus(rsData, stockID);
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验H5生效活动券状态-AVAILABLE";
        config.expRlt = "AVAILABLE";
        config.actRlt = actCouponStatus;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.actRlt,config.testRltData);
        assertEquals( config.actRlt ,  config.expRlt, "<Facade>校验H5生效活动券状态-AVAILABLE");

    }

    @Test(dependsOnMethods = {"TC035_H5DistributeChannel"}, enabled = true)
    public void test020_plans() throws InterruptedException {
        httpResponse rs = plans.plans(config.env, config.URL, bodyNumber,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "Plans接口返回报错");

        JSONObject rsData = rs.getBody(JSONObject.class).getJSONObject("data");
//        JSONArray couponsList = rsData.getJSONArray("coupons");
        JSONArray promotionPlansList = rsData.getJSONArray("promotion_plans");
        JSONObject expPromotionPlans = parseObject(commonFunction.readJsonFile(plansExpectData)).getJSONObject(plansPPKey + bodyNumber);
        boolean promotionResult = plans.promotionPlansValidation(promotionPlansList, stockID, expPromotionPlans);
        planIndex = plans.planIndex;

        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<EP>校验plans接口返回商品促销";
        config.expRlt = String.valueOf(true);
        config.actRlt = String.valueOf(promotionResult);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,planIndex,config.testRltData);
        assertEquals( config.actRlt, config.expRlt, "商品促销优惠信息校验出错");
    }

    @Test(dependsOnMethods = {"test020_plans"}, enabled = true)
    public void test021_repricing() throws InterruptedException {
        httpResponse rs = repricing.repricing(config.URL, planIndex, bodyNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "校验Repricing接口返回报错");

        JSONObject rsData = rs.getBody(JSONObject.class).getJSONObject("data");
        mirrorCode = rsData.getString("mirror_code");
        JSONObject promotionDetail = rsData.getJSONObject("promotion_detail");
        JSONObject expPromotionDetail = parseObject(commonFunction.readJsonFile(repricingPromotionDetailExpectData)).getJSONObject(repricingPromotionDetailKey + bodyNumber);
        boolean promotionDetailValidationResult = repricing.promotionDetailValidation(promotionDetail, expPromotionDetail, couponCodeByStockID, stockID);
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<EP>校验Repricing接口返回优惠";
        config.expRlt = String.valueOf(true);
        config.actRlt = String.valueOf(promotionDetailValidationResult);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt," ",config.testRltData);
        assertEquals(config.actRlt , config.expRlt , "Repricing优惠信息分摊计算错误");
    }

    @Test(dependsOnMethods = {"test021_repricing"}, enabled = true)
    public void test022_applyPromotionPlans() throws Exception {
        httpResponse rs = applyPromotionPlans.applyPromotionPlans(config.URL, mirrorCode, bodyNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "Apply Promotion Plan接口返回报错");
        orderID = applyPromotionPlans.orderID;
        table = new Table();
        JSONObject qryResult = table.coupon(config.DB, config.DBUser,config.DBPassword, couponSQL1+couponCodeByStockID+couponSQL2);
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<DB>校验applyPromotionPlans后优惠券状态-FROZEN";
        config.expRlt = "FROZEN";
        config.actRlt = qryResult.getString("status");
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,orderID ,config.testRltData);
        assertEquals(config.actRlt , config.expRlt, "优惠券状态错误");
    }

    @Test(dependsOnMethods = {"test022_applyPromotionPlans"}, enabled = true)
    public void test023_queryUserCoupon() throws InterruptedException {
        JSONArray couponList;
        httpResponse rs = queryUserCoupon.queryUserCoupon( config.env, config.facadeURL,clientName, "USED", "ALL", 1, 5,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "查询用户领券接口返回报错");
        couponList = queryUserCoupon.couponList(rs, config.env, config.facadeURL,clientName, config.personNumber,"USED");
        boolean couponCodeStatus = crossFunctions.couponCodeValidation(couponList, stockID, couponCodeByStockID,"USED");
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验queryUserCoupon接口优惠券状态-USED";
        config.expRlt = String.valueOf(true);
        config.actRlt = String.valueOf(couponCodeStatus);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt, "USED" ,config.testRltData);
        assertEquals( config.actRlt, config.expRlt, "冻券后优惠券状态异常，期望结果是USED");
    }

    @Test(dependsOnMethods = {"test023_queryUserCoupon"}, enabled = true)
    public void test024_userAvailableCoupon() throws InterruptedException {
        httpResponse rs = userAvailableCoupon.userAvailableCoupon( config.env,config.facadeURL,clientName, bodyNumber, config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "指定商品用户拥有的优惠券接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponNotEffective = crossFunctions.couponEffective(rsData, stockID);
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验生效活动未领券在userAvailableCoupon不展示";
        config.expRlt = String.valueOf(false);
        config.actRlt = String.valueOf(couponNotEffective);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt," ",config.testRltData);
        assertEquals(config.actRlt, config.expRlt , "<Facade>校验生效活动未领券在userAvailableCoupon不展示");
    }

    @Test(dependsOnMethods = {"test024_userAvailableCoupon"}, enabled = true)
    public void TC036_H5DistributeChannel() {
        httpResponse rs = h5.distributeChannelSearch(config.env,config.facadeURL,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "H5渠道查询接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        String actCouponStatus = crossFunctions.couponStatus(rsData, stockID);
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验H5生效活动券冻结后状态-UNCLAIMED";
        config.expRlt = "UNCLAIMED";
        config.actRlt = actCouponStatus;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.actRlt,config.testRltData);
        assertEquals( config.actRlt ,  config.expRlt, "<Facade>校验H5生效活动券冻结后状态-UNCLAIMED");

    }

    @Test(dependsOnMethods = {"test024_userAvailableCoupon"}, enabled = true)
    public void test025_promotionDetails_001() throws InterruptedException {
        httpResponse rs = promotionDetails.promotionDetails(config.URL, orderID, bodyNumber);
        int rsCode = rs.getCode();
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<EP>校验promotionDetails接口状态";
        config.expRlt = String.valueOf(200);
        config.actRlt = String.valueOf(rsCode);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt, config.actRlt ,config.testRltData);
        assertEquals(config.actRlt, config.expRlt, "Promotion Details接口返回报错");

        JSONObject rsData = rs.getBody(JSONObject.class).getJSONObject("data");
        JSONObject expPromotionDetailsData = parseObject(commonFunction.readJsonFile(PromotionDetailsExpectData)).getJSONObject(promotionDetailsKey + bodyNumber);
        actOrderPromo = rsData.getJSONObject("order_promo");
        expOrderPromo = expPromotionDetailsData.getJSONObject("order_promo");

        actOrderItemPromos = rsData.getJSONArray("order_item_promos");
        expOrderItemPromos = expPromotionDetailsData.getJSONArray("order_item_promos");

        actCouponUseDetails = rsData.getJSONArray("coupon_use_details");
        expCouponUseDetails = expPromotionDetailsData.getJSONArray("coupon_use_details");
    }

    @Test(dataProvider = "test025_promotionDetails_002", dependsOnMethods = {"test025_promotionDetails_001"}, enabled = true)
    public void test025_promotionDetails_002(testCaseTemplateOne tcOne) {
        String sKey = tcOne.getKey();
        String actData = actOrderPromo.getString(sKey);
        String expData = expOrderPromo.getString(sKey);
//        System.out.println("Key2 " + sKey + ":" + actData + ":" + expData);
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<EP>校验OrderPromotion中"+ sKey+"的值";
        config.expRlt = actData;
        config.actRlt = expData;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt, config.actRlt,config.testRltData);
        assertEquals(config.actRlt,  config.expRlt, sKey + " value is not correct");
    }

    @DataProvider(name = "test025_promotionDetails_002")
    private Object[][] test018_promotionDetails_002_TestData() {
        JSONArray json = tcOne.getCaseByPath(promoDetailsTCPath, "$." + promoDetailsTCKey + bodyNumber);
        List<testCaseTemplateOne> cases = json.toJavaList(testCaseTemplateOne.class);
        final Object[][] objects = new Object[cases.size()][];
        for (int i = 0; i < cases.size(); i++) {
            objects[i] = new Object[]{cases.get(i)};
        }
        return objects;
    }


    @Test(dataProvider = "test025_promotionDetails_003", dependsOnMethods = {"test025_promotionDetails_002"}, enabled = true)
    public void test025_promotionDetails_003(testCaseTemplateOne tcOne) {
        String sKey = tcOne.getKey();
        for (Object actData : actOrderItemPromos) {
            String actSkuID = ((JSONObject) actData).getString("skuId");
            for (Object expData : expOrderItemPromos) {
                String expSkuID = ((JSONObject) expData).getString("skuId");
                if (actSkuID.equals(expSkuID)) {
                    String actItemData = ((JSONObject) actData).getString(sKey);
                    String expItemData = ((JSONObject) expData).getString(sKey);
//                    System.out.println("Key3 " + sKey + ":" + actItemData + ":" + expItemData);
                    config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
                    config.stepDescription = "<EP>校验OrderItemPromotion中"+ sKey+"的值";
                    config.expRlt = expItemData;
                    config.actRlt = actItemData;
                    config.testRltData= config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt, config.actRlt,config.testRltData);
                    assertEquals(config.actRlt, config.expRlt, actSkuID + "对应的" + sKey + " value is not correct");
                }
            }
        }
    }

    @DataProvider(name = "test025_promotionDetails_003")
    private Object[][] test025_promotionDetails_003_TestData() {
        JSONArray json = tcOne.getCaseByPath(promoDetailsTCPath, "$." + orderItemPromosTCKey + bodyNumber);
        List<testCaseTemplateOne> cases = json.toJavaList(testCaseTemplateOne.class);
        final Object[][] objects = new Object[cases.size()][];
        for (int i = 0; i < cases.size(); i++) {
            objects[i] = new Object[]{cases.get(i)};
        }
        return objects;
    }

    @Test(dataProvider = "test025_promotionDetails_004", dependsOnMethods = {"test025_promotionDetails_003"}, enabled = true)
    public void test025_promotionDetails_004(testCaseTemplateOne tcOne) {
        String sKey = tcOne.getKey();
        for (Object actData : actCouponUseDetails) {
            String actCode = ((JSONObject) actData).getString("code");
            for (Object expData : expCouponUseDetails) {
//                String expCode = ((JSONObject) expData).getString("code");
                if (actCode.equals(couponCodeByStockID)) {
                    String actItemData = ((JSONObject) actData).getString(sKey);
                    String expItemData = ((JSONObject) expData).getString(sKey);
//                    System.out.println("Key4 " + sKey + ":" + actItemData + ":" + expItemData);
                    config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
                    config.stepDescription = "<EP>校验CouponUseDetails中"+ sKey+"的值";
                    config.expRlt = expItemData;
                    config.actRlt = actItemData;
                    config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt, config.actRlt,config.testRltData);
                    assertEquals( config.actRlt, config.expRlt, actCode + "对应的" + sKey + " value is not correct");
                }
            }
        }
    }

    @DataProvider(name = "test025_promotionDetails_004")
    private Object[][] test025_promotionDetails_004_TestData() {
        JSONArray json = tcOne.getCaseByPath(promoDetailsTCPath, "$." + couponUseDetailsKey + bodyNumber);
        List<testCaseTemplateOne> cases = json.toJavaList(testCaseTemplateOne.class);
        final Object[][] objects = new Object[cases.size()][];
        for (int i = 0; i < cases.size(); i++) {
            objects[i] = new Object[]{cases.get(i)};
        }
        return objects;
    }

    @Test(dependsOnMethods = {"test025_promotionDetails_004"}, enabled = true)
    public void test026_redeemCoupon() throws Exception {
        httpResponse rs = redeemCoupon.redeemCoupon(config.URL, orderID, bodyNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "Redeem Coupon接口返回报错");

        table = new Table();
        JSONObject qryResult = table.coupon(config.DB, config.DBUser,config.DBPassword, couponSQL1+couponCodeByStockID+couponSQL2);
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<DB>校验redeemCoupon后,券状态-USED";
        config.expRlt = "USED";
        config.actRlt = qryResult.getString("status");
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt, config.actRlt,config.testRltData);
        assertEquals( config.actRlt,config.expRlt , "Redeem Coupon接口返回报错");
    }

    @Test(dependsOnMethods = {"test026_redeemCoupon"}, enabled = true)
    public void test027_refundCoupon() throws Exception {
        httpResponse rs = refundCoupon.refundCoupon(config.URL, orderID, bodyNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "Refund Coupon接口返回报错");

        table = new Table();
        JSONObject qryResult = table.coupon(config.DB, config.DBUser,config.DBPassword, couponSQL1+couponCodeByStockID+couponSQL2);
        config.stepDescription = "<DB>校验refundCoupon后,券状态-EXCHANGED";
        config.expRlt = "EXCHANGED";
        config.actRlt = qryResult.getString("status");
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt, config.actRlt,config.testRltData);
        assertEquals(config.actRlt, config.expRlt, "Refund Coupon接口返回报错");

    }

    @Test(dependsOnMethods = {"test027_refundCoupon"}, enabled = true)
    public void test028_queryUserCoupon() throws InterruptedException {
        JSONArray couponList;
        httpResponse rs = queryUserCoupon.queryUserCoupon(config.env, config.facadeURL,clientName, "AVAILABLE", "ALL", 1, 5,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "查询用户领券接口返回报错");
        couponList = queryUserCoupon.couponList(rs, config.env, config.facadeURL,clientName,config.personNumber,"AVAILABLE");
        boolean couponCodeStatus = crossFunctions.couponCodeValidation(couponList, stockID, couponCodeByStockID,"AVAILABLE");

        config.stepDescription = "<Facade>校验queryUserCoupon接口返回退券的状态-AVAILABLE";
        config.expRlt = String.valueOf(true);
        config.actRlt = String.valueOf(couponCodeStatus);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt, config.actRlt,config.testRltData);
        assertEquals( config.actRlt,  config.expRlt, "退券后优惠券状态异常，期望结果是AVAILABLE");
    }


    @Test(dependsOnMethods = {"test028_queryUserCoupon"}, enabled = true)
    public void test029_userAvailableCoupon() throws InterruptedException {
        sleep(10000);
        httpResponse rs = userAvailableCoupon.userAvailableCoupon(config.env,config.facadeURL,clientName, bodyNumber,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "指定商品用户拥有的优惠券接口返回报错");
        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponCodeStatus = crossFunctions.couponCodeValidation(rsData, stockID, couponCodeByStockID,"AVAILABLE");

        config.stepDescription = "<Facade>校验userAvailableCoupon接口返回退券的状态-AVAILABLE";
        config.expRlt = String.valueOf(true);
        config.actRlt = String.valueOf(couponCodeStatus);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt, config.actRlt,config.testRltData);
        assertEquals(config.actRlt, config.expRlt, "退券后优惠券状态异常，期望结果是AVAILABLE");
    }

    @Test(dependsOnMethods = {"test029_userAvailableCoupon"}, enabled = true)
    public void TC037_H5DistributeChannel() {
        httpResponse rs = h5.distributeChannelSearch(config.env,config.facadeURL,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "H5渠道查询接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        String actCouponStatus = crossFunctions.couponStatus(rsData, stockID);
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验H5退券状态-AVAILABLE";
        config.expRlt = "AVAILABLE";
        config.actRlt = actCouponStatus;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt,config.actRlt,config.testRltData);
        assertEquals( config.actRlt ,  config.expRlt, "<Facade>校验H5退券状态-AVAILABLE");

    }

    @Test(dependsOnMethods = {"TC037_H5DistributeChannel"}, enabled = true)
    public void test030_全场券活动关闭() throws Exception {
        httpResponse closeResponse = activitiesDetail.patch(config.URL,config.authorization,rhKey,rhPath,activityID);
        int rsCode = closeResponse.getCode();

        table = new Table();
        JSONObject qryResult = table.ep_activity(config.DB, config.DBUser,config.DBPassword,couponSQL3 + activityID);
        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<DB>校验活动关闭后状态-CLOSED";
        config.expRlt = "CLOSED";
        config.actRlt = qryResult.getString("status");
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt, config.actRlt,config.testRltData);
        assertEquals(rsCode, 200, "关闭接口,返回代码错误");
        assertEquals( config.actRlt,config.expRlt , "关闭接口,活动状态变更错误");
    }


    @Test(dependsOnMethods = {"test030_全场券活动关闭"}, enabled = true)
    public void test031_loggedUserReceiveCoupon() throws InterruptedException {
        httpResponse rs = loggedUserReceiveCoupon.loggedUserReceiveCoupon_StockID( config.env, config.facadeURL,clientName, config.personNumber, stockID);
        int rsCode = rs.getCode();
        String titleData = rs.getBody(JSONObject.class).getString("title");
        config.stepDescription = "<Facade>校验活动关闭后 loggedUserReceiveCoupon接口领券失败";
        config.expRlt = "批次无效或暂未开始";
        config.actRlt = titleData;
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt, config.actRlt,config.testRltData);

        assertEquals(rsCode, 400, "会员领券失败返回的code信息不对");
        assertEquals(config.actRlt,config.expRlt,"活动关闭后，会员领券返回的title信息不对");
    }

    @Test(dependsOnMethods = {"test031_loggedUserReceiveCoupon"}, enabled = true)
    public void test032_userAvailableCoupon() {
        httpResponse rs = userAvailableCoupon.userAvailableCoupon( config.env,config.facadeURL,clientName,bodyNumber,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "指定商品用户拥有的优惠券接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponNotEffective = crossFunctions.couponNotEffective(rsData, stockID);
        config.stepDescription = "<Facade>活动关闭后userAvailableCoupon接口不展示活动数据";
        config.expRlt = String.valueOf(true);
        config.actRlt = String.valueOf(couponNotEffective);
        config.testRltData= config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt, config.actRlt,config.testRltData);
        assertEquals(config.actRlt, config.expRlt, "活动关闭后，活动在PDP页面接口展示活动数据，期望结果不展示");
    }


    @Test(dependsOnMethods = {"test032_userAvailableCoupon"}, enabled = true)
    public void TC038_H5DistributeChannel() {
        httpResponse rs = h5.distributeChannelSearch(config.env,config.facadeURL,config.personNumber);
        int rsCode = rs.getCode();
        assertEquals(rsCode, 200, "H5渠道查询接口返回报错");

        JSONArray rsData = rs.getBody(JSONObject.class).getJSONArray("data");
        boolean couponNotEffective = true;
        if(rsData.size() != 0){
            couponNotEffective = crossFunctions.couponNotEffective(rsData, stockID);
        }

        config.stepName = Thread.currentThread().getStackTrace()[1].getMethodName();
        config.stepDescription = "<Facade>校验未生效活动在userAvailableCoupon接口不展示";
        config.expRlt = "true";
        config.actRlt = String.valueOf(couponNotEffective);
        config.testRltData=config.rpt.testResultData(config.stepName,config.stepDescription,styType,config.expRlt,config.actRlt," ",config.testRltData);
        assertEquals( config.actRlt ,  config.expRlt, "未生效活动H5投放渠道展示活动数据，期望结果不展示");
    }

   @AfterTest
    public void report() {
        writeExl.writeExcel("Oln_H5_MPM ",config.testRltData,8,config.filePath);
    }


}
