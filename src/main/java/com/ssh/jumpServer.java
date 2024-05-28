package com.ssh;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

import java.sql.*;

import static java.lang.Class.forName;

public class jumpServer {
    Session session;
    public void connect() throws Exception {
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking","no");
        JSch jsch = new JSch();
        String profile = "ZMA23";
        String password = "Shanghai=2024";
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        Connection conn = null;
        Statement stmt = null;
        String user = "zma23@epo-mysql-rds03-ppd";
        String dbPassword = "e4rsnfUdTp[V";
        String url = "jdbc:mysql://127.0.0.1:13306/epo_ep_pp?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        session = jsch.getSession(profile, "jumpserver-azr-pr.dktapp.cloud", 33061);
        session.setPassword(password);
        session.setConfig(config);
        session.connect();
        session.setPortForwardingL(13306, "epo-mysql-rds03-ppd.mysql.database.chinacloudapi.cn", 3306);
//        System.out.println("SSH connected");

        Class.forName(JDBC_DRIVER);

        conn = DriverManager.getConnection(url, user, dbPassword);
        // 创建Statement
        stmt = conn.createStatement();

        // 执行查询并获取结果
        ResultSet rs = stmt.executeQuery("select * from coupon where stock_id  =\"2403250021100002\"");

        // 处理结果
        while (rs.next()) {
            // 根据你的表结构，获取字段
            System.out.println(rs.getString("code"));
        }

    }
    public void disconnect() throws Exception {
        session.disconnect();
    }
    class localUserInfo implements UserInfo {
        String passwd;

        public String getPassword() {
            return passwd;
        }

        public boolean promptYesNo(String str) {
            return true;
        }

        public String getPassphrase() {
            return null;
        }

        public boolean promptPassphrase(String message) {
            return true;
        }

        public boolean promptPassword(String message) {
            return true;
        }

        public void showMessage(String message) {
        }
    }
}
