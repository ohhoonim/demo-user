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
public class UserAttribute implements Entity {

    private Id attributeId;
    private String name;
    private String value;

    @Override
    public Id getId() {
        return this.attributeId;
    }

    public UserAttribute(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
