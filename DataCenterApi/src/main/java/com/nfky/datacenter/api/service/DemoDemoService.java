package com.nfky.datacenter.api.service;

import com.nfky.datacenter.api.dao.CatRegionRepository;
import com.nfky.datacenter.api.entity.CatRegion;
import com.nfky.datacenter.common.base.BaseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by lyr on 2017/6/8.
 */
@Service
@Transactional
public class DemoDemoService extends BaseService<CatRegionRepository, CatRegion, String> {

}
