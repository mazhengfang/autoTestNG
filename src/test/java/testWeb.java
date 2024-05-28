import com.alibaba.fastjson.JSONObject;
import com.ssh.App;
import org.apache.log4j.helpers.SyslogWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.lang.System.*;
import com.dbConnect.mySQL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.io.*;

import static java.lang.System.*;
import static org.junit.Assert.assertThat;

public class testWeb {
    private String easyPromoHost;
    private String facadePromoHost;
    private String Env;
    WebDriver driver;


//   @BeforeSuite
    static void setupClass() {
    }

//    @BeforeTest
    void setupTest() {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        ChromeOptions chromeOptiOns= new ChromeOptions();
        chromeOptiOns.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptiOns);

    }

//    @AfterTest
    void teardown() {
        driver.quit();
    }

    @Test
    void test1() throws Exception {
        App a = new App();
        a.connect();
        mySQL b = new mySQL();
//        b.dbMySQLConnect();

        // information_schema
//        List<String> c = b.getDatabase();
//        List<String> d = b.getTables("epo_ep_pp");
        List<String> e = b.getTableField("epo_ep_pp","coupon");
        String sql ="select * from coupon where code =\"2309110106430000dyf2\" and person_id =\"15005083203\"";
        JSONObject record = b.getRecords(sql,e);
        b.dbMySQLClose();

            }



}
