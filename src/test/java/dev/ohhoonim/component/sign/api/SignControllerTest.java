package dev.ohhoonim.component.sign.api;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import dev.ohhoonim.component.container.ResponseCode;
import dev.ohhoonim.component.container.Search;
import dev.ohhoonim.component.sign.activity.SignActivity;
import dev.ohhoonim.component.sign.api.SignController.LoginReq;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(SignController.class)
public class SignControllerTest {

    @Autowired
    MockMvcTester mockMvcTester;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    SignActivity signActivity;

    @Test
    @WithMockUser
    void signInTest() throws JacksonException {
        var search = new Search<>(
                new LoginReq(null, null),
                null);
        
        mockMvcTester.post().with(csrf())
                .uri("/sign/in")
                .content(objectMapper.writeValueAsString(search))
                .contentType(MediaType.APPLICATION_JSON)
                .assertThat().apply(print())
                .bodyJson().extractingPath("$.code")
                .isEqualTo(ResponseCode.ERROR.toString());
    }
}
