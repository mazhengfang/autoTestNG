package UITestCase.NewBackOffice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class loginActivity {
    WebDriver driver;

    public WebDriver init(String URL) {
        ChromeOptions chromeOptiOns = new ChromeOptions();
        chromeOptiOns.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptiOns);
        driver.get(URL);
        return driver;
    }

    public boolean SignOn(WebDriver driver, String userName, String password) {
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cnxbton\"]")));
        WebElement inputUserName = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        inputUserName.click();
        inputUserName.sendKeys(userName);

        WebElement inputPasswordElement = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        inputPasswordElement.click();
        inputPasswordElement.sendKeys(password);


        WebElement signOnButton = driver.findElement(By.xpath("//*[@id=\"cnxbton\"]"));
        signOnButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]")));
        String title = driver.getTitle();
        WebElement tagList = driver.findElement(By.xpath("//*[@id=\"app\"]/div[3]/div[1]/ul"));

        if (title.equals("活动列表 | EP-back-office")) {
            return true;
        } else {
            return false;
        }

    }

    public void loginOut() {
        driver.quit();
    }

    public static void closeWindow() throws IOException {
        Runtime.getRuntime().exec("taskkill /F /im " + "chrome.exe");
    }
}

