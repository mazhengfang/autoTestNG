package APITestCase.FacadePromo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import xProject.flowFunction;
import xProject.httpData;
import xProject.testCase.testCase;
import xProject.testResult.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;


// PDP页面领完券展示
public class userAvailableCoupon {
    private String rhKey = "RequestHeader";
    private String rhConfigPath = "src\\main\\resources\\ConfigInfo\\RequestHeader.json";
    private String rType = "post";

    private String sPath = "/facade_benefits/api/v4/coupon/user_available_coupons";
    private String userAvailableCouponRequestBodyKey = "UserAvailableCoupon_TestBody_";
    private String ReqBodyPath = "src\\main\\resources\\ConfigInfo\\FacadePromo\\userAvailableCouponRequestBody.json";

    //request body1 是用来测试全场券的， request body2是用来测试定向券（指定用户） 单品券的
    public httpResponse userAvailableCoupon(String ENV, String fdBenefitHost,String clientName, String bodyNumber,String personPhone) {
        authenticationToken authenticationToken = new authenticationToken();
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhConfigPath);
        httpResponse tokenResponse = authenticationToken.getAuthenticationToken(ENV,personPhone);
        String token = tokenResponse.getBody(JSONObject.class).getString("token_type") + tokenResponse.getBody(JSONObject.class).getString("user_token");
        requestHeader.Add("Authorization", token);
        requestHeader.Add("client-name", clientName);
        String URL = fdBenefitHost + sPath ;

        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(userAvailableCouponRequestBodyKey + bodyNumber);
        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }

    // request body3是用来测试定向券（非指定用户）单品券的
    public httpResponse NonDesignedUserAvailableCoupon(String ENV, String fdBenefitHost,String bodyNumber, String personPhone) {
        authenticationToken authenticationToken = new authenticationToken();
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhConfigPath);
        httpResponse tokenResponse = authenticationToken.getAuthenticationToken(ENV, personPhone);
        String token = tokenResponse.getBody(JSONObject.class).getString("token_type") + tokenResponse.getBody(JSONObject.class).getString("user_token");
        requestHeader.Add("Authorization", token);

        String URL = fdBenefitHost + sPath ;

        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(userAvailableCouponRequestBodyKey + bodyNumber);
        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }


}
