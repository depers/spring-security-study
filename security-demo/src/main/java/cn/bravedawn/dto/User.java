package cn.bravedawn.dto;

import cn.bravedawn.validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

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
    @MyConstraint(message = "测试message")
    private String username;

    @JsonView(UserInfoView.class)
    @NotBlank(message = "密码不能为空")
    private String password;

    @JsonView(UserSimpleView.class)
    private int age;

    @JsonView(UserSimpleView.class)
    @NotBlank
    private String id;

    @JsonView(UserSimpleView.class)
    @Past(message = "生日必须是过去的时间")
    private Date birthday;
}
