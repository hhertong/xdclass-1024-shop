package net.xdclass.exception;

import io.swagger.models.auth.In;
import lombok.Data;
import net.xdclass.enums.BizCodeEnum;

@Data
public class BizException extends RuntimeException {

    private Integer code;

    private String msg;

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum.getMsg());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMsg();
    }


}
