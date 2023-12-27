package APITestCase.BackOffice;

import com.http.httpResponse;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import xProject.flowFunction;
import xProject.httpData;

import static com.alibaba.fastjson.JSON.parseObject;

public class distributeActivity {

    public String rhKey = "RequestHeader_Activity";
    public String rhConfigPath = "src\\main\\resources\\ConfigInfo\\RequestHeader.json";
    public String rType = "post";
    public xProject.flowFunction flowBaseAPICommon;
    public String easyPromoInternalHost;
    public String sPath = "/api/v4/job/distribute";
//    public String activityId = "10750";
//    public String sVar = "/audits?status=APPROVED";
    public String Env;


    @BeforeSuite
    @Parameters({"ENV"})
    public void init(String ENV) {
        this.Env = ENV;
        this.flowBaseAPICommon = new flowFunction();
        flowBaseAPICommon.init(ENV);
        this.easyPromoInternalHost = flowBaseAPICommon.easyPromoInternalHost;

    }


    public httpResponse distributeActivity() {

        String URL = easyPromoInternalHost + sPath;
        httpResponse hs = httpData.transferRequest(URL, null, null, rType);
        return hs;
    }

    @Test
    public void distributeActivity_FunctionTest_1() {
        httpResponse hs = distributeActivity();
        System.out.println(hs.getCode());
    }

}
