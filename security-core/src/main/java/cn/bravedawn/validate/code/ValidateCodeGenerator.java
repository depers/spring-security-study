package cn.bravedawn.validate.code;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author 冯晓
 * @Date 2020/2/7 22:35
 */
public interface ValidateCodeGenerator {

    ImageCode generate(ServletWebRequest request);
}
