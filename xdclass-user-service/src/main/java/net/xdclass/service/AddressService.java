package net.xdclass.service;

import net.xdclass.model.AddressDO;
import net.xdclass.request.AddressAddRequest;

public interface AddressService {

    /**
     * 地址信息
     * @param id
     * @return
     */
    AddressDO detail(Long id);

    /**
     * 新增地址信息
     * @param addressAddRequest
     */
    int add(AddressAddRequest addressAddRequest);
}
