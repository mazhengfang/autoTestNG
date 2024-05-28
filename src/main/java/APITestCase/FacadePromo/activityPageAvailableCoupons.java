package APITestCase.FacadePromo;

import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;

import static com.alibaba.fastjson.JSON.parseObject;

public class activityPageAvailableCoupons {
    public String rhKey = "activityPageAvailableCoupons";
    private String h5gHeaderConfigPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\RequestHeader.json";
    private String rType = "post";
    private String sPath = "/facade_benefits/api/v4/coupon/activity_page_available_coupons";
    private String h5RequestBodyKey = "activityPageAvailableCoupons";
    private String ReqBodyPath = "src/main/resources/ConfigInfo/FacadePromo/activityPageAvailableCouponsRequestBody.json";

    public httpResponse distributeChannelSearch(String ENV, String facadeHost, String personPhone) {
        authenticationToken authenticationToken = new authenticationToken();
        String token = null;
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, h5gHeaderConfigPath);

        httpResponse tokenResponse = authenticationToken.getAuthenticationToken(ENV,personPhone);
        token = tokenResponse.getBody(JSONObject.class).getString("token_type") + tokenResponse.getBody(JSONObject.class).getString("user_token");
        String key = "Authorization";
        String value = token;
        requestHeader.Add(key, value);

        String URL = facadeHost + sPath;
        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(h5RequestBodyKey);

        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }

}
