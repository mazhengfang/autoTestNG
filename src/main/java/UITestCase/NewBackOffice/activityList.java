package UITestCase.NewBackOffice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class activityList {
    public String createOnlineActivityPath= "//*[@id=\"app\"]/div[3]/div/div/div/div[1]/button[1]/span";
    public String newActivityPagePath = "//*[@id=\"app\"]/div[3]/div/div/div/form/div[1]/div/label";

    public boolean createOnlineActivity(WebDriver driver) throws InterruptedException {
        //点击“新建线上优惠券”
        WebElement createOnlineActivityElement = driver.findElement(By.xpath(createOnlineActivityPath));
        createOnlineActivityElement.click();

        //“新建线上优惠券”页面加载
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(newActivityPagePath)));

        //“新建线上优惠券”页面加载成功校验
        sleep(1000);
        String pageLabel = driver.findElement(By.xpath(newActivityPagePath)).getText();
        if (pageLabel.contains("基本信息")) {
            System.out.println(pageLabel);
            return true;
        } else {
            System.out.println(pageLabel);
            return false;
        }
    }

    public void createOfflineActivity(){

    }

    public void createOMNIActivity(){

    }
}
