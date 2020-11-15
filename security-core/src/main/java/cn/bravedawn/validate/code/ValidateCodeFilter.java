package cn.bravedawn.validate.code;

import cn.bravedawn.properties.SecurityProperties;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author 冯晓
 * @Date 2020/1/20 16:39
 */
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter
        // InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候都会执行该方法。
        implements InitializingBean {

    /**
     * 验证码校验失败处理器
     */
    @Setter
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 系统配置信息
     */
    @Setter
    private SecurityProperties securityProperties;

    /**
     * 存放所有需要校验验证码的url
     */
    private Set<String> urls = new HashSet<>();

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 获取“需要图形验证码拦截的请求”
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        if (StringUtils.isNotBlank(securityProperties.getCode().getImage().getUrl())){
            String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(
                    securityProperties.getCode().getImage().getUrl(), ",");
            for (String url : configUrls) {
                urls.add(url);
            }
        }

        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 判断请求是否满足bravedawn.security.code.image.url配置的要求
        Boolean action = false;
        for (String url : urls){
            if(pathMatcher.match(url, request.getRequestURI())){
                action = true;
                break;
            }
        }
        if (action){
            log.info("===============请求的url: {}", request.getRequestURI());
            try {
                // 校验验证信息
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        // 不是登录请求，放开
        filterChain.doFilter(request, response);
    }

    /**
     * 验证图片验证码
     * @param request
     * @throws ServletRequestBindingException
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {

        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpired()){
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)){
            throw new ValidateCodeException("验证码不匹配");
        }

        log.debug("session存储的code: {}, 请求填写的code: {}", codeInSession.getCode(), codeInRequest);
        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
    }














}
