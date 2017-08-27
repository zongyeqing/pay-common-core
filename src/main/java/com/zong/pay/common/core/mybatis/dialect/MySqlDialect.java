package com.zong.pay.common.core.mybatis.dialect;

/**
 * @author 宗叶青 on 2017/8/21/21:25
 */
public class MySqlDialect extends Dialect {

    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public boolean supportsLimitOffset() {
        return true;
    }

    @Override
    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
        if(offset > 0){
            sql += "limit" + offsetPlaceholder + "," + limitPlaceholder;
        }else{
            sql += "limit" + limitPlaceholder;
        }
        return sql;
    }
}
