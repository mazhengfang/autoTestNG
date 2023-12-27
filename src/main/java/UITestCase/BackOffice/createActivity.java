package UITestCase.BackOffice;

import com.commonFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.List;

import static java.lang.Thread.sleep;



public class createActivity {
    public String AtyName;
    public String StartDate;
    public String EndDate;
    public String StartTime;
    public String EndTime;
    public boCommonFunction boCommonFunction;
    private String allUsers = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[2]/div/div/div/div/label[1]/span[1]";
    private String distributeMPMStoreChannelBox = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[3]/div/div/div/label[1]/span[1]";
    private String distributeLiveRoomChannelBox = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[3]/div/div/div/label[4]/span[1]";
    private String redeemChannelBox = "#app > div > div.main-container.hasTagsView > section > div > div.app-container > form > div > div:nth-child(4) > div > div > div.el-checkbox-group > label:nth-child(1) > span.el-checkbox__input";
//    private String activityName =  "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[10]/div/div/div[1]/input";
    private String activityName =   "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[8]/div/div/div[1]/input";


    private String activityDescription = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[9]/div/div/div/textarea";


    private String strPath1 =   "/html/body/div[3]/div[1]/div/div[1]/span[1]/span[1]/div/input";
    private String strPath2 =   "/html/body/div[3]/div[1]/div/div[1]/span[1]/span[2]/div[1]/input";

    private String endDatePath ="/html/body/div[3]/div[1]/div/div[1]/span[3]/span[1]/div/input";
    private String endTimePath = "/html/body/div[3]/div[1]/div/div[1]/span[3]/span[2]/div[1]/input";
    private String okButtonPath = "/html/body/div[3]/div[1]/div/div[1]/span[3]/span[2]/div[2]/div[2]/button[2]";



    private String startDate1Path = "/html/body/div[4]/div[1]/div/div[1]/span[1]/span[1]/div/input";
    private String startTime1Path = "/html/body/div[4]/div[1]/div/div[1]/span[1]/span[2]/div[1]/input";
    private String endDate2Path = "/html/body/div[4]/div[1]/div/div[1]/span[3]/span[1]/div/input";
    private String endTime2Path = "/html/body/div[4]/div[1]/div/div[1]/span[3]/span[2]/div[1]/input";
    private String distributeMaxNumPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[13]/div/div/div[1]/input";



    private String testBatchLogonPath =  "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[17]/div/div/div/label[2]/span[1]";

    private String consumptionThresholdPath =  "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[26]/div/div/div[1]/input";

    private String discountedPricePath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[27]/div/div/div/input";

    private String createButtonPath = "//*[@id=\"app\"]/div/div[3]/section/div/button";

    private String createSuccessPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/div[3]/table/tbody/tr[1]/td[2]/div";
    private String okButton1Path = "/html/body/div[4]/div[1]/div/div[1]/span[3]/span[2]/div[2]/div[2]/button[2]";

    private String dropdownItemPath = "/html/body/div[6]/div[1]/div[1]/ul/li[3]";

    public void createActivity() {
        //to do...
    }

    public boolean createNormalActivity(WebDriver driver,String strName,String stackType, int endTime,String ConsumptionThreshold,String discountedPrice) throws InterruptedException {
        boCommonFunction = new boCommonFunction();
        Actions action = new Actions(driver);
        commonSteps(driver, action,strName,distributeMPMStoreChannelBox,endTime,ConsumptionThreshold,discountedPrice);
        stackingStatus(driver, stackType, action);

        // Create Button
        WebElement createButton = driver.findElement(By.xpath(createButtonPath));
        action.moveToElement(createButton).perform();
        createButton.click();
        sleep(500);

        WebElement createSuccess = driver.findElement(By.xpath(createSuccessPath));
        String actActName = createSuccess.getText();

        if (actActName.equals(AtyName)) {
            return true;
        } else return false;
    }

