package APITestCase.EasyPromo;


import com.TestListener;
//import org.testng.Assert;
import xProject.testResult.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.report.report;


@Listeners(TestListener.class)
public class ReceiveCoupon {
    report iReport = new report();
    public ReceiveCoupon(){

    }


    @Test()
    public void testStep() {
        Assert.verifyEquals("Pass","Fail","Not correct");
//
    }



}
