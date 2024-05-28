package UITestCase.JumpServer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Connection {
    WebDriver driver;
//    String URL = "https://jumpserver-ali.pp.dktapp.cloud/";


    public WebDriver init(String URL) {

        ChromeOptions chromeOptiOns = new ChromeOptions();
        chromeOptiOns.addArguments("--start-maximized");
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\jdk1.8.0_05\\bin\\chromedriver.exe");
        driver = new ChromeDriver(chromeOptiOns);
        driver.get(URL);
        return driver;
    }

    public boolean loginOn(WebDriver driver) {
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div[1]/div[2]/span[1]")));

        WebElement logInButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div/ul[1]/li[2]/a/span/svg/use"));
        logInButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"left-logo\"]")));

        WebElement favoriteIcon = driver.findElement(By.xpath("//*[@id=\"AssetTree_1_span\"]"));
        logInButton.click();

        WebElement db = driver.findElement(By.xpath("//*[@id=\"AssetTree_19_a\"]"));
        logInButton.click();

        WebElement connectButton = driver.findElement(By.xpath("//*[@id=\"mat-dialog-3\"]/elements-asset-tree-dialog/div/div[2]/mat-dialog-actions/button"));
        logInButton.click();

        return false;
    }

}
