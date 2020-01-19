package cn.bravedawn.properties;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author 冯晓
 * @Date 2020/1/19 16:34
 */
@Data
public class BrowserProperties {

    private String loginPage = "/signIn.html";

    private LoginResponseType loginResponseType = LoginResponseType.JSON;
}
