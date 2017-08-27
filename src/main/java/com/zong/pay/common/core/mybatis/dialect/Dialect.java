package com.zong.pay.common.core.mybatis.dialect;

/**
 * @author 宗叶青 on 2017/8/21/21:06
 */
public abstract class Dialect {

    public boolean supportsLimit(){
        return false;
    }

    public boolean supportsLimitOffset(){
        return supportsLimit();
    }

    public String getLimitString(String sql, int offset, int limit){
        return getLimitString(sql, offset, Integer.toString(offset), limit, Integer.toString(limit));
    }

    public abstract String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder);
}
