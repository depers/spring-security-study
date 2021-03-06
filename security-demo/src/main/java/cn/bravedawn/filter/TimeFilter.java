package cn.bravedawn.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @Author 冯晓
 * @Date 2020/1/7 20:15
 */
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        System.out.println("time filter start");
        long start = new Date().getTime();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            System.out.println("----------------------------- Filter处理异常");
        }
        System.out.println("time filter 耗时:"+ (new Date().getTime() - start));
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
