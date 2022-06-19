package net.xdclass.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.xdclass.mapper.ProductMapper;
import net.xdclass.model.ProductDO;
import net.xdclass.service.ProductService;
import net.xdclass.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductMapper productMapper;

    /**
     * 分页查询列表
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> page(int page, int size) {

        Page<ProductDO> pageInfo = new Page<>(page, size);

        IPage<ProductDO> productDOIPage = productMapper.selectPage(pageInfo, new QueryWrapper<ProductDO>()
                .orderByDesc("id"));

        Map<String, Object> pageMap = new HashMap<>();

        pageMap.put("total_record", productDOIPage.getTotal());
        pageMap.put("total_page", productDOIPage.getPages());
        pageMap.put("current_data", productDOIPage.getRecords().stream().map(obj -> beanProcess(obj)).collect(Collectors.toList()));

        return pageMap;
    }

    private ProductVO beanProcess(ProductDO productDO) {

        ProductVO productVO = new ProductVO();

        BeanUtils.copyProperties(productDO, productVO);

        productVO.setStock(productDO.getStock() - productDO.getLockStock());

        return productVO;

    }
}
