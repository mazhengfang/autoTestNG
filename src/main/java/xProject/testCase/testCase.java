package xProject.testCase;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.commonFunction;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class testCase {
    String requestPath;
    String requestHeader;
    String requestBody;
    String requestParam;
    ExpectedData expectedData;
    String caseDesc;
    String caseName;

    @Data
    public static class ExpectedData {
        String code;
        String title;
        String detail;
        JSONArray data;
    }

    public int getCaseSize(String configPath, String path) {
        return (int) JSONPath.read(commonFunction.readJsonFile(configPath), path);
    }

    public JSONArray getCaseByPath(String configPath, String path) {
        return (JSONArray) JSONPath.read(commonFunction.readJsonFile(configPath), path);
    }
}

