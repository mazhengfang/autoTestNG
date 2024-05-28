package APITestCase.EasyPromo;

import APITestCase.BackOffice.V2.OnlineMerchantCoupon;
import APITestCase.FacadePromo.authenticationToken;
import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;

import static com.alibaba.fastjson.JSON.parseObject;

public class confirmPromotionPlans {
    public String rhKey = "common";
    private String confirmPromoPlansHeaderConfigPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\RequestHeader.json";
    private String rType = "post";
    private String sPath = "/api/v4/quotation/confirm_promotion_plans";
    private String confirmPromoPlansRequestBodyKey = "conformPromotionPlans_TestBody_";
    private String confirmPromoPlansReqBodyPath = "src/main/resources/ConfigInfo/EasyPromo/confirmPromotionPlansRequestBody.json";
//    public String planIndex;
    public String orderID;


    public httpResponse confirmPromotionPlans(String ENV, String easyPromoHost, String bodyNumber,String planIndex, String personPhone) {
        authenticationToken authenticationToken = new authenticationToken();
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, confirmPromoPlansHeaderConfigPath);
        String token = null;
        httpResponse tokenResponse = authenticationToken.getAuthenticationToken(ENV,personPhone);
        token = tokenResponse.getBody(JSONObject.class).getString("token_type") + tokenResponse.getBody(JSONObject.class).getString("user_token");
        String key = "Authorization";
        String value = token;
        requestHeader.Add(key, value);

        String URL = easyPromoHost + sPath;
        JSONObject requestBody = parseObject(commonFunction.readJsonFile(confirmPromoPlansReqBodyPath)).getJSONObject(confirmPromoPlansRequestBodyKey+bodyNumber);
        requestBody.put("plan_index", planIndex);

        orderID = "cn"+commonFunction.getCurrentTimeUnix();
        requestBody.put("order_id", orderID);

        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }
}




