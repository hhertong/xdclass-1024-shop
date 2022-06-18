package net.xdclass.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class NewUserCouponRequest {

    @ApiModelProperty(value = "用户id")
    @JsonProperty("user_id")
    private long userId;

    @ApiModelProperty(value = "用户名称")
    private String name;

}
