package dev.ohhoonim.component.sign;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import dev.ohhoonim.component.sign.activity.service.SignService;
import dev.ohhoonim.component.sign.api.SignController;

@WebMvcTest(SignController.class)
class SignControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @MockitoBean 
    private SignService signService;

    @Test
    @DisplayName("로그인 API가 정상적으로 동작하는지 확인")
    void loginTest() {
        // 예시: /api/sign/login 엔드포인트 테스트
        var response = mockMvcTester.post().uri("/sign/in")
                .param("username", "testuser")
                .param("password", "testpass")
                .accept(MediaType.APPLICATION_JSON)
                .assertThat().apply(print());
    }

}
