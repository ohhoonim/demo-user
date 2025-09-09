package dev.ohhoonim.component.sign.activity;

import dev.ohhoonim.component.sign.SignUser;
import dev.ohhoonim.component.sign.infra.SignVo;

public interface SignActivity {

    SignVo refresh(String refreshToken);

    SignVo signIn(SignUser loginTryUser);
}
