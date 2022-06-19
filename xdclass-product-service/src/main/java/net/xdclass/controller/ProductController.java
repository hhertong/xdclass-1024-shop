package net.xdclass.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.xdclass.service.ProductService;
import net.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@Api("商品模块")
@RestController
@RequestMapping("/api/product/v1")
public class ProductController {

    @Autowired
    private ProductService productService;


    /**
     * 分页查询列表
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页查询列表")
    @GetMapping("page_product")
    public JsonData pageProductList(@ApiParam(value = "当前页")
                                    @RequestParam int page,
                                    @ApiParam(value = "每页显示多少条")
                                    @RequestParam int size) {

        Map<String, Object> pageResult = productService.page(page, size);

        return JsonData.buildSuccess(pageResult);
    }


}

