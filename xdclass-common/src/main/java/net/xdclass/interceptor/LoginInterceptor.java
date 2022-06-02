package net.xdclass.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.model.LoginUser;
import net.xdclass.util.CommonUtil;
import net.xdclass.util.JWTUtil;
import net.xdclass.util.JsonData;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    public static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {

            String token = request.getHeader("token");
            if (token == null) {
                token = request.getParameter("token");
            }

            if (StringUtils.isNotBlank(token)) {
                Claims claims = JWTUtil.checkJWT(token);
                if (claims == null) {
                    CommonUtil.sendJsonMessage(response, JsonData.buildResult(BizCodeEnum.ACCOUNT_UNLOGIN));
                    return false;
                }

                Long id = Long.valueOf(claims.get("id").toString());
                String headImg = (String) claims.get("head_img");
                String mail = (String) claims.get("mail");
                String name = (String) claims.get("name");

                LoginUser loginUser = new LoginUser();
                loginUser.setName(name);
                loginUser.setHeadImg(headImg);
                loginUser.setId(id);
                loginUser.setMail(mail);

                threadLocal.set(loginUser);
                return true;
            }
        } catch (Exception e) {
            log.error("拦截器错误:{}", e);
        }

        CommonUtil.sendJsonMessage(response, JsonData.buildResult(BizCodeEnum.ACCOUNT_UNLOGIN));

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
