package net.xdclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiParam;
import net.xdclass.enums.AddressStatusEnum;
import net.xdclass.interceptor.LoginInterceptor;
import net.xdclass.mapper.AddressMapper;
import net.xdclass.model.AddressDO;
import net.xdclass.model.LoginUser;
import net.xdclass.request.AddressAddRequest;
import net.xdclass.service.AddressService;
import net.xdclass.util.JsonData;
import net.xdclass.vo.AddressVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 地址信息
     *
     * @param id
     * @return
     */
    @Override
    public AddressVO detail(Long id) {

        LoginUser loginUser = LoginInterceptor.threadLocal.get();

        AddressDO addressDO = addressMapper.selectOne(new QueryWrapper<AddressDO>().eq("id", id).eq("user_id", loginUser.getId()));

        if (addressDO == null) {
            return null;
        }
        AddressVO addressVO = new AddressVO();
        BeanUtils.copyProperties(addressDO, addressVO);

        return addressVO;

    }

    /**
     * 新增地址信息
     *
     * @param addressAddRequest
     */
    @Override
    public int add(AddressAddRequest addressAddRequest) {

        LoginUser loginUser = LoginInterceptor.threadLocal.get();
        AddressDO addressDO = new AddressDO();

        addressDO.setCreateTime(new Date());
        addressDO.setUserId(loginUser.getId());
        BeanUtils.copyProperties(addressAddRequest, addressDO);

        //是否有默认收货地址
        if (addressDO.getDefaultStatus() == AddressStatusEnum.DEFAULT_STATUS.getStatus()) {
            AddressDO defaultAddressDO = addressMapper.selectOne(new QueryWrapper<AddressDO>().eq("user_id", loginUser.getId()).eq("default_status", AddressStatusEnum.DEFAULT_STATUS.getStatus()));
            if (defaultAddressDO != null) {
                //修改为非默认地址
                defaultAddressDO.setDefaultStatus(AddressStatusEnum.COMMON_STATUS.getStatus());
                addressMapper.update(defaultAddressDO, new QueryWrapper<AddressDO>().eq("id", defaultAddressDO.getId()));
            }
        }


        int rows = addressMapper.insert(addressDO);
        return rows;
    }

    /**
     * 删除收货地址
     *
     * @param addressId
     * @return
     */
    @Override
    public int del(Long addressId) {
        LoginUser loginUser = LoginInterceptor.threadLocal.get();
        int rows = addressMapper.delete(new QueryWrapper<AddressDO>().eq("id", addressId).eq("user_id", loginUser.getId()));
        return rows;
    }

    /**
     * 查询用户所有地址
     *
     * @return
     */
    @Override
    public List<AddressVO> listUserAllAddress() {

        LoginUser loginUser = LoginInterceptor.threadLocal.get();

        List<AddressDO> list = addressMapper.selectList(new QueryWrapper<AddressDO>().eq("user_id", loginUser.getId()));


        List<AddressVO> addressVOList = list.stream().map(obj -> {

            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(obj, addressVO);
            return addressVO;

        }).collect(Collectors.toList());

        return addressVOList;
    }


}
