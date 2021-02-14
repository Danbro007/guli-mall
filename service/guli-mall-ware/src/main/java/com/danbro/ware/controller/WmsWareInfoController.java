package com.danbro.ware.controller;
 
import java.util.List;
import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.danbro.ware.controller.vo.WmsWareInfoVo;
import com.danbro.ware.entity.WmsWareInfo;
import com.danbro.ware.service.WmsWareInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Api(tags = "仓库信息(WmsWareInfo)") 
@Validated
@RestController
@AllArgsConstructor
@Setter
@RequestMapping("ware/wareinfo")
public class WmsWareInfoController {
    private  WmsWareInfoService wmsWareInfoService;

    @ApiOperation("分页查询仓库")
    @GetMapping("list")
    public ResultPageBean<WmsWareInfoVo,WmsWareInfo> getWareInfoList(@RequestParam("page") Long page,
                                                                     @RequestParam("limit") Long limit,
                                                                     @RequestParam(value = "key",required = false) String key){
        PageParam<WmsWareInfo> pageParam = new PageParam<WmsWareInfo>().setPage(page).setLimit(limit);
        return ResultPageBean.ofSuccess(wmsWareInfoService.queryPageByCondition(pageParam,key));
    }

    @ApiOperation("添加仓库")
    @PostMapping("")
    public ResultBean<WmsWareInfoVo> insertWare(@Validated(Insert.class) @RequestBody WmsWareInfoVo wmsWareInfoVo){
        return ResultBean.ofSuccess(wmsWareInfoService.insertWare(wmsWareInfoVo));
    }

    @ApiOperation("修改仓库")
    @PutMapping("")
    public ResultBean<WmsWareInfoVo> updateWare(@Validated(Update.class) @RequestBody WmsWareInfoVo wmsWareInfoVo){
        return ResultBean.ofSuccess(wmsWareInfoService.updateWare(wmsWareInfoVo));
    }

    @ApiOperation("获取仓库的详细信息")
    @GetMapping("info/{wareId}")
    public ResultBean<WmsWareInfoVo> getWareInfo(@PathVariable Long wareId){
        return ResultBean.ofSuccess(wmsWareInfoService.getWareInfoById(wareId));
    }

    @ApiOperation("批量删除仓库")
    @DeleteMapping("")
    public ResultBean<?> batchDeleteWare(@RequestBody List<Long> wareIdList) {
        wmsWareInfoService.batchDeleteWare(wareIdList);
        return ResultBean.ofSuccess();
    }


}