package cn.bravedawn.web.controller;

import cn.bravedawn.dto.User;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 冯晓
 * @Date 2019/12/19 10:37
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> getUserList(User user,
        @PageableDefault(size = 15, page = 1, sort = "age,asc") Pageable page){
        // 利用反射打印Object信息
        System.out.println(ReflectionToStringBuilder.toString(user));

        List<User> userList = Lists.newArrayList();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserInfoView.class)
    public User getUserInfo(@PathVariable String id){
        User user = new User();
        user.setUsername("tom");
        return user;
    }


}
