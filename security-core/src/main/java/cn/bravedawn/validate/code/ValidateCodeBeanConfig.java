package cn.bravedawn.validate.code;

import cn.bravedawn.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 冯晓
 * @Date 2020/2/7 22:45
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    // 若Spring容器中没有名为imageCodeGenerator的bean则会走以下流程
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        //codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }
}
