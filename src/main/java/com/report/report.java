package com.report;

import com.alibaba.fastjson.JSONObject;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class report extends Reporter {
    private final String vStepName = "StepName";
    private final String vStepDescription = "StepDescription";
    private final String vStepResult = "StepResult";
    private final String vSeverityType = "SeverityType";
    private final String vExpectedResult = "ExpectedResult";
    private final String vActualResult = "ActualResult";
    private final String vExecutionTime = "ExecutionTime";


    public report() {
    }

    public JSONObject testStep(String stepName, String stepDescription, ITestResult tr, severityType st, String expResult, String actResult) {
        JSONObject jo = new JSONObject();
        String exeStartTime = (LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        jo.put(vStepName, stepName);
        jo.put(vStepDescription, stepDescription);
        jo.put(vExecutionTime, exeStartTime);
        jo.put(vStepResult, tr.getStatus());
        jo.put(vSeverityType, st);
        jo.put(vExpectedResult, expResult);
        jo.put(vActualResult, actResult);
//        if (tr.getStatus().equals("Fail")) {
//            Assert.fail(
//                    "\n" + vStepName + ": " + stepName
//                            + "\n" + vStepDescription + ": " + stepDescription
//                            + "\n" + vExecutionTime + ": " + exeStartTime
//                            + "\n" + vSeverityType + ": " + st
//                            + "\n" + vExpectedResult + ": " + expResult
//                            + "\n" + vActualResult +": " + actResult
//                            + "\n" + vStepResult + ": " + ": " + tr
//            );
//        }
        System.out.println(
                jo.getString(vStepName)
                        + "   " + jo.getString(vStepDescription)
                        + "   " + jo.getString(vSeverityType)
                        + "   " + jo.getString(vStepResult)
                        + "   " + vActualResult + ":" + jo.getString(vActualResult)
                        + "   " + vExpectedResult + ":" + jo.getString(vExpectedResult)
                        + "   " + vExecutionTime + ":" + jo.getString(vExecutionTime)
        );
        Reporter.log(jo.toJSONString());
        return jo;
    }

}
