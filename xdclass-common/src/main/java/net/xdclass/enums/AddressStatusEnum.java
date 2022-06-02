package net.xdclass.enums;

import lombok.Getter;

/**
 * 收货地址状态
 */
public enum AddressStatusEnum {


    DEFAULT_STATUS(1),

    COMMON_STATUS(0);

    @Getter
    private int status;


    private AddressStatusEnum(int status) {
        this.status = status;
    }

}
