package APITestCase.FacadePromo;

import com.alibaba.fastjson.JSONObject;
import com.http.httpHeaders;
import com.http.httpResponse;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import xProject.flowFunction;
import xProject.httpData;

public class getStockDetail {
    public String rhKey = "RequestHeader";
    public String rhConfigPath = "src\\main\\resources\\ConfigInfo\\RequestHeader.json";
    public String rType = "get";
    public xProject.flowFunction flowBaseAPICommon;
    public String sPath = "/facade_benefits/api/v4/stock/";
    public String sVar = "/details";


    public httpResponse getStockDetail(String ENV, String fdBenefitHost, String stockID,String personNumber ) {
        authenticationToken authenticationToken = new authenticationToken();
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhConfigPath);
        String token = null;
        httpResponse tokenResponse = authenticationToken.getAuthenticationToken(ENV,personNumber);
        token = tokenResponse.getBody(JSONObject.class).getString("token_type") + tokenResponse.getBody(JSONObject.class).getString("user_token");
        String key = "Authorization";
        String value = token;
        requestHeader.Add(key, value);
        String URL = fdBenefitHost + sPath + stockID + sVar;
        httpResponse hs = httpData.transferRequest(URL, null, requestHeader, rType);
        return hs;
    }


}
