package APITestCase.BackOffice.V2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;

import static com.alibaba.fastjson.JSON.parseObject;

public class PromotionPage {
    public String rhKey = "common";
    private String promotionPageHeaderConfigPath = "src\\main\\resources\\ConfigInfo\\EasyPromo\\RequestHeader.json";
    private String ReqBodyPath = "src\\main\\resources\\ConfigInfo\\BackOffice\\PromotionPageRequestBody.json";
    private String promotionPageRequestBodyKey = "PromotionPage_Create_TestBody_1";
    private String promotionInfoRequestBodyKey = "PromotionPage_Info_TestBody_1";


    private String APIPath = "/api/v1/admin/promotion/create";
    private String promotionInfoAPIPath = "/api/v4/promotion/promotion_info";
    private String rType = "post";
    private Long durationStartTime = Long.valueOf(3600000);
    Long currentTime = commonFunction.getCurrentTimeUnix();
    Long endTime = currentTime + durationStartTime;


    public httpResponse create(String url, String authorization, String rhKey, String rhPath, String StockID, String ReqByKey, String ReqBodyPath ){
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhPath);
        requestHeader.Add("Authorization", authorization);

        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(ReqByKey);

        org.json.JSONArray stockIDs = new org.json.JSONArray();
        stockIDs.put(StockID);
        requestBody.put("stock_ids",stockIDs);
        requestBody.put("page_end_time",endTime);
        requestBody.put("name","Auto" + StockID);

        String URL = url + APIPath ;
        httpResponse hs = httpData.transferRequest(URL,requestBody, requestHeader, rType);
        return hs;
    }


    public httpResponse info(String url, String authorization, String rhKey, String rhPath, String ReqByKey, String ReqBodyPath,String pageCode ){
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhPath);
        requestHeader.Add("Authorization", authorization);
        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(ReqByKey);
        requestBody.put("page_code",pageCode);
        String URL = url + promotionInfoAPIPath ;
        httpResponse hs = httpData.transferRequest(URL,requestBody, requestHeader, rType);
        return hs;
    }


    public String claimableStatus(JSONArray couponList, String expStockID) {
        String claimable = null;
        for (Object obj : couponList) {
            String stockID = String.valueOf(((JSONObject) obj).get("stock_id"));

            if (stockID.equals(expStockID)) {
                claimable = String.valueOf(((JSONObject) obj).get("claimable"));
                break;
            }
        }
        return claimable;
    }


    public String couponStatus(JSONArray couponList, String expStockID) {
        String claimable = null;
        for (Object obj : couponList) {
            String stockID = String.valueOf(((JSONObject) obj).get("stock_id"));

            if (stockID.equals(expStockID)) {
                claimable = String.valueOf(((JSONObject) obj).get("coupon_status"));
                break;
            }
        }
        return claimable;
    }

}
