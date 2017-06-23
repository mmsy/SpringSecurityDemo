package com.nfky.datacenter.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * 基础服务类
 *
 * Created by lyr on 2017/6/8.
 */
public class BaseService<Repository extends PagingAndSortingRepository<Entity, ID>, Entity, ID extends Serializable> {
    @Autowired
    Repository rep;

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public Entity findById(ID id) {
        return rep.findOne(id);
    }

    /**
     * 保存实体
     *
     * @param entity 实体对象
     * @return
     */
    public Entity save(Entity entity) {
        return rep.save(entity);
    }

    /**
     * 删除实体
     *
     * @param entity 实体对象
     */
    public void delete(Entity entity) {
        rep.delete(entity);
    }

    /**
     * 根据ID删除数据
     *
     * @param id
     */
    public void deleteById(ID id) {
        rep.delete(id);
    }
}
