package com.nfky.datacenter.api.controller;

import com.nfky.datacenter.api.DataCenterApiApp;
import com.nfky.datacenter.api.entity.CatRegion;
import com.nfky.datacenter.api.service.DemoDemoService;
import com.nfky.datacenter.common.base.BaseController;
import com.nfky.datacenter.common.base.ResponseData;
import com.nfky.mdc.region.service.inf.ICatRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * API示例
 *
 * Created by lyr on 2017/6/8.
 */
@RestController
@RequestMapping(value = DataCenterApiApp.API_VERSION + "/demo")
@Api(value = "API示例", description = "API示例")
public class DemoController extends BaseController{

    @Autowired
    ICatRegionService regionService;

    @Autowired
    DemoDemoService demoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "API示例-查询")
    @PreAuthorize("#oauth2.clientHasRole('ADMIN') or #oauth2.hasScope('ADMIN')")
    public ResponseData findById(@PathVariable("id") @ApiParam(value = "地区ID",defaultValue = "HXUDP0000000000000000000000034") String id) {
        try {
            return success(regionService.getById(id));
        } catch (Exception e) {
            return error("API示例获取失败");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "API示例-删除")
    public ResponseData deleteById(@PathVariable("id") @ApiParam(value = "地区ID") String id) {
        try {
            demoService.deleteById(id);
            return success("删除成功");
        } catch (Exception e) {
            return error("删除失败");
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "API示例-保存")
    public ResponseData save(@RequestBody @ApiParam(value = "地区", defaultValue = "") CatRegion region) {
        try {
            return success("保存成功", demoService.save(region));
        } catch (Exception e) {
            return error("保存失败");
        }
    }
}
