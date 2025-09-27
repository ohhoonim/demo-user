package dev.ohhoonim.component.auditing.change;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.ohhoonim.component.auditing.dataBy.Entity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChangedEventListener {

    private final ChangedEventRepository<?> repository;

    @EventListener
    public void changedEvent(ChangedEvent<? extends Entity> event) {
        switch (event) {
            case CreatedEvent c -> repository.recordingChangedData(c);
            case ModifiedEvent m -> repository.recordingChangedData(m);
            case LookupEvent l -> new RuntimeException("Not supported event");
        }
    }
}
