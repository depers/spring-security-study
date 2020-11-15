package cn.bravedawn;

import cn.bravedawn.authentication.CustomAuthenticationFailureHandler;
import cn.bravedawn.authentication.CustomAuthenticationSuccessHandler;
import cn.bravedawn.properties.SecurityProperties;
import cn.bravedawn.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Author 冯晓
 * @Date 2020/1/16 19:32
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * spring security默认密码实现配置
     * BCryptPasswordEncoder类是PasswordEncoder接口的实现类
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 记住我功能配置
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 新建数据表之后注释这句代码
        // tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http
                // 在UsernamePasswordAuthenticationFilter之前校验图形验证码
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // 配置表单登录
                .formLogin()
        //http.httpBasic()
                    // 配置身份校验路由
                    .loginPage("/authentication")
                    // 配置登录逻辑的处理url
                    .loginProcessingUrl("/authentication/form")
                    // 配置身份校验成功处理类
                    .successHandler(customAuthenticationSuccessHandler)
                    // 配置身份校验失败处理类
                    .failureHandler(customAuthenticationFailureHandler)
                    .and()
                // 配置记住我功能
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                // 对请求进行授权配置
                .authorizeRequests()
                // 放开请求，无需身份认证
                .antMatchers(
                        "/authentication",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/image",
                        "/hello",
                        "/post_hello"
                    ).permitAll()
                // 对所有请求
                .anyRequest()
                // 都要进行身份认证
                .authenticated()
                // 关闭csrf防护
                .and()
                .csrf().disable();
    }
}
