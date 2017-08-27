package com.zong.pay.common.core.mybatis;

import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义Boolean类型转换器
 * java中的boolean和jdbc中的int之间的转换
 * @author 宗叶青 on 2017/8/21/20:24
 */
public class BooleanTypeHandler {

    public Object getResult(ResultSet arg0, int arg1) throws SQLException {
        int num = arg0.getInt(arg1);
        Boolean rt = Boolean.FALSE;
        if(num == 1){
            rt = Boolean.TRUE;
        }
        return rt;
    }

    public Object getResult(CallableStatement arg0, int arg1) throws SQLException {
        Boolean b = arg0.getBoolean(arg1);
        return b == true ? 1 : 0;
    }

    public void setParameter(PreparedStatement arg0, int arg1, Object arg2, JdbcType arg3) throws SQLException {
        Boolean b = (Boolean) arg2;
        int value = (Boolean) b == true ? 1 : 0;
        arg0.setInt(arg1, value);
    }

    public Object getResult(ResultSet arg0, String arg1) throws SQLException {
        int num = arg0.getInt(arg1);
        Boolean rt = Boolean.FALSE;
        if(num == 1){
            rt = Boolean.TRUE;
        }
        return rt;
    }
}
