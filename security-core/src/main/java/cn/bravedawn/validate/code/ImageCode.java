package cn.bravedawn.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Title 二维码图片
 * @Author 冯晓
 * @Date 2020/1/20 15:45
 */
@Data
@AllArgsConstructor
@Slf4j
public class ImageCode {

    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;

    /**
     * 通过设置有效期秒数
     * @param image
     * @param code
     * @param expireInt
     */
    public ImageCode(BufferedImage image, String code, int expireInt) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireInt);
    }

    /**
     * 判断图片验证码是否过期
     * @return
     */
    public boolean isExpired() {
        log.debug("-------------expireTime: "+ expireTime.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return LocalDateTime.now().isAfter(expireTime);
    }
}
