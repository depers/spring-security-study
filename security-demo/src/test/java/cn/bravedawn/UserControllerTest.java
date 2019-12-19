package cn.bravedawn;

import cn.bravedawn.dto.User;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Author 冯晓
 * @Date 2019/12/19 9:55
 */
// 使用springRunner来运行这个类
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        // 伪造mvc环境
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
            // 期望接收的参数是json格式
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            // 期望得到的响应状态码为200
            .andExpect(MockMvcResultMatchers.status().isOk())
            // 期望响应数据集合的长度是3
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
            .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenGetUserInfoSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
            .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenGetUserInfoFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }


}























