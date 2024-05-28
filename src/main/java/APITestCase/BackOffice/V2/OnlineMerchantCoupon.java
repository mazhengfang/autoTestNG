package APITestCase.BackOffice.V2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;

import static com.alibaba.fastjson.JSON.parseObject;

public class OnlineMerchantCoupon {

    private String APIPath = "/api/v1/admin/online_merchant_coupon";
    private String rType = "post";
    private Long durationStartTime = Long.valueOf(190000);
    private Long durationEndTime = Long.valueOf(90000000);

    public httpResponse create(String url, String authorization,String rhKey, String rhPath, String ReqByKey, String ReqBodyPath ){
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhPath);
        requestHeader.Add("Authorization", authorization);

        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(ReqByKey);
        String reqNo = "couponCreateForm-85792b91-6df5-4766-b4f9" + String.valueOf(commonFunction.getCurrentTimeUnix()) ;
        requestBody.put("request_no",reqNo);
        Long currentTime = commonFunction.getCurrentTimeUnix();
        Long startTime = currentTime + durationStartTime;
        Long endTime = startTime + durationEndTime;
        JSONObject activity =requestBody.getJSONObject("activity") ;

        activity.put("available_begin_time", startTime);
        activity.put("available_end_time", endTime);
        activity.put("distri_start_time", startTime);
        activity.put("distri_end_time", endTime);

        String URL = url + APIPath;
        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }
}
