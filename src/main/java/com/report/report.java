package com.report;

import com.alibaba.fastjson.JSONObject;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


public class report extends Reporter {
    private final String vStepName = "StepName";
    private final String vStepDescription = "StepDescription";
    private final String vStepResult = "StepResult";
    private final String vSeverityType = "SeverityType";
    private final String vExpectedResult = "ExpectedResult";
    private final String vActualResult = "ActualResult";
    private final String vTestData = "TestData";
    private final String vExecutionTime = "ExecutionTime";


    public report() {
    }

    public JSONObject testStep(String stepName, String stepDescription, String testRlt, severityType st, String expResult, String actResult,String testData, String exeStartTime) {
        JSONObject jo = new JSONObject();
//        String exeStartTime = (LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        jo.put(vStepName, stepName);
        jo.put(vStepDescription, stepDescription);
        jo.put(vExecutionTime, exeStartTime);
        jo.put(vStepResult, testRlt);
        jo.put(vSeverityType, st);
        jo.put(vExpectedResult, expResult);
        jo.put(vActualResult, actResult);
        jo.put(vTestData, testData);
        String sStepName = String.format("%-50s",stepName);
        String sStepDescription = String.format("%-50s",stepDescription);
        String sSeverityType = String.format("%-20s",st);
        String sStepResult = String.format("%-20s",testRlt);
        String sExpectedResult = String.format("%-50s",expResult);
        String sActualResult = String.format("%-50s",actResult);
        String sTestData = String.format("%-20s",testData);
        String sExecutionTime = String.format("%-30s",exeStartTime);
        System.out.println(
                sStepName
                        + sStepDescription
                        + sSeverityType
                        + sStepResult
                        + sTestData
                        + sExpectedResult
                        + sActualResult
                        + sExecutionTime
        );
        Reporter.log(jo.toJSONString());
        return jo;
    }


    public List<Map>  testResultData(String stepName, String stepDescription,severityType styTy, String expRlt, String actRlt,String testData, List<Map> testRltData){
        JSONObject testScenario = new JSONObject();
        testScenario.put("stepName", stepName);
        testScenario.put("stepDescription",stepDescription);
        testScenario.put("actRlt",actRlt);
        testScenario.put("expRlt",expRlt);
        testScenario.put("testData",testData);
        testScenario.put("severity",styTy);
        String exeStartTime = (LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        testScenario.put("exeStartTime",exeStartTime);
        String testRlt;
        if(expRlt == " " || expRlt == null)  testRlt = String.valueOf(testStatus.Pass);
        else{
            if(expRlt.equals(actRlt))
                testRlt = String.valueOf(testStatus.Pass);
            else  testRlt = String.valueOf(testStatus.Failed);
        }
        testScenario.put("testRlt",testRlt);

        testStep(stepName,stepDescription, testRlt,styTy,expRlt,actRlt,testData,exeStartTime);
        testRltData.add(testScenario);
        return testRltData;
    }

}
