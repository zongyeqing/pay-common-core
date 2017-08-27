package com.zong.pay.common.core.mybatis.interceptor;

import org.apache.ibatis.mapping.*;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.plugin.Interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 宗叶青 on 2017/8/20/23:15
 */
public abstract class AbstractInterceptor implements Interceptor{
    protected MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource, boolean isCount){
        Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        String[] s = ms.getKeyProperties();
        if(s == null){
            builder.keyProperty(null);
        }else{
            builder.keyProperty(s[0]);
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        if(isCount){
            List<ResultMap> resultMaps= new ArrayList<>();
            resultMaps.add(new ResultMap.Builder(ms.getConfiguration(), ms.getId(), Integer.class, new ArrayList<ResultMapping>()).build());
            builder.resultMaps(resultMaps);
        }else{
            builder.resultMaps(ms.getResultMaps());
        }
        builder.cache(ms.getCache());
        MappedStatement newMs = builder.build();
        return newMs;
    }

    public static class BoundSqlSqlSource implements SqlSource{

        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql){
            this.boundSql = boundSql;
        }
        @Override
        public BoundSql getBoundSql(Object o) {
            return boundSql;
        }
    }
}
