package net.xdclass.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.request.AddressAddRequest;
import net.xdclass.service.AddressService;
import net.xdclass.util.JsonData;
import net.xdclass.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

@Api(tags = "收货地址模块")
@RestController
@RequestMapping("/api/address/v1/")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation("根据ID查找地址详情")
    @GetMapping("detail/{address_id}")
    public JsonData detail(@ApiParam(value = "地址ID", required = true)
                           @PathVariable("address_id") long addressId) {

        AddressVO addressVO = addressService.detail(addressId);

        return addressVO == null ? JsonData.buildResult(BizCodeEnum.ADDRESS_NO_EXITS) : JsonData.buildSuccess(addressVO);
    }

    /**
     * 新增收货地址
     *
     * @param addressAddRequest
     * @return
     */
    @ApiOperation("新增收货地址")
    @PostMapping("add")
    public JsonData add(@ApiParam("地址对象")
                        @RequestBody AddressAddRequest addressAddRequest) {

        addressService.add(addressAddRequest);


        return JsonData.buildSuccess();
    }

    /**
     * 删除收货地址
     *
     * @param addressId
     * @return
     */
    @ApiOperation("删除收货地址")
    @DeleteMapping("del/{address_id}")
    public JsonData del(@ApiParam(value = "地址id", required = true)
                        @PathVariable("address_id") Long addressId) {

        int rows = addressService.del(addressId);


        return rows == 1 ? JsonData.buildSuccess() : JsonData.buildResult(BizCodeEnum.ADDRESS_DEL_FAIL);
    }


    /**
     * 查询用户所有收货地址
     *
     * @return
     */
    @ApiOperation("查询用户的全部收货地址")
    @GetMapping("list")
    public JsonData findUserAllAddress() {

        List<AddressVO> list = addressService.listUserAllAddress();


        return JsonData.buildSuccess(list);
    }

}

