package cn.bravedawn.authentication;

import cn.bravedawn.properties.LoginResponseType;
import cn.bravedawn.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 冯晓
 * @Date 2020/1/19 17:53
 */
@Component("customAuthenticationSuccessHandler")
@Slf4j
public class CustomAuthenticationSuccessHandler
        /**
         * 实现校验成功的处理接口
         */
        // implements AuthenticationSuccessHandler
        /**
         * spring security的默认成功处理器
         */
        extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("登录成功");

        // 若定义登录类型为JSON
        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginResponseType())){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else{
            // 否则走spring security自己实现的逻辑
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
