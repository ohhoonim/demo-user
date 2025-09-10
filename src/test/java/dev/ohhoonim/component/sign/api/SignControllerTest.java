package dev.ohhoonim.component.sign.api;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ohhoonim.component.container.ResponseCode;
import dev.ohhoonim.component.container.Search;
import dev.ohhoonim.component.sign.activity.SignActivity;
import dev.ohhoonim.component.sign.api.SignController.LoginReq;

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
    void signInTest() throws JsonProcessingException {
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
