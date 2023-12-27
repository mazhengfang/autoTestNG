package temp;

import org.testng.Reporter;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestFile {

    @Parameters({"first-name"})
    @Test(groups = {"test.1"},enabled = false)
    public void testSingleString(String firstNme) {
        System.out.println("TestAnnotation.test.1");
        assert "Cedric".equals(firstNme);
    }

    @Parameters({"first-nme"})
    @Test(groups = {"test.2"},enabled = false)
    public void testOptionalString(@Optional("mysql") String firstNme) throws Exception {
        System.out.println("TestAnnotation.test.2");
//        assert "Cedric".equals(firstNme);
        if ("mysql".equals(firstNme)) {

            Reporter.log("购物车数据清理成功", true);
        }

        else {
            Assert.fail("FAILED");
//            throw new Exception("购物车数据清理失败");
        }
    }

    @Test(groups = { "init" }, dependsOnGroups = {"test.*"},enabled = false)
    public void serverStartedOk() {System.out.println("TestAnnotation.init 1 - test final");}

    @Test(groups = { "init" },enabled = false)
    public void initEnvironment() {System.out.println("TestAnnotation.init 2");}

    @Test(dependsOnGroups = { "init.*" },enabled = false)
    public void method1() {System.out.println("TestAnnotation.init final");}


    @Test(enabled = false)
    public void test() {
        System.out.println("TestAnnotation.test");
    }

}
