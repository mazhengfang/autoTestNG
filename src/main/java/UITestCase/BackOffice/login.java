package UITestCase.BackOffice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class login {
    WebDriver driver;
//    String URL = "https://easypromo-front.pp.dktapp.cloud/login";


    public WebDriver init(String URL) {

        ChromeOptions chromeOptiOns = new ChromeOptions();
        chromeOptiOns.addArguments("--start-maximized");
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\jdk1.8.0_05\\bin\\chromedriver.exe");
        driver = new ChromeDriver(chromeOptiOns);
        driver.get(URL);
        return driver;
    }

    public boolean loginOn(WebDriver driver) {
        Actions action=new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#app > div > form > button > span")));
        WebElement logInButton = driver.findElement(By.cssSelector("#app > div > form > button > span"));
        logInButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("head > title")));
        WebElement inputNameElement = driver.findElement(By.cssSelector("#username"));
//        inputNameElement.sendKeys("z21zgu");
        inputNameElement.sendKeys("zma23");
        WebElement inputPasswordElement = driver.findElement(By.cssSelector("#password"));
        // 获取 moveToElement 方法 ，元素定位到想要移上去的元素上
        action.moveToElement(inputPasswordElement).perform();
//        inputPasswordElement.sendKeys("$RFVbgt5");
        inputPasswordElement.sendKeys("Shanghai=2024");

        WebElement signOnButton = driver.findElement(By.cssSelector("#cnxbton"));
        signOnButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#app > div > div.sidebar-container > div > div.scrollbar-wrapper.el-scrollbar__wrap > div > ul > div:nth-child(3) > li > div > span")));
        String title = driver.getTitle();
        if (title.equals("ActivityInformation - 迪卡侬后台系统")) {
            return true;
        } else {
            return false;
        }

    }

    public void loginOut() {
        driver.quit();
    }

    public static void closeWindow() throws IOException {
//        driver = new ChromeDriver();
//             for (String winHandle : driver.getWindowHandles()){
//                 driver.switchTo().window(winHandle);
//                 driver.quit();
//              }
//
        Runtime.getRuntime().exec("taskkill /F /im " + "chrome.exe");


    }


}
