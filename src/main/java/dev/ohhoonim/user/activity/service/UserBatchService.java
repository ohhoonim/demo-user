package dev.ohhoonim.user.activity.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.ohhoonim.user.PendingChange;
import dev.ohhoonim.user.User;
import dev.ohhoonim.user.activity.BatchRegisterActivity;
import dev.ohhoonim.user.activity.BatchUpdateActivity;
import dev.ohhoonim.user.activity.port.PendingChangePort;

@Service
public class UserBatchService implements BatchRegisterActivity, BatchUpdateActivity {

    private final UserService userService;
    private final PendingChangePort pendingChangePort;

    public UserBatchService(UserService userService, PendingChangePort pendingChangePort) {
        this.userService = userService;
        this.pendingChangePort = pendingChangePort;
    }

    @Override
    public void batchUpdate(List<User> users) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'batchUpdate'");
    }

    @Override
    public List<PendingChange> registPendingChanges(User userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registPendingChanges'");
    }

    @Override
    public void batchRegister(List<User> users) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'batchRegister'");
    }

    @Override
    public List<User> translateCsvToUsers(File csv) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'translateCsvToUsers'");
    }

    @Override
    public List<User> translateExcelToUsers(File excel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'translateExcelToUsers'");
    }

    @Override
    public List<User> fetchHrSystem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetchHrSystem'");
    }
    
}
