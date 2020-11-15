package cn.bravedawn.config;

import cn.bravedawn.filter.TimeFilter;
import cn.bravedawn.filter.TimeFilterTwo;
import cn.bravedawn.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 冯晓
 * @Date 2020/1/7 20:21
 */

//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * 异步处理配置
     * @param configurer
     */
    /*@Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer
                // 设置callable拦截器
                .registerCallableInterceptors()
                // 设置DeferredResult拦截器
                .registerDeferredResultInterceptors()
                // 设置默认超时时间
                .setDefaultTimeout(1000)
                // 设置可重用的线程池
                .setTaskExecutor();
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }


    @Bean
    public FilterRegistrationBean timeFilter() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);
        registrationBean.setOrder(1);

        // 设置请求非拦截的路径，该处设置为所有请求路径
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean timeFilterTwo() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        TimeFilterTwo timeFilter = new TimeFilterTwo();
        registrationBean.setFilter(timeFilter);
        registrationBean.setOrder(2);

        // 设置请求非拦截的路径，该处设置为所有请求路径
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }
}
