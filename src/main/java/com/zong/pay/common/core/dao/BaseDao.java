package com.zong.pay.common.core.dao;

import com.zong.paycommon.page.PageBean;
import com.zong.paycommon.page.PageParam;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

/**
 * 数据访问层基础支撑接口
 * @author 宗叶青 on 2017/8/20/22:05
 */
public interface BaseDao<T> {

    long insert(T entity);

    long insert(List<T> list);

    long update(T entity);

    long update(List<T> list);

    T getById(long id);

    long deleteById(long id);

    PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);

    PageBean listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId);

    List<T> listBy(Map<String, Object> paramMap);

    List<Object> listBy(Map<String, Object> paramMap, String sqlId);

    T getBy(Map<String, Object> paramMap);

    Object getBy(Map<String, Object> paramMap, String sqlId);

    String getSeqNextValue(String seqName);

    SqlSessionTemplate getSessionTemplate();

    SqlSession getSqlSession();
}
