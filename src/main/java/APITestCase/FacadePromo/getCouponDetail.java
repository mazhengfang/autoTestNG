package APITestCase.FacadePromo;

import com.alibaba.fastjson.JSONObject;
import com.http.httpHeaders;
import com.http.httpResponse;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import xProject.flowFunction;
import xProject.httpData;


public class getCouponDetail {
    public String rhKey = "RequestHeader";
    public String rhConfigPath = "src\\main\\resources\\ConfigInfo\\RequestHeader.json";
    public String rType = "get";
    public xProject.flowFunction flowBaseAPICommon;
    public String sPath = "/facade_benefits/api/v4/coupon/";
    public String sVar = "/details";
    public String Env;


    public httpResponse getCouponDetail(String fdBenefitHost,String couponCode, String personNumber ) {
        authenticationToken authenticationToken = new authenticationToken();
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhConfigPath);
        String token = null;
        httpResponse tokenResponse = authenticationToken.getAuthenticationToken(Env,personNumber);
        token = tokenResponse.getBody(JSONObject.class).getString("token_type") + tokenResponse.getBody(JSONObject.class).getString("user_token");
        String key = "Authorization";
        String value = token;
        requestHeader.Add(key, value);
        String URL = fdBenefitHost + sPath + couponCode + sVar;
        httpResponse hs = httpData.transferRequest(URL, null, requestHeader, rType);
        return hs;
    }

}
