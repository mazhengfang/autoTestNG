package temp;


import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.util.Date;

public class DotTestListener extends TestListenerAdapter {
    private int m_count = 0;

    @Override
    public void onTestFailure(ITestResult tr) {
        log("Fail");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log("Skipped");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log("Pass");
    }

    private void log(String string) {
        System.out.print(string);
        if (++m_count % 40 == 0) {
            System.out.println("");
        }
    }

//    public void step(String name, String description, actionResult ar) {
//        JSONObject jo = new JSONObject();
//        new Date();
//        jo.put("name", name);
//        jo.put("description", description);
//        jo.put("date", (new Date()).getTime());
//        jo.put("result", ar);
////        jo.put("logType", logType.report);
//
//
//        if (ar.toString().equals("Fail") ) {
//            Assert.fail("Step 1: " + description + "Failed, please check");
//        }
//
//        System.out.println(jo.toJSONString());
//        Reporter.log(jo.toJSONString());
//    }


}

