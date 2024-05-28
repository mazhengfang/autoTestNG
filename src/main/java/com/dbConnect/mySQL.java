package com.dbConnect;


import com.alibaba.fastjson.JSONObject;
import com.ssh.App;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.parseObject;

public class mySQL {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    static final String url = "jdbc:mysql://127.0.0.1:13306/epo_ep_pp?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String url = "jdbc:mysql://jumpserver-azr.pp.dktapp.cloud:33061/epo_ep_pp?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

//    static final String user = "epo_app_pp@epo-mysql-rds01-ppd";
//    static final String user = "epo_app_pp@epo-mysql-rds03-ppd";
//    static final String password = "5SjAzTZepEbSkxs8";
    static final String user = "e20bd439-86a6-49cf-915c-1ee4a124181e";
    static final String password = "FBJFbS63kSTGcaHw";
    Connection conn = null;
    Statement stmt = null;

    public void dbMySQLConnect(String user, String password) throws InterruptedException, ClassNotFoundException, SQLException {

        int MAX_RETRIES = 5;
        Class.forName(JDBC_DRIVER);
        for (int i = 0; i <= MAX_RETRIES; i++) {
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
//                e.printStackTrace();
                Thread.sleep(5000);
                if (i == MAX_RETRIES) throw e;
            }
        }

        // if (!conn.isClosed())
        // System.out.println("Succeed connecting to DB.");
        if (conn.isClosed())
            System.out.println(" connecting to DB is closed.");
    }

    public void dbMySQLClose() throws SQLException {

        conn.close();
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("Close DB.");
    }

    public List<String> getTableField(String dataBase, String tableName) throws SQLException, IOException {
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet resultSet = metaData.getColumns(dataBase, null, tableName, null);
        ArrayList<String> columns = new ArrayList<>();
        while (resultSet.next()) {
//            数据库名
            String db_name = resultSet.getString(".TABLE_CAT");
//            表名
            String table_name = resultSet.getString(".TABLE_NAME");
//            获取字段名
            String field = resultSet.getString(".COLUMN_NAME");
//            获取字段类型
            String fieldType = resultSet.getString(".TYPE_NAME");
            String fieldLength = resultSet.getString(".COLUMN_SIZE");
            String fieldDESC = resultSet.getString(".REMARKS");
            String info = String.format("[%s->%s->%s->%s->%s->%s]", db_name, table_name, field, fieldType, fieldLength, fieldDESC);
//            System.out.println(info);
//            OutputStreamWriter writer = new OutputStreamWriter(System.out, "UTF-8");
//            // 输出中文字符
//            writer.write(info);
            columns.add(field);
        }
        return columns;
    }


    public JSONObject getRecords(String sql, List<String> columns) throws SQLException {
        JSONObject record = new JSONObject();
        int cnt = columns.size();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (rs.next()) {
            for (int i = 0; i < cnt; i++
            ) {
                record.put(columns.get(i), rs.getString(columns.get(i)));

//                String info = String.format("[%s->%s]", columns.get(i), rs.getString(columns.get(i)));
//                System.out.println(info);
            }

        }

        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stmt.close();
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    public List<String> getDatabase() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet resultset = metaData.getCatalogs();
        while (resultset.next()) {
            String string = resultset.getString("TABLE_CAT");
            list.add(string);
        }
        return list;
    }


    public List<String> getTables(String database) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(database, null, "%", new String[]{"TABLE", "VIEW"});
            while (tables.next()) {
                String string = tables.getString("TABLE_NAME");
                list.add(string);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public JSONObject record(String DB,String user, String password, String tableName, String sql) throws Exception {

//        mySQL mySQL = new mySQL();
//        mySQL.dbMySQLConnect();

        dbMySQLConnect(user,password);

//        List<String> e = b.getTableField("epo_ep_pp","coupon");
//        List<String> tableField = mySQL.getTableField(DB, tableName);
//        JSONObject record = mySQL.getRecords(sql, tableField);
        List<String> tableField = getTableField(DB, tableName);
        JSONObject record = getRecords(sql, tableField);
//        System.out.println(record.getString("stock_id") +" " + record.getString("code") +" " + record.getString("status"));
//        mySQL.dbMySQLClose();
       dbMySQLClose();

        return record;
    }


}

