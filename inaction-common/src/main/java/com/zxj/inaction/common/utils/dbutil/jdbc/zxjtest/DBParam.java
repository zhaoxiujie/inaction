package com.zxj.inaction.common.utils.dbutil.jdbc.zxjtest;

import lombok.Data;

@Data
public class DBParam {
    //数据库链接
    private String dbUrl;

    //数据库用户名
    private String user;

    //数据库密码
    private String password;

    //sql语句
    private String sql;

}
