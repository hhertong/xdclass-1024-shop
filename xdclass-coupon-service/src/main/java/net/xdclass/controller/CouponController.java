package net.xdclass.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.xdclass.enums.CouponCategoryEnum;
import net.xdclass.service.CouponService;
import net.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "优惠券模块")
@RestController
@RequestMapping("/api/coupon/v1")
public class CouponController {


    @Autowired
    private CouponService couponService;

    @ApiOperation("分页查询优惠券")
    @GetMapping("page-coupon")
    public JsonData pageCouponList(@ApiParam(value = "当前页")
                                   @RequestParam int page,
                                   @ApiParam(value = "每页显示多少条")
                                   @RequestParam int size) {


        Map<String, Object> pageMap = couponService.pageCouponActivity(page, size);

        return JsonData.buildSuccess(pageMap);
    }


    @ApiOperation("领取优惠券")
    @GetMapping("add/promotion/{coupon_id}")
    public JsonData addPromotionCoupon(@ApiParam(value = "优惠券id")
                                       @PathVariable("coupon_id") long couponId) {

        JsonData jsonData = couponService.addCoupon(couponId, CouponCategoryEnum.PROMOTION);

        return jsonData;
    }

}

