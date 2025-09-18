package dev.ohhoonim.user.activity;

import java.util.List;

import dev.ohhoonim.user.UserAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {
    private String username;
    private String name;
    private boolean enabled;
    private boolean locked;
    private List<UserAttribute> attributes;
}
