package APITestCase.FacadePromo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;


// 对应 微信小程序 -> 我的优惠券
public class queryUserCoupon {
    private String rhKey = "RequestHeader";
    private String rhConfigPath = "src\\main\\resources\\ConfigInfo\\RequestHeader.json";
    private String rType = "get";
    private String sPath = "/facade_benefits/api/v4/coupons?";
    private String personPhone = "13916485978";


    public httpResponse queryUserCoupon(String ENV, String fdBenefitHost, String couponStatus, String couponType, int pageOffset, int pageLimit, String personNumber) {
        authenticationToken authenticationToken = new authenticationToken();
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhConfigPath);
        httpResponse tokenResponse = authenticationToken.getAuthenticationToken(ENV,personNumber);
        String token = tokenResponse.getBody(JSONObject.class).getString("token_type") + tokenResponse.getBody(JSONObject.class).getString("user_token");
        requestHeader.Add("Authorization", token);

        String value1 = personNumber;
        requestHeader.Add("person-phone", value1);
        String URL = fdBenefitHost + sPath + "status=" + couponStatus + "&coupon_type=" + couponType + "&page_offset=" + pageOffset + "&page_limit=" + pageLimit;

        httpResponse hs = httpData.transferRequest(URL, null, requestHeader, rType);
        return hs;
    }

    public httpResponse queryDesignatedUserCoupon(String ENV, String fdBenefitHost, String couponStatus, String couponType, int pageOffset, int pageLimit, boolean status, String personNumber) {
        authenticationToken authenticationToken = new authenticationToken();
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhConfigPath);
        httpResponse tokenResponse = authenticationToken.getAuthenticationToken(ENV,personNumber);
        String token = tokenResponse.getBody(JSONObject.class).getString("token_type") + tokenResponse.getBody(JSONObject.class).getString("user_token");
        requestHeader.Add("Authorization", token);

        String value1 = personNumber;
        requestHeader.Add("person-phone", value1);
        String URL = fdBenefitHost + sPath + "status=" + couponStatus + "&coupon_type=" + couponType + "&page_offset=" + pageOffset + "&page_limit=" + pageLimit + "&get_unclaimed="+ status;

        httpResponse hs = httpData.transferRequest(URL, null, requestHeader, rType);
        return hs;
    }
//
//    public httpResponse queryNonDesignatedUserCoupon(String ENV, String fdBenefitHost, String couponStatus, String couponType, int pageOffset, int pageLimit, boolean status, String personNumber) {
//        authenticationToken authenticationToken = new authenticationToken();
//        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhConfigPath);
//        httpResponse tokenResponse = authenticationToken.getAuthenticationToken(ENV,personNumber);
//        String token = tokenResponse.getBody(JSONObject.class).getString("token_type") + tokenResponse.getBody(JSONObject.class).getString("user_token");
//        requestHeader.Add("Authorization", token);
//
//        String value1 = personNumber;
//        requestHeader.Add("person-phone", value1);
//        String URL = fdBenefitHost + sPath + "status=" + couponStatus + "&coupon_type=" + couponType + "&page_offset=" + pageOffset + "&page_limit=" + pageLimit + "&get_unclaimed="+ status;
//
//        httpResponse hs = httpData.transferRequest(URL, null, requestHeader, rType);
//        return hs;
//    }

    public JSONArray couponList(httpResponse rs, String Env, String facadePromoHost, String personPhone) {
        JSONArray couponList;
        JSONObject rsData = rs.getBody(JSONObject.class).getJSONObject("data");
        couponList = rsData.getJSONArray("coupons");
        int totalPage = rsData.getInteger("total_page");
        if (totalPage > 1) {
            for (int i = 2; i <= totalPage; i++
            ) {
                httpResponse responseResult = queryUserCoupon(Env, facadePromoHost, "AVAILABLE", "ALL", i, 5,personPhone);
                JSONObject rsOffsetData = responseResult.getBody(JSONObject.class).getJSONObject("data");
                JSONArray couponOffsetInfo = rsOffsetData.getJSONArray("coupons");
                for (Object couponInfo : couponOffsetInfo) {
                    couponList.add(couponInfo);
                }
            }
        }
        return couponList;
    }



}
