package cn.bravedawn.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @Title 校验错误异常
 * @Author 冯晓
 * @Date 2020/1/20 16:55
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
