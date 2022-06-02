package net.xdclass.service;

import net.xdclass.model.AddressDO;
import net.xdclass.request.AddressAddRequest;
import net.xdclass.vo.AddressVO;

public interface AddressService {

    /**
     * 地址信息
     * @param id
     * @return
     */
    AddressVO detail(Long id);

    /**
     * 新增地址信息
     * @param addressAddRequest
     */
    int add(AddressAddRequest addressAddRequest);

    /**
     * 删除收货地址
     * @param addressId
     * @return
     */
    int del(Long addressId);
}
