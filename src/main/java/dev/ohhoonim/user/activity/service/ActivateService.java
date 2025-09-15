package dev.ohhoonim.user.activity.service;

import dev.ohhoonim.user.User;
import dev.ohhoonim.user.activity.ActivateActivity;
import dev.ohhoonim.user.activity.UserEnableStatus;
import dev.ohhoonim.user.activity.port.UserPort;

public class ActivateService implements ActivateActivity {

    private final UserPort userPort;

    public ActivateService(UserPort userPort) {
        this.userPort = userPort;
    }

    @Override
    public UserEnableStatus changeAccountStatus(User userId, boolean isEnable) {
        if (userId.getId() == null) {
            throw new RuntimeException("사용자 정보를 확인해주세요");
        }
        userPort.changeActivate(userId.getId(), isEnable);
        return UserEnableStatus.getStatus(isEnable);
    }

}