    public boolean createOMNIStoreNormalActivity(WebDriver driver,String strName,String stackType, int endTime,String ConsumptionThreshold,String discountedPrice) throws InterruptedException {
        boCommonFunction = new boCommonFunction();
        Actions action = new Actions(driver);
        commonSteps(driver, action,strName,distributeMPMStoreChannelBox,endTime,ConsumptionThreshold,discountedPrice);
        stackingStatus(driver, stackType, action);

        // Effective channel de-selection
        String STCMPMStoreStorePath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[4]/div/div/div/label[1]/span[1]/span";
        WebElement STCMPMStoreStore = driver.findElement(By.xpath(STCMPMStoreStorePath));
        action.moveToElement(STCMPMStoreStore).perform();
        STCMPMStoreStore.click();

        // Effective channel Re-selection
        String OMNIStorePath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[4]/div/div/div/label[2]/span[1]/span";
        WebElement OMNIStore = driver.findElement(By.xpath(OMNIStorePath));
        action.moveToElement(OMNIStore).perform();
        OMNIStore.click();

        // Create Button
        WebElement createButton = driver.findElement(By.xpath(createButtonPath));
        action.moveToElement(createButton).perform();
        createButton.click();
        sleep(500);

        WebElement createSuccess = driver.findElement(By.xpath(createSuccessPath));
        String actActName = createSuccess.getText();

        if (actActName.equals(AtyName)) {
            return true;
        } else return false;
    }

    public boolean createH5NormalActivity(WebDriver driver,String strName,String stackType, int endTime,String ConsumptionThreshold,String discountedPrice) throws InterruptedException {
        boCommonFunction = new boCommonFunction();
        Actions action = new Actions(driver);
        commonSteps(driver, action,strName,distributeMPMStoreChannelBox,endTime,ConsumptionThreshold,discountedPrice);
        stackingStatus(driver, stackType, action);

        //H5 活动页
        H5Selection(driver, action);

        // Create Button
        WebElement createButton = driver.findElement(By.xpath(createButtonPath));
        action.moveToElement(createButton).perform();
        createButton.click();
        sleep(500);

        WebElement createSuccess = driver.findElement(By.xpath(createSuccessPath));
        String actActName = createSuccess.getText();

        if (actActName.equals(AtyName)) {
            return true;
        } else return false;
    }

    private void H5Selection(WebDriver driver, Actions action) {
        //H5 活动页
        String cancelSelection = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[3]/div/div/div/label[1]/span[1]/span";
        WebElement cancelMPMSelection = driver.findElement(By.xpath(cancelSelection));
        action.moveToElement(cancelMPMSelection).perform();
        cancelMPMSelection.click();

        String H5Selection = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[3]/div/div/div/label[3]/span[1]";
        WebElement H5Button = driver.findElement(By.xpath(H5Selection));
        action.moveToElement(H5Button).perform();
        H5Button.click();

        String urlText = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[4]/div/div/div/input";
        WebElement urlTextSelection = driver.findElement(By.xpath(urlText));
        action.moveToElement(urlTextSelection).perform();
        urlTextSelection.sendKeys("https://H5.com");
    }

    private void stackingStatus(WebDriver driver, String stackType, Actions action) {
        if(stackType.equals("product")){
            String stackCheckedPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[23]/div/div/div";
            WebElement stackChecked = driver.findElement(By.xpath(stackCheckedPath));
            action.moveToElement(stackChecked).perform();
            stackChecked.click();

            String productCheckedPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[23]/div/div/div[2]/label/span[1]";
            WebElement productChecked = driver.findElement(By.xpath(productCheckedPath));
            action.moveToElement(productChecked).perform();
            productChecked.click();
        }
    }

    public boolean createBlackModelListNormalActivity(WebDriver driver, int endTime,String ConsumptionThreshold,String discountedPrice) throws InterruptedException {
        boCommonFunction = new boCommonFunction();
        Actions action = new Actions(driver);
        commonSteps(driver, action,"[Auto]黑名单",distributeMPMStoreChannelBox,endTime,ConsumptionThreshold,discountedPrice);
        notApplicableProduct(driver, action);

        // Create Button
        WebElement createButton = driver.findElement(By.xpath(createButtonPath));
        action.moveToElement(createButton).perform();
        createButton.click();
        sleep(1000);

        WebElement createSuccess = driver.findElement(By.xpath(createSuccessPath));
        String actActName = createSuccess.getText();

        if (actActName.equals(AtyName)) {
            return true;
        } else return false;
    }

