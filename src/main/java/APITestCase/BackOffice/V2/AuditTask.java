package APITestCase.BackOffice.V2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commonFunction;
import com.http.httpHeaders;
import com.http.httpResponse;
import xProject.httpData;
import com.dbConnect.mySQL;
import com.ssh.App;
import static com.alibaba.fastjson.JSON.parseObject;

public class AuditTask {
    private String APIPath = "/api/v1/admin/task/audit";
    private String rType = "post";

    public httpResponse approve(String url, String authorization, String rhKey, String rhPath, String flowID,String ReqByKey, String ReqBodyPath ){
        httpHeaders requestHeader = httpData.prepareHttpsHeader(rhKey, rhPath);
        requestHeader.Add("Authorization", authorization);

        JSONObject requestBody = parseObject(commonFunction.readJsonFile(ReqBodyPath)).getJSONObject(ReqByKey);
        JSONObject auditTask = (JSONObject)requestBody.getJSONArray("audit_task").get(0);
        auditTask.put("flow_id",flowID);

        String URL = url + APIPath ;
        httpResponse hs = httpData.transferRequest(URL,requestBody, requestHeader, rType);
        return hs;
    }
}
