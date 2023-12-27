package APITestCase.EasyPromo;

import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;

import static com.alibaba.fastjson.JSON.parseObject;

public class cartEstimatePrices {
    public String rhKey = "common";
    private String cartEstimatePricesHeaderConfigPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\RequestHeader.json";
    private String rType = "post";
    private String sPath = "/api/v4/quotation/cart_estimate_prices";
    private String cartEstimatePricesRequestBodyKey = "cartEstimatePrices_TestBody_";
    private String ReqBodyPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\cartEstimatePricesRequestBody.json";


    public httpResponse cartEstimatePrices(String easyPromoHost, String bodyNumber) {
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, cartEstimatePricesHeaderConfigPath);
        String URL = easyPromoHost + sPath;
        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(cartEstimatePricesRequestBodyKey + bodyNumber);
        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }
}