    public boolean createH5BlackModelListNormalActivity(WebDriver driver, int endTime,String ConsumptionThreshold,String discountedPrice) throws InterruptedException {
        boCommonFunction = new boCommonFunction();
        Actions action = new Actions(driver);
        commonSteps(driver, action,"[Auto]H5黑",distributeMPMStoreChannelBox,endTime,ConsumptionThreshold,discountedPrice);

        //不适用商品按钮
        notApplicableProduct(driver, action);

        //选择H5投放渠道
        H5Selection(driver, action);

        // Create Button
        WebElement createButton = driver.findElement(By.xpath(createButtonPath));
        action.moveToElement(createButton).perform();
        createButton.click();
        sleep(1000);

        WebElement createSuccess = driver.findElement(By.xpath(createSuccessPath));
        String actActName = createSuccess.getText();

        if (actActName.equals(AtyName)) {
            return true;
        } else return false;
    }

    private void notApplicableProduct(WebDriver driver, Actions action) throws InterruptedException {
        //不适用商品按钮
        String notApplicableProductPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[21]/div/div/div";

        WebElement notApplicableProduct = driver.findElement(By.xpath(notApplicableProductPath));
        action.moveToElement(notApplicableProduct).perform();
        notApplicableProduct.click();
        sleep(2000);
        String uploadPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[21]/div/div/div[2]/div[1]/div/div";
        WebElement uploadButton = driver.findElement(By.xpath(uploadPath));
        action.moveToElement(uploadButton).perform();
        uploadButton.click();

        sleep(2000);
        Runtime exe = Runtime.getRuntime();
        try {
            String str = "C:\\Users\\zhengfang\\Downloads\\autoIT\\uploadFile.exe";
            exe.exec(str);
        } catch (IOException e) {
            System.out.println("Error to run the exe");
            e.printStackTrace();
        }
        sleep(3000);
    }

    public boolean createLiveRoomNormalActivity(WebDriver driver, String strName, String stackType, int endTime,String consumptionThreshold,String discountedPrice) throws InterruptedException {
        boCommonFunction = new boCommonFunction();
        Actions action = new Actions(driver);
        commonSteps(driver, action,strName,distributeLiveRoomChannelBox,endTime,consumptionThreshold,discountedPrice);

        if(stackType.equals("product")){
            String stackCheckedPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[23]/div/div/div";
            WebElement stackChecked = driver.findElement(By.xpath(stackCheckedPath));
            action.moveToElement(stackChecked).perform();
            stackChecked.click();

            String productCheckedPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[23]/div/div/div[2]/label/span[1]";
            WebElement productChecked = driver.findElement(By.xpath(productCheckedPath));
            action.moveToElement(productChecked).perform();
            productChecked.click();
        }

        // Create Button
        WebElement createButton = driver.findElement(By.xpath(createButtonPath));
        action.moveToElement(createButton).perform();
        createButton.click();
        sleep(500);

        WebElement createSuccess = driver.findElement(By.xpath(createSuccessPath));
        String actActName = createSuccess.getText();

        if (actActName.equals(AtyName)) {
            return true;
        } else return false;
    }



    public boolean createDesignatedUserActivity(WebDriver driver, int endTime,String consumptionThreshold,String discountedPrice) throws InterruptedException {
        boCommonFunction = new boCommonFunction();
        Actions action = new Actions(driver);
        commonSteps(driver, action,"[Auto]定向券",distributeMPMStoreChannelBox, endTime,consumptionThreshold,discountedPrice);

        designatedUserSet(driver, action);

        // Create Button
        WebElement createButton = driver.findElement(By.xpath(createButtonPath));
        action.moveToElement(createButton).perform();
        createButton.click();
        sleep(500);

        WebElement createSuccess = driver.findElement(By.xpath(createSuccessPath));
        String actActName = createSuccess.getText();

        if (actActName.equals(AtyName)) {
            return true;
        } else return false;

    }
    public boolean createH5DesignatedUserActivity(WebDriver driver, int endTime,String consumptionThreshold,String discountedPrice) throws InterruptedException {
        boCommonFunction = new boCommonFunction();
        Actions action = new Actions(driver);
        commonSteps(driver, action,"[Auto]H5定",distributeMPMStoreChannelBox, endTime,consumptionThreshold,discountedPrice);
        //指定用户
        designatedUserSet(driver, action);
        //选择H5投放渠道
        H5Selection(driver, action);

        // Create Button
        sleep(500);
        WebElement createButton = driver.findElement(By.xpath(createButtonPath));
        action.moveToElement(createButton).perform();
        createButton.click();
        sleep(500);

        WebElement createSuccess = driver.findElement(By.xpath(createSuccessPath));
        String actActName = createSuccess.getText();

        if (actActName.equals(AtyName)) {
            return true;
        } else return false;

    }

