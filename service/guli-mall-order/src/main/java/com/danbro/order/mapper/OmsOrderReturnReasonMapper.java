package com.danbro.order.mapper;
 
import com.danbro.order.entity.OmsOrderReturnReason;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 退货原因(OmsOrderReturnReason)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:30:18
 */
@Mapper
public interface OmsOrderReturnReasonMapper extends BaseMapper<OmsOrderReturnReason>{
 
}