package APITestCase.BackOffice.V2.DB;

import com.alibaba.fastjson.JSONObject;
import com.dbConnect.mySQL;
import com.ssh.App;

import static org.testng.Assert.assertEquals;


public class Table {

    com.dbConnect.mySQL mySQL;
    App app;


    public JSONObject ep_workflow_instance(String DB,String user, String password, String sql) throws Exception {
//        app = new App();
//        app.connect();
        mySQL = new mySQL();
        JSONObject dbRecord =mySQL.record(DB,user,password,"ep_workflow_instance",sql);
//        app.disconnect();
        return dbRecord;
    }

    public JSONObject ep_coupon_stock(String DB,String user, String password,String sql) throws Exception {
//        app = new App();
//        app.connect();
        mySQL = new mySQL();
        JSONObject dbRecord =mySQL.record(DB,user,password,"ep_coupon_stock",sql);
//        app.disconnect();
        return dbRecord;
    }

    public JSONObject ep_workflow_instance_audit_item(String DB,String user, String password,String sql) throws Exception {
//        app = new App();
//        app.connect();
        mySQL = new mySQL();
        JSONObject dbRecord =mySQL.record(DB,user,password,"ep_workflow_instance_audit_item",sql);
//        app.disconnect();
        return dbRecord;
    }

    public JSONObject coupon(String DB,String user, String password,String sql) throws Exception {
//        app = new App();
//        app.connect();
        mySQL = new mySQL();
        JSONObject dbRecord =mySQL.record(DB,user,password,"coupon",sql);
//        app.disconnect();
        return dbRecord;
    }

    public JSONObject ep_activity(String DB,String user, String password,String sql) throws Exception {
//        app = new App();
//        app.connect();
        mySQL = new mySQL();
        JSONObject dbRecord =mySQL.record(DB,user,password,"ep_activity",sql);
//        app.disconnect();
        return dbRecord;
    }

}
