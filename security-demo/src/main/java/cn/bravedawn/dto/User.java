package cn.bravedawn.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * @Author 冯晓
 * @Date 2019/12/19 10:37
 */
@Data
public class User {

    // 使用接口来声明多个视图
    public interface UserSimpleView {};
    public interface UserInfoView extends UserSimpleView {};

    // 在对象的属性上指定视图
    @JsonView(UserSimpleView.class)
    private String username;

    @JsonView(UserInfoView.class)
    private String password;

    @JsonView(UserSimpleView.class)
    private int age;

}
