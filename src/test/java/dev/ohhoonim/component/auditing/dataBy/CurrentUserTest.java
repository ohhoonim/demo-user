package dev.ohhoonim.component.auditing.dataBy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
public class CurrentUserTest {
    
    @Test
    @WithMockUser(username="matthew")
    void currentUser() {
        String username = CurrentUser.getUsername();
        assertThat(username).isEqualTo("matthew");
    }
}
