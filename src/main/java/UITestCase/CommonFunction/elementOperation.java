package UITestCase.CommonFunction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;

public class elementOperation {
    public void elementOperation(){
        // to do
    }

    public void clickByXpath(WebDriver driver, String byElement){
        Actions action = new Actions(driver);
        WebElement actionElement = driver.findElement(By.xpath(byElement));
        action.moveToElement(actionElement).perform();
        actionElement.click();
    }

    public void clickByCSSSelector(WebDriver driver, String byElement){
        Actions action = new Actions(driver);
        WebElement actionElement = driver.findElement(By.cssSelector(byElement));
        action.moveToElement(actionElement).perform();
        actionElement.click();
    }

    public void sendKeysByCSSSelector(WebDriver driver, String byElement, String value){
        Actions action = new Actions(driver);
        WebElement actionElement = driver.findElement(By.cssSelector(byElement));
        action.moveToElement(actionElement).perform();
        actionElement.sendKeys(value);
    }

    public void sendKeysByXpath(WebDriver driver, String byElement, String value){
        Actions action = new Actions(driver);
        WebElement actionElement = driver.findElement(By.xpath(byElement));
        action.moveToElement(actionElement).perform();
        actionElement.sendKeys(value);
    }

    public void clearSendKeysByXpath(WebDriver driver, String byElement, String value){
        Actions action = new Actions(driver);
        WebElement actionElement = driver.findElement(By.xpath(byElement));
        action.moveToElement(actionElement).perform();
        actionElement.clear();
        actionElement.sendKeys(value);
    }



    public HashMap<String,String> JsonObject(String name, String value, HashMap arr){
        arr.put(name,value);
        return arr;
    }
}
