package net.xdclass.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登录对象")
public class UserLoginRequest {

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "密码")
    private String pwd;


}
