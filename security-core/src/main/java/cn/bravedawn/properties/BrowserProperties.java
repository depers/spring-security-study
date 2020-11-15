package cn.bravedawn.properties;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author 冯晓
 * @Date 2020/1/19 16:34
 */
@Data
public class BrowserProperties {

    /**
     * 登录的页面
     */
    private String loginPage = "/signIn.html";

    /**
     * 登录后的响应类型
     */
    private LoginResponseType loginResponseType = LoginResponseType.JSON;

    /**
     * 记住我功能cookie保存的秒数
     */
    private int rememberMeSeconds = 3600;

}
