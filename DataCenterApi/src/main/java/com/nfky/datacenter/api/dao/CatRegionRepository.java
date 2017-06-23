package com.nfky.datacenter.api.dao;

import com.nfky.datacenter.api.entity.CatRegion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by lyr on 2017/6/8.
 */
public interface CatRegionRepository extends PagingAndSortingRepository<CatRegion, String> {

    @Query("select a from CatRegion a where a.regionId = ?1 ")
    CatRegion findById(String id);
}
