package APITestCase.EasyPromo;

import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;

import static com.alibaba.fastjson.JSON.parseObject;

public class redeemCoupon {
    public String rhKey = "common";
    private String redeemCouponHeaderConfigPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\RequestHeader.json";
    private String rType = "post";
    private String sPath = "/api/v4/coupons/redeem_coupons";
    private String redeemCouponRequestBodyKey = "RedeemCoupon_TestBody_";
    private String ReqBodyPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\redeemCouponRequestBody.json";


    public httpResponse redeemCoupon(String easyPromoHost, String orderID, String bodyNumber) {
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, redeemCouponHeaderConfigPath);
        String URL = easyPromoHost + sPath;
        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(redeemCouponRequestBodyKey+ bodyNumber);
        requestBody.put("order_id", orderID);
        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }
}
