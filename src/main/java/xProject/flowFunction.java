package xProject;

import com.alibaba.fastjson.JSON;
import com.commonFunction;
import xProject.testResult.actionResult;


import static com.alibaba.fastjson.JSON.parseObject;

public class flowFunction {

    public final String globalConfigPath = "src\\main\\resources\\GlobalConfigInfo\\ENV.json";
    public String env;
    public String facadeBenefitHost;
    public String easyPromoHost;
    public String TokenHttpsReqBodyPath ;
    public String TokenHttpsReqHeaderPath ;
    public String TokenHostURL ;
    public String TokenHttpsHeaderKey;
    public String TokenRequestBodyKey;
    public String TokenRequestBodyKey2;
    public String easyPromoInternalHost;


    public flowFunction() {

    }

    public void init(String ENV) {
        this.env = ENV;
        this.facadeBenefitHost = JSON.parseObject(commonFunction.readJsonFile(globalConfigPath)).getJSONObject(ENV).getString("FacadeBenefitHost");
        this.easyPromoHost = parseObject(commonFunction.readJsonFile(globalConfigPath)).getJSONObject(ENV).getString("easyPromoHost");
        this.easyPromoInternalHost = parseObject(commonFunction.readJsonFile(globalConfigPath)).getJSONObject(ENV).getString("easyPromoInternalHost");
        this.TokenHostURL = parseObject(commonFunction.readJsonFile(globalConfigPath)).getJSONObject(ENV).getString("TokenHostURL");
        this.TokenHttpsHeaderKey= parseObject(commonFunction.readJsonFile(globalConfigPath)).getJSONObject(ENV).getString("TokenRequestHeaderKey");
        this.TokenHttpsReqHeaderPath = parseObject(commonFunction.readJsonFile(globalConfigPath)).getJSONObject(ENV).getString("TokenRequestHeaderPath");
        this.TokenHttpsReqBodyPath = parseObject(commonFunction.readJsonFile(globalConfigPath)).getJSONObject(ENV).getString("RequestBodyPath");
        this.TokenRequestBodyKey = parseObject(commonFunction.readJsonFile(globalConfigPath)).getJSONObject(ENV).getString("TokenRequestBodyKey");
        this.TokenRequestBodyKey2 = parseObject(commonFunction.readJsonFile(globalConfigPath)).getJSONObject(ENV).getString("TokenRequestBodyKey2");
    }
    public actionResult testResult (boolean stepRlt){
        actionResult testRlt;
        if(stepRlt){
            testRlt = actionResult.Pass;
        } else{
            testRlt = actionResult.Fail;
        }
        return testRlt;
    }

}
