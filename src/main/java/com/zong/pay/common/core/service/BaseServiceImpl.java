package com.zong.pay.common.core.service;

import com.zong.pay.common.core.dao.BaseDao;
import com.zong.paycommon.entity.BaseEntity;
import com.zong.paycommon.page.PageBean;
import com.zong.paycommon.page.PageParam;

import java.util.List;
import java.util.Map;

/**
 * @author 宗叶青 on 2017/8/21/23:54
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
    protected abstract BaseDao<T> getDao();

    public T getById(long id) {
        return this.getDao().getById(id);
    }

    /**
     * 分页查询 .
     *
     * @param pageParam
     *            分页参数.
     * @param paramMap
     *            业务条件查询参数.
     * @return
     */
    public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
        return this.getDao().listPage(pageParam, paramMap);
    }

    public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId) {
        return this.getDao().listPage(pageParam, paramMap, sqlId);
    }

    /**
     * 根据条件查询 listBy: <br/>
     *
     * @param paramMap
     * @return 返回集合
     */
    public List<T> listBy(Map<String, Object> paramMap) {
        return this.getDao().listBy(paramMap);
    }

    public List<Object> listBy(Map<String, Object> paramMap, String sqlId) {
        return this.getDao().listBy(paramMap, sqlId);
    }

    /**
     * 根据条件查询 listBy: <br/>
     *
     * @param paramMap
     * @return 返回实体
     */
    public T getBy(Map<String, Object> paramMap) {
        return this.getDao().getBy(paramMap);
    }

    public Object getBy(Map<String, Object> paramMap, String sqlId) {
        return this.getDao().getBy(paramMap, sqlId);
    }

    /**
     * 根据序列名称获取下一个值
     *
     * @return
     */
    public String getSeqNextValue(String seqName) {
        return this.getDao().getSeqNextValue(seqName);
    }
}
