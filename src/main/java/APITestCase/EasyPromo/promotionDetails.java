package APITestCase.EasyPromo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;

import static com.alibaba.fastjson.JSON.parseObject;

public class promotionDetails {
    public String rhKey = "common";
    private String promotionDetailsHeaderConfigPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\RequestHeader.json";
    private String rType = "post";
    private String sPath = "/api/v4/quotation/promotion_details";
    private String promotionDetailsRequestBodyKey = "PromotionDetails_TestBody_";
    private String ReqBodyPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\PromotionDetailsRequestBody.json";


    public httpResponse promotionDetails(String easyPromoHost, String orderID,String bodyNumber) {
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, promotionDetailsHeaderConfigPath);
        String URL = easyPromoHost + sPath;
        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(promotionDetailsRequestBodyKey+bodyNumber);
        // "mirror_code": "d86418f9-4478-4a79-a17f-92a39eefa933",
        requestBody.put("order_id", orderID);
        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }



}
