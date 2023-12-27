package APITestCase.EasyPromo;

import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;

import static com.alibaba.fastjson.JSON.parseObject;

public class refundCoupon {
    public String rhKey = "common";
    private String refundCouponHeaderConfigPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\RequestHeader.json";
    private String rType = "post";
    private String sPath = "/api/v4/coupons/refund_coupons";
    private String refundCouponRequestBodyKey = "RefundCoupon_TestBody_";
    private String ReqBodyPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\refundCouponRequestBody.json";

    public httpResponse refundCoupon(String easyPromoHost, String orderID, String bodyNumber) {
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, refundCouponHeaderConfigPath);
        String URL = easyPromoHost + sPath;
        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(refundCouponRequestBodyKey + bodyNumber);
        requestBody.put("order_id", orderID);
        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }
}
