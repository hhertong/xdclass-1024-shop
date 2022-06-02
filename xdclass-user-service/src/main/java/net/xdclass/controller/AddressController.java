package net.xdclass.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.model.AddressDO;
import net.xdclass.request.AddressAddRequest;
import net.xdclass.service.AddressService;
import net.xdclass.util.JsonData;
import net.xdclass.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

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


    @ApiOperation("新增收货地址")
    @PostMapping("add")
    public JsonData add(@ApiParam("地址对象")
                        @RequestBody AddressAddRequest addressAddRequest) {

        addressService.add(addressAddRequest);


        return JsonData.buildSuccess();
    }


    @ApiOperation("删除收货地址")
    @DeleteMapping("del/{address_id}")
    public JsonData del(@ApiParam(value = "地址id", required = true)
                        @PathVariable("address_id") Long addressId) {

        int rows = addressService.del(addressId);


        return rows==1? JsonData.buildSuccess():JsonData.buildResult(BizCodeEnum.ADDRESS_DEL_FAIL);
    }

}

