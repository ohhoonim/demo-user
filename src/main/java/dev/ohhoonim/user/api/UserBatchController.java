package dev.ohhoonim.user.api;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ohhoonim.user.User;
import dev.ohhoonim.user.activity.service.UserBatchService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-batch")
public class UserBatchController {

    private final UserBatchService userBatchService;
    
    @PostMapping("/batchUpdate")
    public int batchUpdate(List<User> users) {
        return userBatchService.batchUpdate(users);
    }

    
    @PostMapping("/applyPendingChangesToUser")
    public int applyPendingChangesToUser(LocalDateTime effectiveDate) {
        return userBatchService.applyPendingChangesToUser(effectiveDate);
    }

    
    @PostMapping("/batchRegister")
    public int batchRegister(List<User> users) {
        return userBatchService.batchRegister(users);
    }

    
    @PostMapping("/translateCsvToUsers")
    public List<User> translateCsvToUsers(File csv) {
        return userBatchService.translateCsvToUsers(csv);
    }

    
    @PostMapping("/translateExcelToUsers")
    public List<User> translateExcelToUsers(File excel) {
        return userBatchService.translateExcelToUsers(excel);
    }

    
    @PostMapping("/fetchHrSystemToPendingChange")
    public int fetchHrSystemToPendingChange() {
        return userBatchService.fetchHrSystemToPendingChange();
    }
}