    public boolean createDesignatedUserProductTypeActivity(WebDriver driver, int endTime,String consumptionThreshold,String discountedPrice) throws InterruptedException {
        boCommonFunction = new boCommonFunction();
        Actions action = new Actions(driver);
        commonSteps(driver, action,"[Auto]定向券",distributeMPMStoreChannelBox, endTime,consumptionThreshold,discountedPrice);
        //指定用户
        designatedUserSet(driver, action);

        //指定商品
        applicableProductRangeSet(driver, action);

        //不支持叠加
        productNoStackingSet(driver, action);

        //Create Button
        WebElement createButton = driver.findElement(By.xpath(createButtonPath));
        action.moveToElement(createButton).perform();
        createButton.click();
        sleep(500);

        WebElement createSuccess = driver.findElement(By.xpath(createSuccessPath));
        String actActName = createSuccess.getText();

        if (actActName.equals(AtyName)) {
            return true;
        } else return false;

    }



    private void designatedUserSet(WebDriver driver, Actions action) throws InterruptedException {
        //指定用户
        String designatedUserPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[2]/div/div/div/div/label[2]/span[1]";
        WebElement designatedUser = driver.findElement(By.xpath(designatedUserPath));
        action.moveToElement(designatedUser).perform();
        designatedUser.click();

        String uploadUserFilePath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[2]/div/div/div[2]/div/div/div";
        WebElement uploadUserFile = driver.findElement(By.xpath(uploadUserFilePath));
        action.moveToElement(uploadUserFile).perform();
        uploadUserFile.click();
        sleep(500);
        Runtime exe = Runtime.getRuntime();
        try {
            String str = "C:\\Users\\zhengfang\\Downloads\\autoIT\\uploadUserFile.exe";
            exe.exec(str);

        } catch (IOException e) {
            System.out.println("Error to run the uploadUserFile.exe");
            e.printStackTrace();
        }
        sleep(2000);
    }

    public boolean createLiveRoomProductActivity(WebDriver driver,String stackingType,String strName,int endTime,String consumptionThreshold,String discountedPrice) throws InterruptedException {
        boCommonFunction = new boCommonFunction();
        Actions action = new Actions(driver);
        commonSteps(driver, action,strName,distributeLiveRoomChannelBox,endTime,consumptionThreshold,discountedPrice);

        WebElement applicableProduct = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[19]/div/div/div/div[1]/input"));
        applicableProduct.click();

        WebElement applicableProductItem = driver.findElement(By.xpath(dropdownItemPath));
        action.moveToElement(applicableProductItem).perform();
        applicableProductItem.click();
        sleep(500);

        String uploadPath =  "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[21]/div/div/div[1]/div/div/div";
        WebElement uploadXLS = driver.findElement(By.xpath(uploadPath));
        action.moveToElement(uploadXLS).perform();
        uploadXLS.click();

        sleep(3000);
        Runtime exe = Runtime.getRuntime();
        try {
            String str = "C:\\Users\\zhengfang\\Downloads\\autoIT\\uploadFile.exe";
            exe.exec(str);
        } catch (IOException e) {
            System.out.println("Error to run the exe");
            e.printStackTrace();
        }
        sleep(3000);

        if(stackingType.equals("noStack")){
            String stackCheckedPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[24]/div/div/div[1]";
            WebElement stackChecked = driver.findElement(By.xpath(stackCheckedPath));
            action.moveToElement(stackChecked).perform();
            stackChecked.click();
        }

        // Create Button
        WebElement createButton = driver.findElement(By.xpath(createButtonPath));
        action.moveToElement(createButton).perform();
        createButton.click();
        sleep(1000);

        WebElement createSuccess = driver.findElement(By.xpath(createSuccessPath));
        String actActName = createSuccess.getText();

        if (actActName.equals(AtyName)) {
            return true;
        } else return false;
    }


