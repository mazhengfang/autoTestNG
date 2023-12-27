package xProject.testCase;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;
import com.commonFunction;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class testCaseTemplateOne {
    String key;


    public int getCaseSize(String configPath, String path) {
        return (int) JSONPath.read(commonFunction.readJsonFile(configPath), path);
    }

    public JSONArray getCaseByPath(String configPath, String path) {
        return (JSONArray) JSONPath.read(commonFunction.readJsonFile(configPath), path);
    }
}
