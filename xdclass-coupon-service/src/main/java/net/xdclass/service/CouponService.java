package net.xdclass.service;

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

}
