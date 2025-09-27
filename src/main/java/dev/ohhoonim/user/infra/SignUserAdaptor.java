package dev.ohhoonim.user.infra;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import dev.ohhoonim.component.sign.SignUser;
import dev.ohhoonim.component.sign.activity.port.SignUserPort;

@Component
public class SignUserAdaptor implements SignUserPort {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public SignUserAdaptor(UserMapper userMapper, 
            PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<SignUser> findByUsernamePassword(String name, String password) {
        String username = userMapper.findByUsernamePassword(
                name, passwordEncoder.encode(password));
        return !StringUtils.hasText(username)  
                ? Optional.empty() 
                : Optional.of(new SignUser(username));
    }

}
