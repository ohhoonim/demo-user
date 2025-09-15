package dev.ohhoonim.user.activity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.ohhoonim.component.auditing.dataBy.Id;
import dev.ohhoonim.user.User;
import dev.ohhoonim.user.activity.port.UserPort;
import dev.ohhoonim.user.activity.service.ActivateService;
import dev.ohhoonim.user.activity.service.LoginService;

@ExtendWith(MockitoExtension.class)
public class UserActivityTest {

    @InjectMocks LoginService loginService;

    @Mock UserPort userPort;
    @Mock PasswordEncoder passwordEncoder;
    
    @Test
    void isValidUser() {
        when(userPort.findUser(any(), any())).thenReturn(Optional.of(new User()));
        when(passwordEncoder.encode(any())).thenReturn("test");

        var findedUser = loginService.isValidUser("matthew", "test");
        assertThat(findedUser).isInstanceOf(User.class);
    }


    @Test
    void notFoundUser() {
        when(userPort.findUser(any(), any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any())).thenReturn("test");

        assertThatThrownBy(() -> loginService.isValidUser("matthew", "test"))
                .hasMessageContaining("회원을 찾을 수 없습니다");
    }

    @InjectMocks ActivateService activateService;

    @Test
    @DisplayName("계정 활성/비활성")
    void changeAccountStatus() {
       
        var userEnableStatus = activateService.changeAccountStatus(new User(new Id()), true);
        assertThat(userEnableStatus).isEqualTo(UserEnableStatus.Activate);

        verify(userPort, times(1)).changeActivate(any(), anyBoolean());
    }

    @Test
    @DisplayName("User id가 있어야한다")
    void failActivateNoUserId() {
        assertThatThrownBy(() -> activateService.changeAccountStatus(new User(), true))
                .hasMessageContaining("사용자 정보를 확인해주세요");

    }
}


















