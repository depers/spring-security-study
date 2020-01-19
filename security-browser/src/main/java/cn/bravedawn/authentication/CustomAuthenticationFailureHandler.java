package cn.bravedawn.authentication;

import cn.bravedawn.properties.LoginResponseType;
import cn.bravedawn.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 冯晓
 * @Date 2020/1/19 18:39
 */
@Component("customAuthenticationFailureHandler")
@Slf4j
public class CustomAuthenticationFailureHandler
        /**
         * 实现校验失败的处理接口
         */
        // implements AuthenticationFailureHandler
        /**
         * spring security的默认失败处理器
         */
        extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        log.info("登录失败");

        // 若定义登录类型为JSON
        if(LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginResponseType())){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(e));
        }else{
            // 否则走spring security自己实现的逻辑
            super.onAuthenticationFailure(request, response, e);
        }

    }
}