    public boolean createProductActivity(WebDriver driver,String sName, String stackingType, int endTime,String consumptionThreshold,String discountedPrice) throws InterruptedException {
        boCommonFunction = new boCommonFunction();
        Actions action = new Actions(driver);
        commonSteps(driver, action,sName,distributeMPMStoreChannelBox,endTime,consumptionThreshold,discountedPrice);

        applicableProductRangeSet(driver, action);

        if(stackingType.equals("noStack")){
            productNoStackingSet(driver, action);
        }

        // Create Button
        WebElement createButton = driver.findElement(By.xpath(createButtonPath));
        action.moveToElement(createButton).perform();
        createButton.click();
        sleep(1000);

        WebElement createSuccess = driver.findElement(By.xpath(createSuccessPath));
        String actActName = createSuccess.getText();

        if (actActName.equals(AtyName)) {
            return true;
        } else return false;


    }

    public boolean createH5ProductActivity(WebDriver driver,String sName, String stackingType, int endTime,String consumptionThreshold,String discountedPrice) throws InterruptedException {
        boCommonFunction = new boCommonFunction();
        Actions action = new Actions(driver);
        commonSteps(driver, action,sName,distributeMPMStoreChannelBox,endTime,consumptionThreshold,discountedPrice);

        applicableProductRangeSet(driver, action);
        if(stackingType.equals("noStack")){
            productNoStackingSet(driver, action);
        }

        //H5 活动页
        H5Selection(driver, action);

        // Create Button
        WebElement createButton = driver.findElement(By.xpath(createButtonPath));
        action.moveToElement(createButton).perform();
        createButton.click();
        sleep(1000);

        WebElement createSuccess = driver.findElement(By.xpath(createSuccessPath));
        String actActName = createSuccess.getText();

        if (actActName.equals(AtyName)) {
            return true;
        } else return false;


    }
    private void productNoStackingSet(WebDriver driver, Actions action) {
        String stackCheckedPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[24]/div/div/div[1]";
        WebElement stackChecked = driver.findElement(By.xpath(stackCheckedPath));
        action.moveToElement(stackChecked).perform();
        stackChecked.click();
    }

    private void applicableProductRangeSet(WebDriver driver, Actions action) throws InterruptedException {
        WebElement applicableProduct = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[19]/div/div/div/div/span"));
        applicableProduct.click();

        WebElement applicableProductItem = driver.findElement(By.xpath(dropdownItemPath));
        action.moveToElement(applicableProductItem).perform();
        applicableProductItem.click();
        sleep(500);

        String uploadPath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[21]/div/div/div[1]/div/div/div";
        WebElement uploadXLS = driver.findElement(By.xpath(uploadPath));
        action.moveToElement(uploadXLS).perform();
        uploadXLS.click();

        sleep(2000);
        Runtime exe = Runtime.getRuntime();
        try {
            String str = "C:\\Users\\zhengfang\\Downloads\\autoIT\\uploadFile.exe";
            exe.exec(str);
        } catch (IOException e) {
            System.out.println("Error to run the exe");
            e.printStackTrace();
        }
        sleep(2000);
    }


