package APITestCase.FacadePromo;

import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.flowFunction;
import xProject.httpData;

public class authenticationToken {
    public String tokenHeaderKey;
    public String tokenHeaderPath;
    public String tokenBodyKey;
    public String tokenBodyPath;
    public String tokenURL;
    public String rType = "post";
    public xProject.httpData httpData;
    public commonFunction functionBaseAPICommon;
    public xProject.flowFunction flowBaseAPICommon;

    public authenticationToken() {
        //do nothing
    }

    public httpResponse getAuthenticationToken(String ENV, String TokenRequestBodyKey){
        this.flowBaseAPICommon = new flowFunction();
        flowBaseAPICommon.init(ENV);
        this.tokenHeaderKey = flowBaseAPICommon.TokenHttpsHeaderKey;
        this.tokenHeaderPath =  flowBaseAPICommon.TokenHttpsReqHeaderPath;
        if(TokenRequestBodyKey.equals("13916485978")){
            this.tokenBodyKey = flowBaseAPICommon.TokenRequestBodyKey;
        } else {
            this.tokenBodyKey = flowBaseAPICommon.TokenRequestBodyKey2;
        }

        this.tokenBodyPath = flowBaseAPICommon.TokenHttpsReqBodyPath;
        this.httpData = new httpData();
        this.tokenURL = flowBaseAPICommon.TokenHostURL;
        httpHeaders requestHeader = httpData.prepareHttpsHeader(tokenHeaderKey, tokenHeaderPath);
        JSONObject requestBody =  httpData.prepareRequestBody(tokenBodyKey, tokenBodyPath);
        httpResponse hs = httpData.transferRequest(tokenURL, requestBody, requestHeader, rType);
        return hs;
    }
//    public httpResponse getAuthenticationToken2(String ENV){
//        this.flowBaseAPICommon = new flowFunction();
//        flowBaseAPICommon.init(ENV);
//        this.tokenHeaderKey = flowBaseAPICommon.TokenHttpsHeaderKey;
//        this.tokenHeaderPath =  flowBaseAPICommon.TokenHttpsReqHeaderPath;
//        this.tokenBodyKey = flowBaseAPICommon.TokenRequestBodyKey2;
//        this.tokenBodyPath = flowBaseAPICommon.TokenHttpsReqBodyPath;
//        this.httpData = new httpData();
//        this.tokenURL = flowBaseAPICommon.TokenHostURL;
//        httpHeaders requestHeader = httpData.prepareHttpsHeader(tokenHeaderKey, tokenHeaderPath);
//        JSONObject requestBody =  httpData.prepareRequestBody(tokenBodyKey, tokenBodyPath);
//        httpResponse hs = httpData.transferRequest(tokenURL, requestBody, requestHeader, rType);
//        return hs;
//    }


}
