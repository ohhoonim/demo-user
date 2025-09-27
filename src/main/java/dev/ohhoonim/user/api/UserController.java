package dev.ohhoonim.user.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ohhoonim.component.container.Search;
import dev.ohhoonim.component.container.Vo;
import dev.ohhoonim.user.User;
import dev.ohhoonim.user.activity.UserEnableStatus;
import dev.ohhoonim.user.activity.UserLockStatus;
import dev.ohhoonim.user.activity.UserReq;
import dev.ohhoonim.user.activity.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/withdrawUser")
    public void withdrawUser(User user) {
        userService.withdrawUser(user);
    }

    @PostMapping("/users")
    public Vo<List<User>> users(Search<UserReq> condition) {
        return userService.users(condition);
    }

    @PostMapping("/userInfo")
    public User userInfo(User username) {
        return userService.userInfo(username);
    }

    @PostMapping("/verifyPassword")
    public boolean verifyPassword(User username, String inputPassword) {
        return userService.verifyPassword(username, inputPassword);
    }

    @PostMapping("/resetPassword")
    public void resetPassword(User username, String oldPassword, String newPassword) {
        userService.resetPassword(username, oldPassword, newPassword);
    }

    @PostMapping("/register")
    public void register(User user) {
        userService.register(user);
    }

    @PostMapping("/modifyInfo")
    public void modifyInfo(User userInfo) {
        userService.modifyInfo(userInfo);
    }

    @PostMapping("/isValidUser")
    public User isValidUser(String username, String passord) {
        return userService.isValidUser(username, passord);
    }

    @PostMapping("/modifyLock")
    public UserLockStatus modifyLock(User username, boolean isLock, LocalDateTime effectiveDate) {
        return userService.modifyLock(username, isLock, effectiveDate);
    }

    @PostMapping("/dormantUser")
    public void dormantUser(User username) {
        userService.dormantUser(username);
    }

    @PostMapping("/increaseFailedAttemptCount")
    public int increaseFailedAttemptCount(User username, boolean isInit) {
        return increaseFailedAttemptCount(username, isInit);
    }

    @PostMapping("/lastLogin")
    public LocalDateTime lastLogin(User username) {
        return userService.lastLogin(username);
    }

    @PostMapping("/batchDormantUser")
    public void batchDormantUser(List<User> users) {
        userService.batchDormantUser(users);
    }

    @PostMapping("/modifyActivate")
    public UserEnableStatus modifyActivate(User userId, boolean isEnable) {
        return userService.modifyActivate(userId, isEnable);
    }
}
