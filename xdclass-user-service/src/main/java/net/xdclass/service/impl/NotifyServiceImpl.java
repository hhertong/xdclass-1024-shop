package net.xdclass.service.impl;

import net.xdclass.enums.BizCodeEnum;
import net.xdclass.enums.SendCodeEnum;
import net.xdclass.service.MailService;
import net.xdclass.service.NotifyService;
import net.xdclass.util.CheckUtil;
import net.xdclass.util.CommonUtil;
import net.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private MailService mailService;

    private static final String SUBJECT = "验证码";

    private static final String CONTENT = "你的验证码是%S,有效时间为60秒";

    @Override
    public JsonData sendCode(SendCodeEnum sendCodeEnum, String to) {

        if (CheckUtil.isEmail(to)) {

            String code = CommonUtil.getRandomCode(6);
            mailService.sendMail(to, SUBJECT, String.format(CONTENT, code));
            return JsonData.buildSuccess();

        } else if (CheckUtil.isPhone(to)) {

        }

        return JsonData.buildResult(BizCodeEnum.CODE_TO_ERROR);
    }
}
