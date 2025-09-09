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
public class ChangeDetail implements Entity {

    private Id changeDetailId;
    private Id pendingChangeId;
    private SignUserAttribute oldValue;
    private SignUserAttribute newValue;

    @Override
    public Id getId() {
        return changeDetailId;
    }
}
