package dev.ohhoonim.user.activity.port;

import java.util.Optional;

import dev.ohhoonim.component.auditing.dataBy.Id;
import dev.ohhoonim.user.User;

public interface UserPort {

    Optional<User> findUser(String username, String encodedPassword);

    void changeActivate(Id id, boolean isEnable);
    
}
