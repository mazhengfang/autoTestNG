package APITestCase.BackOffice;
import com.commonFunction;
import xProject.flowFunction;
import xProject.testCase.testCase;
import xProject.httpData;

public class createStock {
    public void initProject(String ENV) throws Exception {
        commonFunction functionBaseAPICommon = new commonFunction();
        flowFunction flowBaseAPICommon = new flowFunction();
        testCase testCase = new testCase();
        httpData httpData = new httpData();
    }

    public void createStockRequest(testCase testCase) {

//        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhPath, false);
//        JSONObject requestBody = JSONObject.parseObject(String.valueOf(testCase.getRequestBody()));
//        httpResponse rs = httpData.transferRequest(functionBaseAPICommon.easyPromoHost + requestPath, requestBody, requestHeader, rType);
//        boolean result = functionBaseAPICommon.AssertResult(rs, testCase.getCaseName() + ":" + testCase.getCaseDesc(), Integer.parseInt(testCase.getExpectedData().getCode()));
//        if (result) {
//            testData = common.TestResultDataList(testData, testCase.getCaseName(), testCase.getCaseDesc(), actionResult.Pass);
//        } else
//            testData = common.TestResultDataList(testData, testCase.getCaseName(), testCase.getCaseDesc(), actionResult.Fail);
    }
}
