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

public class loginPage {
    WebDriver driver;
    public String driverProperty = "webdriver.chrome.driver";
    public String driverPath = "C:\\Program Files\\Java\\jdk1.8.0_05\\bin\\chromedriver.exe";
    public String expTitle = "活动列表 | EP-back-office";
    private String loginUserNamePath = "//*[@id=\"username\"]";
    private String loginPasswordPath = "//*[@id=\"password\"]";
    private String loginPagePath = "//*[@id=\"cnxbton\"]";
    private String loginPageTitle = "//*[@id=\"app\"]/div[1]/div[2]";

    public WebDriver init(String URL) {
        ChromeOptions chromeOptiOns = new ChromeOptions();
        chromeOptiOns.addArguments("--start-maximized");
        System.setProperty(driverProperty, driverPath);
        driver = new ChromeDriver(chromeOptiOns);
        driver.get(URL);
        return driver;
    }

    public boolean signOn(WebDriver driver, String userName, String password) {
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loginPagePath)));
        WebElement inputUserName = driver.findElement(By.xpath(loginUserNamePath));
        inputUserName.click();
        inputUserName.sendKeys(userName);

        WebElement inputPasswordElement = driver.findElement(By.xpath(loginPasswordPath));
        inputPasswordElement.click();
        inputPasswordElement.sendKeys(password);

        WebElement signOnButton = driver.findElement(By.xpath(loginPagePath));
        signOnButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loginPageTitle)));
        String title = driver.getTitle();
        if (title.equals(expTitle)) {
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

