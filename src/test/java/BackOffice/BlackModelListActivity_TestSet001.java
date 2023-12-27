package BackOffice;

import UITestCase.BackOffice.createActivity;
import UITestCase.BackOffice.login;
import UITestCase.BackOffice.newActivity;
import UITestCase.BackOffice.validateActivity;
import com.alibaba.fastjson.JSONArray;
import com.commonFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import xProject.testCase.backOfficeTestCase;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class BlackModelListActivity_TestSet001 {
    private String tcPath = "src/main/resources/ConfigInfo/TestCase/backOfficeTestCaseData.json";
    private String tcKey = "BlackModelListNormalActivity";
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
    public void test003_创建黑名单全场券() throws InterruptedException {
        boolean newCouponResult = createActivity.createBlackModelListNormalActivity(driver,15,"105","5");
        assertEquals(newCouponResult, true, "BackOffice创建黑名单活动失败");
    }

    //
    @Test(dependsOnMethods = {"test003_创建黑名单全场券"}, enabled = true)
    public void test004_黑名单全场券页面获取() throws InterruptedException {
        activityInfo = validateActivity.validateActivity(driver,"normal","noStack");
        String notApplicableGoodTypePath = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[8]/div[1]/div/p";
        WebElement notApplicableGoodType = driver.findElement(By.xpath(notApplicableGoodTypePath));
        String notApplicableGood = notApplicableGoodType.getText();
        activityInfo.put("Not Applicable Good Type", notApplicableGood);

        String notApplicableGoodIDPath = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[8]/div[2]/div[3]/table/tbody/tr[1]/td[1]/div";

        WebElement notApplicableGoodID8492960 = driver.findElement(By.xpath(notApplicableGoodIDPath));
        String ID8492960 = notApplicableGoodID8492960.getText();
        activityInfo.put("8492960", ID8492960);

        String notApplicableGoodID2Path = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[8]/div[2]/div[3]/table/tbody/tr[2]/td[1]/div";
        WebElement notApplicableGoodID8363990 = driver.findElement(By.xpath(notApplicableGoodID2Path));
        String ID8363990 = notApplicableGoodID8363990.getText();
        activityInfo.put("8363990", ID8363990);

        String notApplicableGoodID3Path = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[8]/div[2]/div[3]/table/tbody/tr[3]/td[1]/div";
        WebElement notApplicableGoodID8324625 = driver.findElement(By.xpath(notApplicableGoodID3Path));
        String ID8324625 = notApplicableGoodID8324625.getText();
        activityInfo.put("8324625", ID8324625);

    }

    @Test(dependsOnMethods = {"test004_黑名单全场券页面获取"}, enabled = true)
    public void test005_校验黑名单全场券() {
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

    @Test(dataProvider = "test005_校验黑名单全场券", dependsOnMethods = {"test004_黑名单全场券页面获取"}, enabled = true)
    public void test006_校验黑名单全场券(backOfficeTestCase tc) {
        String sKey = tc.getKey();
        String sValue = tc.getValue();
        String actualValue = activityInfo.get(sKey);
        assertEquals(actualValue, sValue, sKey + " value is not correct");
    }

    @DataProvider(name = "test005_校验黑名单全场券")
    private Object[][] test005_校验黑名单全场券_TestData() {
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
