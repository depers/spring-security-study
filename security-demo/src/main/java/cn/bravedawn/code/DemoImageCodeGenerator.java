package cn.bravedawn.code;

import cn.bravedawn.validate.code.ImageCode;
import cn.bravedawn.validate.code.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author 冯晓
 * @Date 2020/2/7 22:58
 */
//@Component("imageCodeGenerator")
@Slf4j
public class DemoImageCodeGenerator implements ValidateCodeGenerator {


    @Override
    public ImageCode generate(ServletWebRequest request) {
        log.info("这是一个高级的图形验证码生成方法，他会代替security-core中默认的实现");
        return null;
    }
}
