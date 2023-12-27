package BackOffice;

import UITestCase.BackOffice.createActivity;
import UITestCase.BackOffice.login;
import UITestCase.BackOffice.newActivity;
import UITestCase.BackOffice.validateActivity;
import com.alibaba.fastjson.JSONArray;
import com.commonFunction;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import xProject.testCase.backOfficeTestCase;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import static org.testng.Assert.assertEquals;

public class NormalActivityStackingProduct_TestSet001 {
    private String tcPath = "src/main/resources/ConfigInfo/TestCase/backOfficeTestCaseData.json";
    private String tcKey = "NormalActivityStackingProduct";
    private HashMap<String, String> activityInfo;
    WebDriver driver;
    login login = new login();
    newActivity newActivity = new newActivity();
    createActivity createActivity = new createActivity();
    UITestCase.BackOffice.validateActivity validateActivity = new validateActivity();
    backOfficeTestCase tc = new backOfficeTestCase();

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
    public void test003_创建全场券() throws InterruptedException {
        boolean newCouponResult = createActivity.createNormalActivity(driver,"[Auto]全场券", "product",15,"105","5");
        assertEquals(newCouponResult, true, "BackOffice创建全场券失败");
    }

    //
    @Test(dependsOnMethods = {"test003_创建全场券"}, enabled = true)
    public void test004_全场券页面获取() throws InterruptedException {
        activityInfo = validateActivity.validateActivity(driver,"normal","product");
    }

    @Test(dependsOnMethods = {"test004_全场券页面获取"}, enabled = true)
    public void test005_校验全场券() {
        assertEquals(activityInfo.get("Activity Name"), createActivity.AtyName, "Activity name is not correct");

        assertEquals(commonFunction.toFormatDate(activityInfo.get("Distribute Time").substring(0, 10)), createActivity.StartDate, "distribute start date is not correct");
        assertEquals(commonFunction.toFormatDate(activityInfo.get("Distribute Time").substring(21, 32)), createActivity.EndDate, "distribute start time is not correct");
        assertEquals(activityInfo.get("Distribute Time").substring(11, 19), createActivity.StartTime, "distribute end date is not correct");
        assertEquals(activityInfo.get("Distribute Time").substring(33, 41), createActivity.EndTime, "distribute end time is not correct");

        assertEquals(commonFunction.toFormatDate(activityInfo.get("Effective Time").substring(0, 10)), createActivity.StartDate, "Effective start date is not correct");
        assertEquals(activityInfo.get("Effective Time").substring(11, 19), createActivity.StartTime, "Effective Start Time  is not correct");
        assertEquals(commonFunction.toFormatDate(activityInfo.get("Effective Time").substring(21, 32)), createActivity.EndDate, "Effective End Time  is not correct");
        assertEquals(activityInfo.get("Effective Time").substring(33, 41), createActivity.EndTime, "Effective End Time  is not correct");

    }

    @Test(dataProvider = "test005_校验全场券", dependsOnMethods = {"test004_全场券页面获取"}, enabled = true)
    public void test006_校验全场券(backOfficeTestCase tc) {
        String sKey = tc.getKey();
        String sValue = tc.getValue();
        String actualValue = activityInfo.get(sKey);
        assertEquals(actualValue, sValue, sKey + " value is not correct");
    }

    @DataProvider(name = "test005_校验全场券")
    private Object[][] test005_校验全场券_TestData() {
        JSONArray json = tc.getCaseByPath(tcPath, "$." + tcKey);
        List<backOfficeTestCase> cases = json.toJavaList(backOfficeTestCase.class);
        final Object[][] objects = new Object[cases.size()][];
        for (int i = 0; i < cases.size(); i++) {
            objects[i] = new Object[]{cases.get(i)};
        }
        return objects;
    }

    @AfterSuite
    public void loginOut() {
        login.loginOut();
    }


}
