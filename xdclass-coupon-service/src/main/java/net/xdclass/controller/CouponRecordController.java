package net.xdclass.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.service.CouponRecordService;
import net.xdclass.util.JsonData;
import net.xdclass.vo.CouponRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.websocket.server.PathParam;
import java.util.Map;


@RestController
@RequestMapping("/api/coupon_record/v1")
public class CouponRecordController {

    @Autowired
    private CouponRecordService couponRecordService;

    @ApiOperation("分页查询个人优惠券")
    @GetMapping("page")
    public JsonData page(@ApiParam("当前页")
                         @RequestParam(value = "page") int page,
                         @ApiParam("显示多少条")
                         @RequestParam(value = "size") int size) {

        Map<String, Object> pageInfo = couponRecordService.page(page, size);

        return JsonData.buildSuccess(pageInfo);
    }


    @ApiOperation("查询优惠券详情")
    @GetMapping("detail/{record_id}")
    public JsonData getCouponRecordDetail(@ApiParam("记录id")
                                          @PathVariable("record_id") long recordId) {

        CouponRecordVO couponRecordVO = couponRecordService.findById(recordId);


        return couponRecordVO == null ? JsonData.buildResult(BizCodeEnum.COUPON_NO_EXITS) : JsonData.buildSuccess(couponRecordVO);
    }

}

