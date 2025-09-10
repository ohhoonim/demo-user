package dev.ohhoonim.user.infra;

import java.util.Optional;

import org.springframework.stereotype.Component;

import dev.ohhoonim.component.sign.SignUser;
import dev.ohhoonim.component.sign.activity.port.SignUserPort;

@Component
public class SignUserAdaptor implements SignUserPort {

    @Override
    public Optional<SignUser> findByUsernamePassword(String name, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsernamePassword'");
    }

}
