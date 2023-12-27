package xProject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import static com.alibaba.fastjson.JSON.parseObject;

import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpRequest;
import com.http.httpResponse;


public class httpData {


    /**
     * 2023.1.3 Ma Zhg-Fg:  prepare https request header
     *
     * @param hdKy
     * @return
     */
    public static httpHeaders prepareHttpsHeader(String hdKy, String rhConfigPath) {

        JSONArray requestHeader = parseObject(commonFunction.readJsonFile(rhConfigPath)).getJSONArray(hdKy);
        httpHeaders header = new httpHeaders();
        requestHeader.<JSONObject>forEach(data -> {
            String key = ((JSONObject) data).getString("name");
            String value = ((JSONObject) data).getString("value");
            header.Add(key, value);
        });
        return header;
    }

//    public static String getToken(String url, String rhKey, String rhKeyPath, String rbKey, String rbKeyPath, String rType, boolean tokenSts) {
//        String token = "";
//
//        httpHeaders headers = prepareHttpsHeader(rhKey, rhKeyPath, tokenSts);
//        JSONObject requestBody = prepareRequestBody(rbKey, rbKeyPath);
//        httpResponse hs = transferRequest(url, requestBody, headers, rType);
//
//        if (hs.getCode() == 200)
//            token = hs.getBody(JSONObject.class).getString("token_type") + hs.getBody(JSONObject.class).getString("user_token");
//
//        return token;
//    }

    public static JSONObject prepareRequestBody(String bdKy, String bdKyPath) {
        JSONObject requestBody = new JSONObject();
        JSONArray requestBy = parseObject(commonFunction.readJsonFile(bdKyPath)).getJSONArray(bdKy);

        requestBy.<JSONObject>forEach(data -> {
            String key = ((JSONObject) data).getString("name");
            String value = ((JSONObject) data).getString("value");
            requestBody.put(key, value);
        });
        return requestBody;
    }



    public static httpResponse transferRequest(String url, JSONObject requestJO, httpHeaders head, String requestType) {
        httpRequest hr = null;
        if (!(head == null))
            hr = new httpRequest(head);
        else
            hr = new httpRequest();
        httpResponse hs = null;
        int retryNum = 4; //设定超时重试次数
        do {
            switch (requestType) {
                case "post":
                    hs = hr.httpPost(url, requestJO, 30);
                    break;
                case "postString":
                    hs = hr.httpPost_String(url, requestJO.toString(), head.get("Content-Type"), 30);
                    break;
                case "get":
                    hs = hr.httpGet(url);
                    break;
                case "delete":
                    hs = hr.httpDelete(url);
                    break;
                case "patchString":
                    hs = hr.httpPatch_String(url, requestJO.toString(), head.get("Content-Type"), 30);
                    break;
                case "patch":
                    hs = hr.httpPatch(url, requestJO);
                    break;
                default:
                    break;
            }
            retryNum--;
        } while (
                retryNum > 0 &&
                        (hs.getBody(String.class).indexOf("timed out") != -1
                        )
        );
        if (retryNum < 3)
            System.out.println("总共重试" + (4 - retryNum - 1) + "次!");
        return hs;
    }
}
