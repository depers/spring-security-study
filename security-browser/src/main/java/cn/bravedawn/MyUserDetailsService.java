package cn.bravedawn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author 冯晓
 * @Date 2020/1/16 21:28
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户名： {}", username);

        // 默认数据库中读取的密码应该是已经加密的，这里为了方便演示对密码进行加密
        String pwd = passwordEncoder.encode("123456");
        log.info("用户密码是： {}", pwd);

        // 处理用户信息获取逻辑，根据用户名查找用户信息
        // 用户访问资源的时候就输入用户名可以是任何字符串,密码123456就可以通过校验了
        /**
         * 参数1：用户名
         * 参数2：密码
         * 参数3：权限信息数组
         */
        //return new User(username, "123456", AuthorityUtils.createAuthorityList("admin"));

        /**
         * 参数1：用户名
         * 参数2：密码
         * 参数3：校验用户是否可用
         * 参数4：校验用户是否没过期
         * 参数5：校验用户密码是否没过期
         * 参数6：校验用户是否没被锁
         * 参数7：权限信息数组
         */
        return new User(username, pwd,
                true, true, true, true,
                AuthorityUtils.createAuthorityList("admin"));
    }
}
