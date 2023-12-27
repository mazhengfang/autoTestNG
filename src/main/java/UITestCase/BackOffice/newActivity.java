package UITestCase.BackOffice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class newActivity {

    public void init() {
        //to do...
    }

    public boolean newActivity(WebDriver driver) throws InterruptedException {
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        // 获取 moveToElement 方法 ，元素定位到想要移上去的元素上


        WebElement homeMenu = driver.findElement(By.cssSelector("#app > div > div.sidebar-container > div > div.scrollbar-wrapper.el-scrollbar__wrap > div > ul > div:nth-child(2) > a"));
        action.moveToElement(homeMenu).perform();
        homeMenu.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement activityInfoMenu = driver.findElement(By.cssSelector("#app > div > div.sidebar-container > div > div.scrollbar-wrapper.el-scrollbar__wrap > div > ul > div:nth-child(3) > li > ul > div:nth-child(1)"));
        action.moveToElement(activityInfoMenu).perform();
        activityInfoMenu.click();
        sleep(5000);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#app > div > div.main-container.hasTagsView > section > div > div.form-content > div.flex > button > span > i")));
            WebElement newActivityButton = driver.findElement(By.cssSelector("#app > div > div.main-container.hasTagsView > section > div > div.form-content > div.flex > button > span > i"));
//             driver.findElement(By.xpath("/html/body/div/div/div[3]/section/div/div[1]/div[1]/button")).click();
            action.moveToElement(newActivityButton).perform();
            newActivityButton.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sleep(2000);

//            WebElement weAtyInfo = driver.findElement(By.cssSelector("#app > div > div.main-container.hasTagsView > section > div > div.form-content > div.flex > div"));
//            String strV1 = weAtyInfo.getText();
//            if(strV1.equals("activity information")){
//            WebElement newActivity = driver.findElement(By.cssSelector("#app > div > div.main-container.hasTagsView > section > div > div.form-content > div.flex > button > span > i"));
//            action.moveToElement(newActivity).perform();
//            newActivity.click();
//            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        String createActivityPage = null;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#app > div > div.main-container.hasTagsView > section > div > div.form-content > div")));
            WebElement holdActivity = driver.findElement(By.cssSelector("#app > div > div.main-container.hasTagsView > section > div > div.form-content > div"));
            createActivityPage = holdActivity.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (createActivityPage.equals("创建优惠活动")) {
            return true;
        } else return false;


    }
}
