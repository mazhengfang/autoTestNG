package APITestCase.EasyPromo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;

import static com.alibaba.fastjson.JSON.parseObject;

public class appendPromotion {

        public String rhKey = "common";
        private String appendPromotionHeaderConfigPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\RequestHeader.json";
        private String rType = "post";
        private String sPath = "/api/v4/quotation/append_promotions";
        private String appendPromotionRequestBodyKey = "AppendPromotion_TestBody_1";
        private String ReqBodyPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\AppendPromotionRequestBody.json";


        public httpResponse appendPromotion(String easyPromoHost, String orderID) {
            httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, appendPromotionHeaderConfigPath);
            String URL = easyPromoHost + sPath;
            JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(appendPromotionRequestBodyKey);
            // "mirror_code": "d86418f9-4478-4a79-a17f-92a39eefa933",
            requestBody.put("order_id", orderID);
            String couponCode = "cc"+commonFunction.getCurrentTimeUnix();
            JSONObject couponInfo = (JSONObject) requestBody.getJSONArray("coupons").get(0);
            couponInfo.put("coupon_code",couponCode);
            httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
            return hs;
        }

}
