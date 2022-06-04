package net.xdclass.service;

import net.xdclass.enums.CouponCategoryEnum;
import net.xdclass.util.JsonData;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

public interface CouponService {

    /**
     * 分页查询优惠券
     * @param page
     * @param size
     * @return
     */
    Map<String, Object> pageCouponActivity(int page, int size);

    /**
     * 领取优惠券
     * @param couponId
     * @param promotion
     * @return
     */
    JsonData addCoupon(long couponId, CouponCategoryEnum promotion);
}
