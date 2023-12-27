package xProject.testResult;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testResultData {

    public testResultData() {
        //do nothing
    }


    public static List<Map<String,Object>> TestResultDataList(List testResultData, String strCaseName, String strFunctionPoint, String strTestStep,actionResult strStatus ){
//        List<Map<String,Object>> testData=new ArrayList<Map<String,Object>>();
        Map<String,Object> data=new HashMap<String, Object>();
        data.put("Case Name", strCaseName);
        data.put("Function Points", strFunctionPoint);
        data.put("Test Step", strTestStep);
        data.put("Status", strStatus);
        testResultData.add(data);
        return testResultData;
    }

//    public static List<Map<String,Object>> TestStepResultList(List testResultData, String strTestStep,actionResult strStatus ){
////        List<Map<String,Object>> testData=new ArrayList<Map<String,Object>>();
//
//        Map<String,Object> data=new HashMap<String, Object>();
//        data.put("Test Step", strTestStep);
//        data.put("Status", strStatus);
//        testResultData.add(data);
//        return testResultData;
//    }
}
