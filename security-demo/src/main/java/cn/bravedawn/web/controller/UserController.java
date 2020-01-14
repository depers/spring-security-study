package cn.bravedawn.web.controller;

import cn.bravedawn.dto.User;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        // int a = 1/0;
        return user;
    }

    @PostMapping
    @JsonView(User.UserSimpleView.class)
    public User create(@RequestParam String username, @Valid @RequestBody User user, BindingResult result){

        if (result.hasErrors()){
            result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println("--------------------------------------");
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getAge());

        System.out.println("-------------username----"+ username);

        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult result) {

        if (result.hasErrors()){
            result.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                String message = fieldError.getField() + " : " +fieldError.getDefaultMessage();
                System.out.println(message);
            });
        }
        System.out.println("-----------------------------------");
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        System.out.println("-----------------------------------");

        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }

}
