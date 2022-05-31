package net.xdclass.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

@Api(value = "用户注册对象")
@Data
public class UserRegisterRequest {

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "头像")
    @JsonProperty("head_img")
    private String headImg;

    @ApiModelProperty(value = "用户个性签名")
    private String slogan;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "验证码")
    private String code;


}
