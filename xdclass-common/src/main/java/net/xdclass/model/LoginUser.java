package net.xdclass.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
public class LoginUser {

    private Long id;

    private String name;

    @JsonProperty("head_img")
    private String headImg;

    private String mail;

}
