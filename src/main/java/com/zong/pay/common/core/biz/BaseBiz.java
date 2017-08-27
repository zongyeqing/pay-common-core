package com.zong.pay.common.core.biz;

import com.zong.paycommon.entity.BaseEntity;
import com.zong.paycommon.page.PageBean;
import com.zong.paycommon.page.PageParam;

import java.util.List;
import java.util.Map;

/**
 * 基类biz接口
 * @author 宗叶青 on 2017/8/16/17:15
 */
public interface BaseBiz <T extends BaseEntity>{

    /**
     * 根据实体对象新增记录.
     *
     * @param entity
     *            .
     * @return id .
     */
    long create(T entity);

    /**
     * 批量保存对象.
     *
     * @param entities
     *            .
     * @return id .
     */
    long create(List<T> entities);

    /**
     * 批量更新对象.
     *
     * @param entities
     *            .
     * @return id .
     */
    long update(List<T> entities);

    /**
     * 根据ID删除记录.
     *
     * @param id
     *            .
     * @return
     */
    long deleteById(long id);

    /**
     * 根据ID查找记录.
     *
     * @param id
     *            .
     * @return entity .
     */
    T getById(long id);

    /**
     * 分页查询 .
     *
     * @param pageParam
     *            分页参数.
     * @param paramMap
     *            业务条件查询参数.
     * @return
     */
    PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);

    PageBean listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId);

    /**
     * 根据条件查询 listBy: <br/>
     *
     * @param paramMap
     * @return 返回集合
     */
    List<T> listBy(Map<String, Object> paramMap);

    List<Object> listBy(Map<String, Object> paramMap, String sqlId);

    /**
     * 根据条件查询 listBy: <br/>
     *
     * @param paramMap
     * @return 返回实体
     */
    T getBy(Map<String, Object> paramMap);

    Object getBy(Map<String, Object> paramMap, String sqlId);

    /**
     * 根据序列名称获取下一个值
     *
     * @return
     */
    String getSeqNextValue(String seqName);
}
