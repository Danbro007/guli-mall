package com.danbro.member.service;
 
 
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.member.controller.vo.UmsMemberLevelVo;
import com.danbro.member.entity.UmsMemberLevel;
 
 
/**
 * 会员等级(UmsMemberLevel)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
public interface UmsMemberLevelService extends IService<UmsMemberLevel> {
    /**
     * 分页查询会员等级
     * @param pageParam 分页条件
     * @param key 关键字
     * @return 查询结果
     */
    Pagination<UmsMemberLevelVo,UmsMemberLevel> getMemberLevelList(PageParam<UmsMemberLevel> pageParam, String key);

    /**
     * 添加会员等级
     * @param memberLevelVo 会员等级对象
     * @return 添加完毕后的会员等级
     */
    UmsMemberLevelVo insertMemberLevel(UmsMemberLevelVo memberLevelVo);


    /**
     * 修改会员等级
     * @param memberLevelVo 会员等级对象
     * @return 修改完毕后的会员等级
     */
    UmsMemberLevelVo updateMemberLevel(UmsMemberLevelVo memberLevelVo);

    /**
     * 获取会员等级的详细信息
     * @param memberLevelId 会员等级的Id
     * @return 会员等级的详细
     */
    UmsMemberLevelVo getMemberLevelInfo(Long memberLevelId);
}