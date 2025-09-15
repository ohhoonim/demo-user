package dev.ohhoonim.user.activity.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.ohhoonim.user.User;
import dev.ohhoonim.user.activity.LoginActivity;
import dev.ohhoonim.user.activity.port.UserPort;

@Service
public class LoginService implements LoginActivity {

    private final UserPort userPort;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserPort userPort,
            PasswordEncoder passwordEncoder) {
        this.userPort = userPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override

    public User isValidUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        return userPort.findUser(username, encodedPassword)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다"));
    }
    
}
