package dev.ohhoonim.user.activity.port;

import java.time.LocalDateTime;
import java.util.List;

import dev.ohhoonim.user.PendingChange;

public interface PendingChangePort {

    List<PendingChange> pendings(LocalDateTime effectiveDate);
    
}
