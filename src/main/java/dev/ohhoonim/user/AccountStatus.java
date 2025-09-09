package dev.ohhoonim.user;

import dev.ohhoonim.component.auditing.dataBy.Entity;
import dev.ohhoonim.component.auditing.dataBy.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatus implements Entity {

    private Id signUserId;
    private boolean enabled;
    private boolean locked;
    private int failedAttemptCount;

    @Override
    public Id getId() {
        return this.signUserId;
    }
}
