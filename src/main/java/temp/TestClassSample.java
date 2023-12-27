package temp;


//import lombok.extern.java.Log;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

        import static org.testng.Assert.*;

public class TestClassSample {
    DotTestListener dotTestListener = new DotTestListener();
    @Parameters({"m_lastName"})
    @Test
    public void test2(@Optional("Test") String m_lastName) {
//     if( m_lastName.equals("expected"))
//         this.dotTestListener.step("TC1","Function Point",actionResult.Pass);
//     else
//         this.dotTestListener.step("TC1","Function Point",actionResult.Fail);




//        dotTestListener.onTestSuccess(Reporter.getCurrentTestResult());
//        dotTestListener.onTestFailure(Reporter.getCurrentTestResult());
//        dotTestListener.step("TC1","expected result",iResult);
//        Reporter.getOutput();


    }

    @Parameters({"m_lastName"})
    @Test(enabled=true)
    public void test1(@Optional("Beust") String m_lastName) {
         assertEquals(m_lastName,"Test");
         System.out.println("Test1");
    }

    @Parameters({"m_lastName"})
    @Test(enabled=true)
    public void test3(@Optional("Beust") String m_lastName) {
        assertEquals(m_lastName,"Test");
        System.out.println("Test3");
    }

}