    private void commonSteps(WebDriver driver, Actions action, String strName,String distributeMPMStoreChannelBox,int endTimes, String conThreshold,String discountedPrice) throws InterruptedException {
        //所有用户
        boCommonFunction.clickByXpath(driver, allUsers);

        //投放渠道
        boCommonFunction.clickByXpath(driver, distributeMPMStoreChannelBox);

        //核销渠道
        boCommonFunction.clickByCSSSelector(driver, redeemChannelBox);

        //活动名称
        AtyName = strName + commonFunction.getCurrentTimePlus5("yyyyMMddHHmm", 1);
        boCommonFunction.sendKeysByXpath(driver, activityName, AtyName);

        //活动描述
        boCommonFunction.sendKeysByXpath(driver, activityDescription, "[AutoScript]自动化创建活动");

        //Campaign
        WebElement campaignList = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[10]/div/div/div/div/input"));

        campaignList.click();
        List<WebElement> campaignValueList =  driver.findElements(By.cssSelector("body > div.el-select-dropdown.el-popper > div.el-scrollbar > div.el-select-dropdown__wrap.el-scrollbar__wrap > ul > li.el-select-dropdown__item"));
        for (WebElement campaign :campaignValueList
             ) {
           String campaignValue =  campaign.getText();
            if(campaignValue.equals("暑期嘉年华")) {
                action.moveToElement(campaign).perform();
                campaign.click();
                break;
            };
        }

        //投放时间
        sleep(1000);
        WebElement distributeStartTime = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[11]/div/div/div/input[1]"));
        action.moveToElement(distributeStartTime).perform();
        distributeStartTime.click();

        //投放开始时间
        StartDate = commonFunction.getCurrentTimePlus5("yyyy-MM-dd", 5);
        EndDate = commonFunction.getCurrentTimePlus5("yyyy-MM-dd", 5);
        StartTime = commonFunction.getCurrentTimePlus5("HH:mm:ss", 1);
        EndTime = commonFunction.getCurrentTimePlus5("HH:mm:ss", endTimes);

        WebElement startDate = driver.findElement(By.xpath(strPath1));
        startDate.sendKeys(StartDate);

        WebElement startTime = driver.findElement(By.xpath(strPath2));
        startTime.clear();
        startTime.sendKeys(StartTime);

        //投放结束时间
        WebElement endDate = driver.findElement(By.xpath(endDatePath));
        endDate.clear();
        endDate.sendKeys(EndDate);

        WebElement endTime = driver.findElement(By.xpath(endTimePath));
        endTime.clear();
        endTime.sendKeys(EndTime);

        WebElement okButton = driver.findElement(By.xpath(okButtonPath));
        okButton.click();
        WebElement ok2button = driver.findElement(By.xpath("/html/body/div[3]/div[2]/button[2]"));
        ok2button.click();

        //生效时间
        WebElement distributeEffectiveTime = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[12]/div/div/div/input[1]"));
        action.moveToElement(distributeEffectiveTime).perform();
        distributeEffectiveTime.click();

        WebElement startDate1 = driver.findElement(By.xpath(startDate1Path));
        startDate1.sendKeys(StartDate);

        WebElement startTime1 = driver.findElement(By.xpath(startTime1Path));
        startTime1.clear();
        startTime1.sendKeys(StartTime);

        WebElement endDate2 = driver.findElement(By.xpath(endDate2Path));
        endDate2.clear();
        endDate2.sendKeys(EndDate);

        WebElement endTime2 = driver.findElement(By.xpath(endTime2Path));
        endTime2.clear();
        endTime2.sendKeys(EndTime);

        WebElement okButton1 = driver.findElement(By.xpath(okButton1Path));
        okButton1.click();
        WebElement okButton2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/button[2]"));
        okButton2.click();

        // Maximum distribution
        WebElement distributeMaxNum = driver.findElement(By.xpath(distributeMaxNumPath));
        action.moveToElement(distributeMaxNum).perform();
        distributeMaxNum.sendKeys("10000");

        // Maximum number per user
        WebElement distributeMaxNumPerUser = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[14]/div/div/div[1]/input"));
        action.moveToElement(distributeMaxNumPerUser).perform();
        distributeMaxNumPerUser.sendKeys("10");

        // Maximum distribution per day
        WebElement distributeMaxPerDay = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[15]/div/div/div[1]/input"));
        action.moveToElement(distributeMaxPerDay).perform();
        distributeMaxPerDay.sendKeys("1000");

        // Belong Channel
        WebElement belongChannel = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[16]/div/div/div/div/input"));
        action.moveToElement(belongChannel).perform();
        belongChannel.click();
        WebElement B2C = driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/ul/li[1]/span"));
        action.moveToElement(B2C).perform();
        B2C.click();

        //B2C option
//        WebElement belongB2cChannel = driver.findElement(By.xpath(belongB2cChannelPath));
//        action.moveToElement(belongB2cChannel).perform();
//        belongB2cChannel.click();

        //Test batch logo
        WebElement testBatchLogon = driver.findElement(By.xpath(testBatchLogonPath));
        action.moveToElement(testBatchLogon).perform();
        testBatchLogon.click();

        //Consumption Threshold
        WebElement consumptionThreshold = driver.findElement(By.xpath(consumptionThresholdPath));
        action.moveToElement(consumptionThreshold).perform();
        consumptionThreshold.sendKeys(conThreshold);

        //Discounted price
        WebElement discountPrice = driver.findElement(By.xpath(discountedPricePath));
        action.moveToElement(discountPrice).perform();
        discountPrice.sendKeys(discountedPrice);

        // 生效有效期
//        String effectiveTimePath = "//*[@id=\"app\"]/div/div[3]/section/div/div[2]/form/div/div[12]/div/div/div[2]/div/div/label[1]/span[1]";
//        WebElement effectiveTime = driver.findElement(By.xpath(effectiveTimePath));
//        action.moveToElement(effectiveTime).perform();
//        effectiveTime.click();
    }
}
