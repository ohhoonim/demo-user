package dev.ohhoonim.user.infra;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.ohhoonim.component.sign.Authority;
import dev.ohhoonim.component.sign.activity.port.AuthorityPort;

@Component
public class AuthorityAdaptor implements AuthorityPort {

    @Override
    public List<Authority> authoritiesByUsername(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authoritiesByUsername'");
    }

}
