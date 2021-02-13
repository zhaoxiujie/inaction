package com.zxj.inaction.common.utils.dbutil.jdbc.zxjtest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DBConnection {
    private static Logger logger= LoggerFactory.getLogger(DBConnection.class);

    private static Map<String, Statement> statementMap=new HashMap<>();
    private static Map<String, Connection> connectionMap=new HashMap<>();

    public static synchronized Statement getDBStatement(DBParam dbParam){
        String key= StringUtils.split(dbParam.getDbUrl(),"?")[0];
        try {
            if (!statementMap.containsKey(key)){
                Connection connection;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection= DriverManager.getConnection(dbParam.getDbUrl(),dbParam.getUser(),dbParam.getPassword());
                }catch (Exception ex){
                    logger.error(ex.getMessage());
                    throw new Exception("创建数据库链接失败，请确认数据库链接url及用户名密码是否正确");
                }
                if (connection!=null){
                    CleanRunnable cleanRunnable=new CleanRunnable(key);
                    cleanRunnable.start();
                    connectionMap.put(key,connection);
                    statementMap.put(key,connection.createStatement());
                }
            }else {
                CleanRunnable.updateTimeStamp(key);
            }
            return statementMap.get(key);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    static synchronized void destroyConnection(String dbUrlKey){
        try {
            if (statementMap.containsKey(dbUrlKey)){
                statementMap.get(dbUrlKey).close();
            }
        }catch (Exception ex){
            logger.error(dbUrlKey,ex);
        }finally {
            statementMap.remove(dbUrlKey);
        }

        try {
            if (connectionMap.containsKey(dbUrlKey)){
                connectionMap.get(dbUrlKey).close();
            }
        }catch (Exception ex){
            logger.error(dbUrlKey,ex);
        }finally {
            connectionMap.remove(dbUrlKey);
        }
    }
}
