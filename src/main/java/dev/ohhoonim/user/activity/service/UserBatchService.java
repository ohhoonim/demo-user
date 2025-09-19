package dev.ohhoonim.user.activity.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import dev.ohhoonim.component.auditing.dataBy.Id;
import dev.ohhoonim.user.PendingChange;
import dev.ohhoonim.user.User;
import dev.ohhoonim.user.UserAttribute;
import dev.ohhoonim.user.activity.BatchRegisterActivity;
import dev.ohhoonim.user.activity.BatchUpdateActivity;
import dev.ohhoonim.user.activity.port.HrClient;
import dev.ohhoonim.user.activity.port.PendingChangePort;

@Service
public class UserBatchService implements BatchRegisterActivity, BatchUpdateActivity {

    private final UserService userService;
    private final PendingChangePort pendingChangePort;
    private final HrClient hrClient;

    public UserBatchService(UserService userService,
            PendingChangePort pendingChangePort,
            HrClient hrClient) {
        this.userService = userService;
        this.pendingChangePort = pendingChangePort;
        this.hrClient = hrClient;
    }

    @Override
    public int batchUpdate(List<User> users) {
        var addedCount = 0;
        for (var userInfo : users) {
            try {
                userService.modifyInfo(userInfo);
                addedCount++;
            } catch (Exception e) {
                continue;
            }
        }
        return addedCount;
    }

    @Override
    public int fetchHrSystemToPendingChange() {
        // FIXME 연동시스템 있을시 여기에 코드 추가

        // hrClient.fetchHrUsers();
        return 0;
    }

    @Override
    public int applyPendingChangesToUser(LocalDateTime effectiveDate) {
        List<PendingChange> pendings = pendingChangePort.pendings(effectiveDate);

        int count = 0;
        for (var pending : pendings) {
            try {
                var user = pending.getUserId();
                var userAttribute = pending.getChangeDetails().stream().map(detail -> {
                    return new UserAttribute(
                            null,
                            detail.getNewValue().getName(),
                            detail.getNewValue().getValue());
                }).toList();

                user.setAttributes(userAttribute);
                userService.modifyInfo(user);
                count++;
            } catch (Exception e) {
                continue;
            }
        }
        return count;
    }

    @Override
    public int batchRegister(List<User> users) {
        int count = 0;
        for (var user : users) {
            try {
                userService.register(user);
                count++;
            } catch (Exception e) {
                continue;
            }
        }
        return count;
    }

    @Override
    public List<User> translateCsvToUsers(File csv) {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    User user = new User(new Id());
                    user.setUsername(values[1].trim());
                    user.setName(values[2].trim());
                    user.setEmail(values[3].trim());

                    user.setAttributes(List.of(
                            new UserAttribute("job", values[4].trim())
                    // FIXME attribute 부분 보강 할 것 
                    ));

                    users.add(user);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 읽는 도중 실패하였습니다");
        }
        return users;
    }

    @Override
    public List<User> translateExcelToUsers(File excel) {
        List<User> users = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excel);
                Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트 

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || row.getPhysicalNumberOfCells() < 5) {
                    // row.getPhysicalNumberOfCells()는 필수값 입력갯수 체크용 
                    continue;
                }

                User user = new User(new Id());
                // 셀 인덱스는 0부터 시작합니다.
                user.setUsername(getStringCellValue(row.getCell(1)).trim());
                user.setName(getStringCellValue(row.getCell(2)).trim());
                user.setEmail(getStringCellValue(row.getCell(3)).trim());

                user.setAttributes(List.of(
                        new UserAttribute("job", getStringCellValue(row.getCell(4)).trim())
                // FIXME : 추가 속성 설정 
                ));

                users.add(user);
            }
        } catch (IOException e) {
            throw new RuntimeException("엑셀 파일 읽기 실패: " + e.getMessage(), e);
        }
        return users;
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        // 셀의 타입에 따라 값을 읽습니다.
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                // 수식 셀을 처리할 수 있습니다.
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
