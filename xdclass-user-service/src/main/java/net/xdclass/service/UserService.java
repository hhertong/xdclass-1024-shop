package net.xdclass.service;

import net.xdclass.request.UserRegisterRequest;
import net.xdclass.util.JsonData;

public interface UserService {

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    JsonData register(UserRegisterRequest userRegisterRequest);

}
