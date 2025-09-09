package dev.ohhoonim.component.sign.api;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.ohhoonim.component.sign.SignUser;
import dev.ohhoonim.component.sign.activity.SignActivity;
import dev.ohhoonim.component.sign.infra.SignVo;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sign")
public class SignController {

    private final SignActivity signService;

    public SignController(SignActivity signService) {
        this.signService = signService;
    }

    @PostMapping("/in")
    public SignVo login(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        var loginUser = new SignUser(username, password);
        return signService.signIn(loginUser);
    }

    @PostMapping("/out")
    public void logout() {

    }

    @GetMapping("/refresh")
    public SignVo refresh(HttpServletRequest request) {
        var bearerToken = request.getHeader("Authorization");
        if (!(StringUtils.hasText(bearerToken) &&
                bearerToken.startsWith("Bearer "))) {
            throw new RuntimeException("토큰이 존재하지 않습니다");
        }
        return signService.refresh(bearerToken.substring(7));
    }

    @GetMapping("/{callback}")
    public String signOutMain(@PathVariable("callback") String callback) {
        return callback;
    }
}
