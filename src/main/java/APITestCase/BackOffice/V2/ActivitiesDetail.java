package APITestCase.BackOffice.V2;

import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;

import static com.alibaba.fastjson.JSON.parseObject;

public class ActivitiesDetail {
    private String APIPath = "/api/v1/admin/activities/";
    private String getType = "get";
    private String patchType = "patch";
    private String ReqBodyPath = "src/main/resources/ConfigInfo/BackOffice/createOnlineMerchantCoupon/requestBody.json";
    private String ReqByKey = "CloseActivity";

    public httpResponse get(String url, String authorization, String rhKey, String rhPath, String activityID ){
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhPath);
        requestHeader.Add("Authorization", authorization);
        String URL = url + APIPath + activityID;
        httpResponse hs = httpData.transferRequest(URL,null, requestHeader, getType);
        return hs;
    }

    public httpResponse patch(String url, String authorization, String rhKey, String rhPath, String activityID ){

        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhPath);
        requestHeader.Add("Authorization", authorization);
        String URL = url + APIPath + activityID;
        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(ReqByKey);
        httpResponse hs = httpData.transferRequest(URL,requestBody, requestHeader, patchType);
        return hs;
    }
}
