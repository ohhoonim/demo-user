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
    private String attributeName;
    private UserAttribute oldValue;
    private UserAttribute newValue;

    @Override
    public Id getId() {
        return changeDetailId;
    }
}
