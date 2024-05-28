package NewBackOffice;

import UITestCase.NewBackOffice.loginPage;
import UITestCase.NewBackOffice.menuBar;
import com.alibaba.fastjson.JSONObject;
import com.dbConnect.mySQL;
import com.ssh.App;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class onlineActivity_All {
    UITestCase.NewBackOffice.loginPage login;
    WebDriver driver;
    UITestCase.NewBackOffice.menuBar menuBar;
    com.dbConnect.mySQL mySQL;
    App app;
    private String couponSQL1 ="select * from coupon where code =\"";
    private String couponSQL2 ="\" and person_id =\"15005083203\"";
    private String userName ="zma23";
    private String password ="Shanghai=2024";
}
