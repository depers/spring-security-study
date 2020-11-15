package cn.bravedawn;

import com.github.tomakehurst.wiremock.common.Json;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Map;

/**
 * @Author 冯晓
 * @Date 2019/12/18 11:04
 */
@SpringBootApplication(scanBasePackages = {"cn.bravedawn"})
@RestController
@EnableSwagger2
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";

    }

    @PostMapping("/post_hello")
    public String hellTest(@RequestBody Map<String, String> body){
        System.out.println(body);
        return Json.write(body);
    }

}
