package net.xdclass.mapper;

import net.xdclass.model.CouponDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper extends BaseMapper<CouponDO> {


    /**
     * 扣减库存
     * @param couponId
     * @param stock
     * @return
     */
    int reduceStock(long couponId, Integer stock);
}
