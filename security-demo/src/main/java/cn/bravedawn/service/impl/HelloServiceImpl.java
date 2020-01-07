package cn.bravedawn.service.impl;

import cn.bravedawn.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @Author 冯晓
 * @Date 2020/1/7 16:55
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {

        System.out.println("greeting");
        return "hello "+name;
    }
}
