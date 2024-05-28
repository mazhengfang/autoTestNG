package APITestCase.BackOffice.V1;

import com.alibaba.fastjson.JSONObject;
import com.http.httpResponse;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import xProject.flowFunction;
import xProject.httpData;

public class closeActivity {

    public String rType = "patch";
    public xProject.flowFunction flowBaseAPICommon;
    public String easyPromoInternalHost;
    public String sPath = "/api/v4/activity/";
    public String activityId = "10750";
    public String sVar = "/closes";
    public String Env;
    public String tokenBodyKey;
    public String tokenBodyPath;

    @BeforeSuite
    @Parameters({"ENV"})
    public void init(String ENV) {
        this.Env = ENV;
        this.flowBaseAPICommon = new flowFunction();
        flowBaseAPICommon.init(ENV);
        this.easyPromoInternalHost = flowBaseAPICommon.easyPromoInternalHost;
    }

    public httpResponse closeActivity() {
        String URL = easyPromoInternalHost + sPath + activityId + sVar ;
        this.tokenBodyKey = flowBaseAPICommon.TokenRequestBodyKey;
        this.tokenBodyPath = flowBaseAPICommon.TokenHttpsReqBodyPath;
        JSONObject requestBody =  httpData.prepareRequestBody(tokenBodyKey, tokenBodyPath);
        httpResponse hs = httpData.transferRequest(URL, requestBody, null, rType);
        return hs;
    }

    @Test
    public void closeActivity_FunctionTest_1() {
        httpResponse hs = closeActivity();
        System.out.println(hs.getBody(JSONObject.class).toString());
    }

}
