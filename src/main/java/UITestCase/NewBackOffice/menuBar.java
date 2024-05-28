package UITestCase.NewBackOffice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class menuBar {
    public String activityMenuPath = "//*[@id=\"app\"]/div[2]/ul/li[1]";
    public String activitySearchVisibility= "/html/body/div[1]/div[3]/div/div/div/div[1]/div[1]/div/div/div/input";
    public String campaignMenuPath = "//*[@id=\"app\"]/div[2]/ul/li[2]";
    public String campaignSearchVisibility= "/html/body/div[1]/div[3]/div/div/div[1]/div[1]/div[1]/div/input";
    public String marketingMenuPath = "//*[@id=\"app\"]/div[2]/ul/li[3]";
    public String marketingSearchVisibility= "/html/body/div[1]/div[3]/div/div/div/div[1]/div/div/div[1]/div/div/div/div/input";
    public String toDoListMenuPath = "//*[@id=\"app\"]/div[2]/ul/li[4]";
    public String batchReviewVisibility= "/html/body/div[1]/div[3]/div/div/div[1]/button/span";


    public void activityIcon(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement activityElement = driver.findElement(By.xpath(activityMenuPath));
        activityElement.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(activitySearchVisibility)));
    }

    public void campaignIcon(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement campaignElement = driver.findElement(By.xpath(campaignMenuPath));
        campaignElement.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(campaignSearchVisibility)));
    }

    public void marketingIcon(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement campaignElement = driver.findElement(By.xpath(marketingMenuPath));
        campaignElement.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(marketingSearchVisibility)));
    }

    public void toDoListIcon(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement campaignElement = driver.findElement(By.xpath(toDoListMenuPath));
        campaignElement.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(batchReviewVisibility)));
    }

}
