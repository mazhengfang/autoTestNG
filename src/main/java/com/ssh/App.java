package com.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class App {
    Session session;

    public void connect() throws Exception {
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking","no");
        JSch jsch = new JSch();
        String profile = "ZMA23";
        String password = "Shanghai=2024";
//        Session session = jsch.getSession("wallixusr@local@IFGWN1ADM01:SSH:zma23", "gateway-bastion-azr-cne2.dktapp.cloud", 22);
        session = jsch.getSession("wallixusr@local@IFGWN1ADM01:SSH:ZMA23", "gateway-bastion-azr-cne2.dktapp.cloud", 22);
        session.setPassword(password);
//        localUserInfo lui = new localUserInfo();
//        session.setUserInfo(lui);
        session.setConfig(config);
        session.connect();
        //依次添加需要做转发的端口地址
//        session.setPortForwardingL(15432, "fcddv1pgs11.cn-e.azr.dktinfra.cloud", 60901);
//        session.setPortForwardingL(13306, "epo-mysql-rds01-ppd.mysql.database.chinacloudapi.cn", 3306);
        session.setPortForwardingL(13306, "epo-mysql-rds03-ppd.mysql.database.chinacloudapi.cn", 3306);
        System.out.println("SSH connected");

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