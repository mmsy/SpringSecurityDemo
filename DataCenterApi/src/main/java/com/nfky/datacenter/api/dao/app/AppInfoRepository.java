package com.nfky.datacenter.api.dao.app;

import com.nfky.datacenter.api.entity.app.AppInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by lyr on 2017/6/14.
 */
public interface AppInfoRepository extends PagingAndSortingRepository<AppInfo, Integer> {

    AppInfo findByAppId(String appId);
}
