package APITestCase.EasyPromo;

import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;

import static com.alibaba.fastjson.JSON.parseObject;

public class productEstimatePrices {
    public String rhKey = "common";
    private String productEstimatePricesHeaderConfigPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\RequestHeader.json";
    private String rType = "post";
    private String sPath = "/api/v4/quotation/product_estimate_prices";
    private String productEstimatePricesRequestBodyKey = "productEstimatePrices_TestBody_1";
    private String ReqBodyPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\productEstimatePricesRequestBody.json";


    public httpResponse productEstimatePrices(String easyPromoHost,String bodyNumber) {
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, productEstimatePricesHeaderConfigPath);
        String URL = easyPromoHost + sPath;
        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(productEstimatePricesRequestBodyKey + bodyNumber);
        httpResponse hs = httpData.transferRequest(URL, requestBody, requestHeader, rType);
        return hs;
    }
}
