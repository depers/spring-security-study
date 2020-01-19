package cn.bravedawn.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author 冯晓
 * @Date 2020/1/19 16:34
 */
@Data
@ConfigurationProperties(prefix = "bravedawn.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

}
