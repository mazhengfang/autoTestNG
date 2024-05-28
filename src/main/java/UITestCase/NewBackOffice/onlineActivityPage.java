package UITestCase.NewBackOffice;

import com.commonFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import UITestCase.CommonFunction.elementOperation;
import UITestCase.CommonFunction.mouseSimulation;

import java.awt.*;
import java.util.List;

public class onlineActivityPage {
    elementOperation elementOpe = new elementOperation();
    mouseSimulation mouseSim= new mouseSimulation();

    public String activityNamePath = "//*[@id=\"couponCreateForm-activity_name\"]";
    public String AtyName = "Auto"+ commonFunction.getCurrentTimePlus5("yyyyMMddHHmm", 1)+"全场券";
    public String activityDiscountPricePath = "//*[@id=\"couponCreateForm-coupon_values0\"]";
    public String activityDiscountPriceValue = "5";
    public String consumptionThresholdPath ="//*[@id=\"couponCreateForm-coupon_conditions0\"]";
    public String consumptionThresholdValue = "105";
    public int endTimes = 15;
    public String startTimePath = "//*[@id=\"couponCreateForm-start_time_range\"]/div[2]/div[1]/input[1]";
    public String endTimePath = "//*[@id=\"couponCreateForm-start_time_range\"]/div[2]/div[1]/input[2]";
//    public String startDatePath = "//*[@placeholder=\"开始日期\"]";
//    public String startTimePath2 = "/html/body/div[2]/div[3]/div/div[1]/div/div[1]/span[1]/span[2]/div[1]/div/input";
//    public String endDatePath = "//*[@placeholder=\"结束日期\"]";
//    public String endTimePath = "/html/body/div[2]/div[3]/div/div[1]/div/div[1]/span[3]/span[2]/div[1]/div/input";
    public String StartDate = commonFunction.getCurrentTimePlus5("yyyy-MM-dd", 5);
    public String EndDate = commonFunction.getCurrentTimePlus5("yyyy-MM-dd", 5);
    public String StartTime = commonFunction.getCurrentTimePlus5("HH:mm:ss", 1);
    public String EndTime = commonFunction.getCurrentTimePlus5("HH:mm:ss", endTimes);
    public String startDateTime = StartDate + " " + StartTime;
    public String endDateTime = EndDate + " " + EndTime;
//    public String okButtonPath ="/html/body/div[2]/div[3]/div/div[2]/button[2]/span";
    public String campaignPath = "//*[@id=\"couponCreateForm-campaign_name\"]";
//    public String campaignListPath = "//*[@class=\"el-scrollbar__view el-select-dropdown__list\"]";
    public String campaignListPath = "/html/body/div[2]/div[4]/div/div/div[1]/ul";
    public String activityDescriptionPath = "//*[@id=\"couponCreateForm-description0\"]";
    public String activityDescriptionValue = "Test: AutoScripts";
    public String activityURLPath = "//*[@id=\"couponCreateForm-participate_page0\"]";
    public String activityURLValue = "www.autotest.com";
    public String activityReminderPath = "//*[@id=\"couponCreateForm-reminder0\"]";
    public String activityReminderValue = "AutoTest";
    public String basicInfoPagePath = "//*[@id=\"app\"]/div[3]/div/div/div/form/div[1]/div/label";



    public void basicInfo(WebDriver driver) throws InterruptedException, AWTException {
        //优惠券名称
        elementOpe.sendKeysByXpath(driver, activityNamePath, AtyName);

        //优惠金额
        elementOpe.sendKeysByXpath(driver, activityDiscountPricePath, activityDiscountPriceValue);

        //消费门槛
        elementOpe.sendKeysByXpath(driver, consumptionThresholdPath, consumptionThresholdValue);

        //可使用时间-开始时间 开始日期 开始时间 结束日期 结束时间  OK按钮确认
        elementOpe.sendKeysByXpath(driver, startTimePath, startDateTime);
        elementOpe.sendKeysByXpath(driver, endTimePath, endDateTime);
        mouseSim.moveAndClick();
//        elementOpe.clickByXpath(driver,startTimePath);
//
//        elementOpe.clickByXpath(driver,startDatePath);
//        elementOpe.clearSendKeysByXpath(driver,startDatePath,StartDate);
//
//        elementOpe.clickByXpath(driver,endDatePath);
//        elementOpe.clearSendKeysByXpath(driver,endDatePath,EndDate);
//
//        elementOpe.clickByXpath(driver,startTimePath2);
//        elementOpe.clearSendKeysByXpath(driver,startTimePath2,StartTime);
//
//
//        elementOpe.clickByXpath(driver,endTimePath);
//        elementOpe.clearSendKeysByXpath(driver,endTimePath,EndTime);
//
//        elementOpe.clickByXpath(driver, okButtonPath);

        //所属档期
        elementOpe.clickByXpath(driver, campaignPath);
        List<WebElement> campaignValueList =  driver.findElements(By.xpath(campaignListPath));

        for (WebElement campaign :campaignValueList
        ) {
            String campaignValue =  campaign.getText();
            if(campaignValue.contains("自动化测试专用")) {
                campaign.click();
                break;
            };
        }

        //优惠券描述
        elementOpe.sendKeysByXpath(driver,activityDescriptionPath,activityDescriptionValue);

        //优惠券跳转页面url
        elementOpe.sendKeysByXpath(driver,activityURLPath,activityURLValue);

        //温馨提醒
        elementOpe.clearSendKeysByXpath(driver,activityReminderPath,activityReminderValue);
    }
}
