package dev.ohhoonim.component.sign.infra;

import java.util.Optional;

import org.springframework.stereotype.Component;

import dev.ohhoonim.component.sign.SignUser;
import dev.ohhoonim.component.sign.activity.port.UserPort;

@Component
public class UserAdaptor implements UserPort{

    @Override
    public void addUser(SignUser newUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUser'");
    }

    @Override
    public Optional<SignUser> findByUsernamePassword(String name, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsernamePassword'");
    }

    
}
