package APITestCase.FacadePromo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class crossFunctions {

    public boolean couponNotEffective(JSONArray couponList, String expStockID) {
        int ct = 0;
        for (Object obj : couponList) {
            String stockID = String.valueOf(((JSONObject) obj).get("stock_id"));
            if (stockID.equals(expStockID)) {
                ct++;
            }
        }
        if (ct > 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean couponEffective(JSONArray couponList, String expStockID) {
        int ct = 0;
        for (Object obj : couponList) {
            String stockID = String.valueOf(((JSONObject) obj).get("stock_id"));
            if (stockID.equals(expStockID)) {
                ct++;
            }
        }
        if (ct == 1) {
            return true;
        } else {
            return false;
        }
    }



    public  String couponStatus(JSONArray couponList, String expStockID) {
        String couponStatus = null;
        for (Object obj : couponList) {
            String stockID = String.valueOf(((JSONObject) obj).get("stock_id"));
            if (stockID.equals(expStockID)) {
                couponStatus = (String) ((JSONObject) obj).get("coupon_status");
                break;
            }
        }
       return couponStatus;
    }

    public boolean couponCodeValidation(JSONArray couponList, String stockID, String couponCode, String couponStatus) {
        int cnt = 0;
        for (Object data : couponList) {
            String actStockID = ((JSONObject) data).getString("stock_id");
            String actCouponCode = ((JSONObject) data).getString("coupon_code");
            String actCouponStatus = ((JSONObject) data).getString("coupon_status");
//            System.out.println(actStockID + " : " +actCouponStatus);
            if (actCouponCode == null) {
                if (actStockID.equals(stockID) && couponCode == null&& actCouponStatus.equals(couponStatus)) {
                    cnt++;
                }
            } else {
                if (actStockID.equals(stockID) && actCouponCode.equals(couponCode) && actCouponStatus.equals(couponStatus)) {
                    cnt++;
                }
            }

        }

        if (cnt == 1) return true;
        else return false;
    }
}
