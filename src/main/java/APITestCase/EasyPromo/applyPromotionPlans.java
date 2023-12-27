package APITestCase.EasyPromo;

import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;

import static com.alibaba.fastjson.JSON.parseObject;

public class applyPromotionPlans {
    public String rhKey = "common";
    private String applyPromotionHeaderConfigPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\RequestHeader.json";
    private String rType = "post";
    private String sPath = "/api/v4/quotation/apply_promotion_plans";
    private String applyPromotionRequestBodyKey = "ApplyPromotionPlans_TestBody_";
    private String ReqBodyPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\ApplyPromotionPlansRequestBody.json";
    public String orderID;

    public httpResponse applyPromotionPlans(String easyPromoHost, String mirrorCode, String bodyNumber) {
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, applyPromotionHeaderConfigPath);
        String URL = easyPromoHost + sPath;
        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(applyPromotionRequestBodyKey+bodyNumber);
        // "mirror_code": "d86418f9-4478-4a79-a17f-92a39eefa933",
        requestBody.put("mirror_code", mirrorCode);
        orderID = "cn"+commonFunction.getCurrentTimeUnix();
        requestBody.put("order_id", orderID);
        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }
}
