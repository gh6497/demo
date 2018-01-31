package cn.cat.springmvc.demo.service;

import java.util.List;

/**
 * @Author: cat
 * @Date: Created in 2018/1/24
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
public interface BaseService<T> {
    /**
     * 根据主键查询
     */
    T selectByKey(Object key);

    /**
     * 插入实体
     * @param entity
     * @return
     */
    int save(T entity);

    /**
     * 根据主键删除
     * @param key
     * @return
     */
    int delete(Object key);

    /**
     * 更新实体
     * @param entity
     * @return
     */
    int updateAll(T entity);

    /**
     * 更新实体,不包括原来是空值的
     * @param entity
     * @return
     */
    int updateNotNull(T entity);

    List<T> selectByExample(Object example);

    List<T> getAll();
}
