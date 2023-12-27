package APITestCase.FacadePromo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.flowFunction;
import xProject.httpData;


public class loggedUserReceiveCoupon {
    private String rhKey = "RequestHeader";
    private String rhConfigPath = "src\\main\\resources\\ConfigInfo\\RequestHeader.json";
    private String rType = "post";
    private String sPath = "/facade_benefits/api/v4/coupons/";


    /**
     * 会员领取商家券 ：
     * 1.全场券
     * 2.单品券
     * @param stockID 传参，该数据需要进一步考虑：自动化 还是 手动创建1年数据 ？？？
     * @return 领券返回的数据结果
     */
    public httpResponse loggedUserReceiveCoupon_StockID(String Env, String fdBenefitHost, String personNumber,String stockID) {

        authenticationToken authenticationToken = new authenticationToken();
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhConfigPath);
        httpResponse tokenResponse = authenticationToken.getAuthenticationToken(Env,personNumber);
        String token = tokenResponse.getBody(JSONObject.class).getString("token_type") + tokenResponse.getBody(JSONObject.class).getString("user_token");
        requestHeader.Add("Authorization", token);
        requestHeader.Add("person-phone", personNumber);

        String URL = fdBenefitHost + sPath;
        JSONObject requestBody = new JSONObject();
        JSONArray value2 = new JSONArray();
        String key2 = "stock_ids";
        value2.add(stockID);
        requestBody.put(key2, value2);

        String key3 = "request_id";
        String value3 = "abc" + commonFunction.getCurrentTimeUnix();
        requestBody.put(key3, value3);

        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }

    public httpResponse loggedUserReceiveCoupon_ItemCode(String Env, String fdBenefitHost,String personPhone, String itemCode) {
        authenticationToken authenticationToken = new authenticationToken();
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhConfigPath);
        httpResponse tokenResponse = authenticationToken.getAuthenticationToken(Env,personPhone);
        String token = tokenResponse.getBody(JSONObject.class).getString("token_type") + tokenResponse.getBody(JSONObject.class).getString("user_token");
        String key = "Authorization";
        String value = token;
        requestHeader.Add(key, value);

        String key1 = "person-phone";
        String value1 = personPhone;
        requestHeader.Add(key1, value1);

        String URL = fdBenefitHost + sPath;
        JSONObject requestBody = new JSONObject();

        JSONArray Items = new JSONArray();
        JSONObject item = new JSONObject();
        String key2 = "item_code";
        item.put(key2,itemCode);
        Items.add(item);
        String key3 = "items";
        requestBody.put(key3, Items);

        String key4 = "request_id";
        String requestID = "9e7b290f-3a81-41a2-" + commonFunction.getCurrentTimeUnix();
        requestBody.put(key4, requestID);

        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }

}
