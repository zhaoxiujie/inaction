package com.zxj.inaction.common.utils.dbutil.jdbc.zxjtest;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection_bak {
    private static final Logger logger= LoggerFactory.getLogger(DBConnection_bak.class);

    public static synchronized Statement getDBStatement(DBParam dbParam){
        String key= StringUtils.split(dbParam.getDbUrl(),"?")[0];
        try {
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
            }
            return connection.createStatement();

        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    static synchronized void destroyConnection(Connection connection){
        try {
            connection.close();
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }finally {
            try {
                if (connection!=null){
                    connection.close();
                }
            }catch (Exception ex){
                logger.error(ex.getMessage());
            }
        }
    }

}
