package UITestCase.BackOffice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

import static java.lang.Thread.sleep;

public class validateActivity {
    public void validateActivity() {

    }

    public HashMap validateActivity(WebDriver driver, String couponType, String stackType) throws InterruptedException {
        boCommonFunction boCommonFunction = new boCommonFunction();
        HashMap activityInfo = new HashMap();
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement webElement = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/section/div/div[2]/div[3]/table/tbody/tr[1]/td[9]/div/button[1]"));
        action.moveToElement(webElement).perform();
        webElement.click();
        sleep(2000);

        //Batch Number
        String viewPagePath = "//*[@id=\"app\"]/div/div[3]/section/div/div[1]/div/div[1]/p";
        WebElement viewPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(viewPagePath)));
        String StockID = viewPage.getText();
        boCommonFunction.JsonObject("StockID", StockID.substring(14, 30), activityInfo);

        //Active status
        String activeStatusPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[1]/div/div[3]/p[5]/span";
        String activeStatus = driver.findElement(By.xpath(activeStatusPath)).getText();
        boCommonFunction.JsonObject("Active Status", activeStatus, activityInfo);

        //Belong to Campaign
        String belongToCampaignPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[1]/div/div[3]/p[7]";
        String belongToCampaign = driver.findElement(By.xpath(belongToCampaignPath)).getText();
        boCommonFunction.JsonObject("Belong to Campaign", belongToCampaign.substring(19), activityInfo);

        //Activity ID
        String activityIDPath = "//*[@id=\"pane-first\"]/div/div[2]/form/div/div[1]/div/div/p";
        String activityID = driver.findElement(By.xpath(activityIDPath)).getText();
        boCommonFunction.JsonObject("Activity ID", activityID, activityInfo);

        //Activity Name
        String activityNamePath = "//*[@id=\"app\"]/div/div[3]/section/div/div[1]/div/div[2]";
        String activityName = driver.findElement(By.xpath(activityNamePath)).getText();
        boCommonFunction.JsonObject("Activity Name", activityName, activityInfo);

        //Activity Description
        String activityDescriptionPath = "//*[@id=\"pane-first\"]/div/div[2]/form/div/div[2]/div/div/p";
        String activityDescription = driver.findElement(By.xpath(activityDescriptionPath)).getText();
        boCommonFunction.JsonObject("Coupon Description", activityDescription, activityInfo);

        //Distribution Time
        String strDistributeTime = "//*[@id=\"pane-first\"]/div/div[2]/form/div/div[3]/div";
        String distributeTime = driver.findElement(By.xpath(strDistributeTime)).getText();
        boCommonFunction.JsonObject("Distribute Time", distributeTime.substring(7), activityInfo);

        //Effective Time
        String effectiveTimePath = "//*[@id=\"pane-first\"]/div/div[2]/form/div/div[4]/div";
        String effectiveTime = driver.findElement(By.xpath(effectiveTimePath)).getText();
        boCommonFunction.JsonObject("Effective Time", effectiveTime.substring(16), activityInfo);

        //Max Distribution
        String maxDistributionPath = "//*[@id=\"pane-first\"]/div/div[2]/form/div/div[6]/div/div/p";
        String maxDistributionValue = driver.findElement(By.xpath(maxDistributionPath)).getText();
        boCommonFunction.JsonObject("Max Distribution", maxDistributionValue, activityInfo);

        //Max Number users can take
        String maxNumByUserPath = "//*[@id=\"pane-first\"]/div/div[2]/form/div/div[7]/div/div/p";
        String maxNumByUser = driver.findElement(By.xpath(maxNumByUserPath)).getText();
        boCommonFunction.JsonObject("Max Num by Users", maxNumByUser, activityInfo);

        //Max Number issued per day
        String maxNumIssuePerDayPath = "//*[@id=\"pane-first\"]/div/div[2]/form/div/div[8]/div/div/p";
        String maxNumIssuePerDay = driver.findElement(By.xpath(maxNumIssuePerDayPath)).getText();
        boCommonFunction.JsonObject("Max Num Issued Per Day", maxNumIssuePerDay, activityInfo);

        //Belonging Channel
//        String belongingChannelPath = "//*[@id=\"pane-first\"]/div/div[2]/form/div/div[8]/div/div/p/span";
        String belongingChannelPath = "//*[@id=\"pane-first\"]/div/div[2]/form/div/div[9]/div/div/p/span";
        String belongingChannel = driver.findElement(By.xpath(belongingChannelPath)).getText();
        boCommonFunction.JsonObject("Belonging Channel", belongingChannel, activityInfo);

        //Test Batch Logo
