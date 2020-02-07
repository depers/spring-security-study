package cn.bravedawn.properties;

import lombok.Data;

/**
 * @Author 冯晓
 * @Date 2020/2/7 20:37
 */
@Data
public class ImageCodeProperties {

    // 宽
    private int width = 67;
    // 高
    private int height = 23;
    // 验证码字符长度
    private int length = 4;
    // 失效的时间
    private int expireIn = 60;
    // 需要验证码连接的请求
    private String url;
}
