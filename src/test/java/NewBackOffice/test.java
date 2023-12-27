package NewBackOffice;
import UITestCase.NewBackOffice.loginActivity;
import com.alibaba.fastjson.JSONObject;
import com.dbConnect.mySQL;
import com.ssh.App;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Slf4j
public class test {


    UITestCase.NewBackOffice.loginActivity login;
    WebDriver driver;
    private String userName ="zma23";
    private String password ="Shanghai=2024";
    com.dbConnect.mySQL mySQL;
    App app;
    private String couponSQL1 ="select * from coupon where code =\"";
    private String couponSQL2 ="\" and person_id =\"15005083203\"";


    public void test1(){
        login = new loginActivity();
        String URL ="https://easypromo-front-new.pp.dktapp.cloud/#/login";
        driver = login.init(URL);

        boolean success = login.SignOn(driver,userName,password);
        assertEquals(success,true,"Backoffice SignOn is successful.");
        log.info("list中的数据打印出来");
        log.warn("list中的数据打印出来");

        System.out.println("BackOffice login page is loading ...");
    }

    @Test
    public void test2() throws Exception {
        app = new App();
        mySQL = new mySQL();
        app.connect();
        JSONObject dbRecord =mySQL.record("epo_ep_pp","coupon",couponSQL1+"2312050121000002"+couponSQL2);
    }
}
