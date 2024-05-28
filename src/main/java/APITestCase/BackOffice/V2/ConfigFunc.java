package APITestCase.BackOffice.V2;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.report.report;
import static com.alibaba.fastjson.JSON.parseObject;

public class ConfigFunc {
    public final String configPath = "src/main/resources/GlobalConfigInfo/config.json";
    public String URL;
    public String authorization;
    public String env;
    public String DB;
    public String facadeURL;
    public String easyPromotionURL;
    public List<Map> testRltData;
    public report rpt;
    public JSONObject testScenario;
    public String stepName ;
    public String stepDescription ;
    public String expRlt ;
    public String actRlt ;
    public String testData ;
    public String personNumber;
    public String DBUser;
    public String DBPassword;
    public String filePath;


    public void init(String ENV) {
        this.env = ENV;
        this.URL = JSON.parseObject(com.commonFunction.readJsonFile(configPath)).getJSONObject(env).getString("EPInternalURL");
        this.authorization = parseObject(com.commonFunction.readJsonFile(configPath)).getJSONObject(env).getString("Authorization");
        this.DB = parseObject(com.commonFunction.readJsonFile(configPath)).getJSONObject(env).getString("DB");
        this.facadeURL = parseObject(com.commonFunction.readJsonFile(configPath)).getJSONObject(env).getString("FacadeHost");
        testRltData =  new ArrayList<>();
        rpt = new report();
        this.personNumber = JSON.parseObject(com.commonFunction.readJsonFile(configPath)).getJSONObject(env).getString("personNumber");
        this.DBUser = parseObject(com.commonFunction.readJsonFile(configPath)).getJSONObject(env).getString("DB_UserName");
        this.DBPassword = parseObject(com.commonFunction.readJsonFile(configPath)).getJSONObject(env).getString("DB_Password");
        this.filePath =  parseObject(com.commonFunction.readJsonFile(configPath)).getString("reportFilePath");
    }
}