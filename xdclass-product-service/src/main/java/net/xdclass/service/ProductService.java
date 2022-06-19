package net.xdclass.service;

import net.xdclass.vo.ProductVO;

import java.util.Map;

public interface ProductService {

    Map<String, Object> page(int page, int size);

    ProductVO findDetailById(long productId);
}