//        String testBatchLogoPath = "//*[@id=\"pane-first\"]/div/div[2]/form/div/div[9]/div/div/p/span";
        String testBatchLogoPath = "//*[@id=\"pane-first\"]/div/div[2]/form/div/div[10]/div/div/p/span";
        String testBatchLogo = driver.findElement(By.xpath(testBatchLogoPath)).getText();
        boCommonFunction.JsonObject("Test Batch Logo", testBatchLogo, activityInfo);

        // ********  applicable product range ******
        //投放 Channel
        String distributeChannelPath = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[1]/div/div/p/span/span";
        String distributeChannel = driver.findElement(By.xpath(distributeChannelPath)).getText();
        boCommonFunction.JsonObject("Distribute Channel", distributeChannel, activityInfo);

        if(distributeChannel.equals("活动页")) {
            //活动页url
            String urlPath = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[2]/div/div/p";
            String url = driver.findElement(By.xpath(urlPath)).getText();
            boCommonFunction.JsonObject("url", url, activityInfo);

            //Effective Channel
            String effectiveChannelPath = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[3]/div/div/p/span/span";
            String effectiveChannel = driver.findElement(By.xpath(effectiveChannelPath)).getText();
            boCommonFunction.JsonObject("Effective Channel", effectiveChannel, activityInfo);

            //H5
            String h5Path = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[5]/div/div/p";
            String h5 = driver.findElement(By.xpath(h5Path)).getText();
            boCommonFunction.JsonObject("H5", h5, activityInfo);

            //Participating users
            String participatingUsersPath = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[6]/div/div/p/span";
            String participatingUsers = driver.findElement(By.xpath(participatingUsersPath)).getText();
            boCommonFunction.JsonObject("Participating Users", participatingUsers, activityInfo);

            //ApplicableGoodsType
            String applicableGoodsTypePath = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[7]/div[1]/div/div/span";
            String applicableGoodsType = driver.findElement(By.xpath(applicableGoodsTypePath)).getText();
            boCommonFunction.JsonObject("Applicable Goods Type", applicableGoodsType, activityInfo);

            if (couponType.equals("product")) {
                //8492960

                String item8492960Path = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[7]/div[2]/div[3]/table/tbody/tr[1]/td[1]/div";
                String item8492960 = driver.findElement(By.xpath(item8492960Path)).getText();
                boCommonFunction.JsonObject("8492960", item8492960, activityInfo);

                String item8363990Path = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[7]/div[2]/div[3]/table/tbody/tr[2]/td[1]/div";
                String item8363990 = driver.findElement(By.xpath(item8363990Path)).getText();
                boCommonFunction.JsonObject("8363990", item8363990, activityInfo);

                String item8324625Path = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[7]/div[2]/div[3]/table/tbody/tr[3]/td[1]/div";
                String item8324625 = driver.findElement(By.xpath(item8324625Path)).getText();
                boCommonFunction.JsonObject("8324625", item8324625, activityInfo);
            }
        }
        else{
            //Effective Channel
            String effectiveChannelPath = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[2]/div/div/p/span/span";
            String effectiveChannel = driver.findElement(By.xpath(effectiveChannelPath)).getText();
            boCommonFunction.JsonObject("Effective Channel", effectiveChannel, activityInfo);


            //H5
            String h5Path = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[4]/div/div/p";
            String h5 = driver.findElement(By.xpath(h5Path)).getText();
            boCommonFunction.JsonObject("H5", h5, activityInfo);

            //Participating users
            String participatingUsersPath = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[5]/div/div/p/span";
            String participatingUsers = driver.findElement(By.xpath(participatingUsersPath)).getText();
            boCommonFunction.JsonObject("Participating Users", participatingUsers, activityInfo);


            //ApplicableGoodsType
            String applicableGoodsTypePath = "//*[@id=\"pane-first\"]/div/div[6]/form/div/div[6]/div[1]/div/div/span";
            String applicableGoodsType = driver.findElement(By.xpath(applicableGoodsTypePath)).getText();
            boCommonFunction.JsonObject("Applicable Goods Type", applicableGoodsType, activityInfo);

            if (couponType.equals("product")) {
                //8492960
                String item8492960Path = "//*[@id=\"pane-first\"]/div/div[7]/form/div/div[6]/div[2]/div[3]/table/tbody/tr[1]/td[1]/div";
                String item8492960 = driver.findElement(By.xpath(item8492960Path)).getText();
                boCommonFunction.JsonObject("8492960", item8492960, activityInfo);

                String item8363990Path = "//*[@id=\"pane-first\"]/div/div[7]/form/div/div[6]/div[2]/div[3]/table/tbody/tr[2]/td[1]/div";
                String item8363990 = driver.findElement(By.xpath(item8363990Path)).getText();
                boCommonFunction.JsonObject("8363990", item8363990, activityInfo);

                String item8324625Path = "//*[@id=\"pane-first\"]/div/div[7]/form/div/div[6]/div[2]/div[3]/table/tbody/tr[3]/td[1]/div";
                String item8324625 = driver.findElement(By.xpath(item8324625Path)).getText();
                boCommonFunction.JsonObject("8324625", item8324625, activityInfo);
            }

        }



        // ********  discount information ******
        //Type of discount coupon
        String discountCouponTypePath = "//*[@id=\"pane-first\"]/div/div[8]/form/div/div[1]/div/div/p/span";
        String discountCouponType = driver.findElement(By.xpath(discountCouponTypePath)).getText();
        boCommonFunction.JsonObject("Discount Coupon Type", discountCouponType, activityInfo);

        //Consumption Threshold
        String consumptionThresholdPath = "//*[@id=\"pane-first\"]/div/div[8]/form/div/div[2]/div/div/p";
        String consumptionThreshold = driver.findElement(By.xpath(consumptionThresholdPath)).getText();
        boCommonFunction.JsonObject("Consumption Threshold", consumptionThreshold, activityInfo);

        //Discounted price
        String discountPricePath = "//*[@id=\"pane-first\"]/div/div[8]/form/div/div[3]/div/div/p";
        String discountPrice = driver.findElement(By.xpath(discountPricePath)).getText();
        boCommonFunction.JsonObject("Discount Price", discountPrice, activityInfo);



        Boolean StackingChecked = false;
        if (stackType.equals("noStack")) {
            Boolean StackingUnChecked = true;
            String couponStackingTypePath = "//*[@id=\"pane-first\"]/div/div[4]/form/div/div/div/label/p/div";

            String couponStackingTypeStatus = driver.findElement(By.xpath(couponStackingTypePath)).getAttribute("class");
            if (couponStackingTypeStatus.contains("is-disabled")) {
                StackingUnChecked = false;
            }
            boCommonFunction.JsonObject("Stacking Checked", String.valueOf(StackingUnChecked), activityInfo);
        } else {
            String couponStackingTypePath = "//*[@id=\"pane-first\"]/div/div[4]/form/div/div/div/label/p/div";
            String couponStackingTypeStatus = driver.findElement(By.xpath(couponStackingTypePath)).getAttribute("class");
            if (couponStackingTypeStatus.contains("is-checked")) {
                StackingChecked = true;
                Boolean productChecked = false;
                String productCheckedPath = "//*[@id=\"pane-first\"]/div/div[4]/form/div/div/div/label/div/label/span[1]";
                String productCheckedStatus = driver.findElement(By.xpath(productCheckedPath)).getAttribute("class");
                if (productCheckedStatus.contains("is-checked")) {
                    productChecked = true;
                }
                boCommonFunction.JsonObject("Product Checked", String.valueOf(productChecked), activityInfo);
            }            boCommonFunction.JsonObject("Stacking Checked", String.valueOf(StackingChecked), activityInfo);
        }

        return activityInfo;
    }


    public HashMap UpdateActivity(WebDriver driver,  HashMap activityInfo, String couponType, String stackType, int sleepTime)throws InterruptedException {
        sleep(sleepTime);
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement homeMenu = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div/div[1]/div/ul/div[3]/li/ul/div[1]"));
        action.moveToElement(homeMenu).perform();
        homeMenu.click();

        String filterPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[1]/div[2]/div[1]/div[1]/div/input";
        WebElement filter = driver.findElement(By.xpath(filterPath));
        action.moveToElement(filter).perform();
        filter.sendKeys((CharSequence) activityInfo.get("Activity ID"));

        String SearchButton = "//*[@id=\"app\"]/div/div[3]/section/div/div[1]/div[2]/div[1]/div[2]/button[1]/span";
        WebElement Search = driver.findElement(By.xpath(SearchButton));
        action.moveToElement(Search).perform();
        Search.click();

        String viewButton = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/div[3]/table/tbody/tr/td[9]/div/button[1]/span";
        WebElement view = driver.findElement(By.xpath(viewButton));
        action.moveToElement(view).perform();
        view.click();

        //Active status
        String activeStatusPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[1]/div/div[3]/p[5]/span";
        String activeStatus = driver.findElement(By.xpath(activeStatusPath)).getText();
        boCommonFunction boCommonFunction = new boCommonFunction();
        boCommonFunction.JsonObject("Active Status", activeStatus, activityInfo);
        return activityInfo;
    }

    public String closeActivity(WebDriver driver,  String activityID)throws InterruptedException
    {
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement homeMenu = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div/div[1]/div/ul/div[3]/li/ul/div[1]"));
        action.moveToElement(homeMenu).perform();
        homeMenu.click();

//        String filterPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[1]/div[2]/div[1]/div[1]/div/input";
//        WebElement filter = driver.findElement(By.xpath(filterPath));
//        action.moveToElement(filter).perform();
//        filter.sendKeys((CharSequence) activityID);

        String SearchButton = "//*[@id=\"app\"]/div/div[3]/section/div/div[1]/div[2]/div[1]/div[2]/button[1]/span";
        WebElement Search = driver.findElement(By.xpath(SearchButton));
        action.moveToElement(Search).perform();
        Search.click();

        String closeButton = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/div[3]/table/tbody/tr/td[9]/div/button[3]/span";
        WebElement close = driver.findElement(By.xpath(closeButton));
        action.moveToElement(close).perform();
        close.click();


        String confirmButton =  "//*[@id=\"app\"]/div/div[3]/section/div/div[4]/div/div[3]/div/button[1]/span";
        WebElement Confirm = driver.findElement(By.xpath(confirmButton));
        action.moveToElement(Confirm).perform();
        Confirm.click();

        sleep(5000);
        //Active status
        String activeStatusPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/div[3]/table/tbody/tr/td[8]/div/div/div/p[2]";
        String activeStatus = driver.findElement(By.xpath(activeStatusPath)).getText();
        return activeStatus;
    }
}
