package cn.bravedawn.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @Author 冯晓
 * @Date 2020/1/14 20:30
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/sync")
    public String sync() throws Exception {
        log.info("主线程开始");
        Thread.sleep(1000);
        log.info("主线程结束");
        return "success";
    }

    /**
     * 使用Runnable异步处理Rest服务
     * @return
     * @throws Exception
     */
    //@RequestMapping("/async")
    public Callable<String> async() throws Exception {
        log.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("副线程开始");
                Thread.sleep(1000);
                log.info("副线程结束");
                return "success";
            }
        };

        log.info("主线程结束");
        return result;
    }

    /**
     *  使用DeferredResult一步处理Rest服务
     * @return
     * @throws Exception
     */
    @RequestMapping("/async")
    public DeferredResult<String> async_de() throws Exception {
        log.info("主线程开始");

        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);
        log.info("主线程结束");

        return result;
    }


}
