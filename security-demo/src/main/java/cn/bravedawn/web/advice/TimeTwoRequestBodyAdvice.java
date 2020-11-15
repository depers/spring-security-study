package cn.bravedawn.web.advice;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.annotation.Priority;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author : depers
 * @program : security
 * @description: 请求Controller增强
 * @date : Created in 2020/11/15 20:54
 */
@RestControllerAdvice
@Priority(1)
public class TimeTwoRequestBodyAdvice implements RequestBodyAdvice {


    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("-----TimeRequestBodyAdvice-two-supports-----------------");
        System.out.println(methodParameter.getMethod());
        System.out.println(targetType.getTypeName());
        System.out.println(converterType);
        return true;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("-----TimeRequestBodyAdvice-two-handleEmptyBody-----------------");
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        System.out.println("-----TimeRequestBodyAdvice-two-beforeBodyRead-----------------");
        String body = IOUtils.toString(inputMessage.getBody(), "UTF-8");
        System.out.println(body);
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("-----TimeRequestBodyAdvice-two-afterBodyRead-----------------");
        return body;
    }
}
