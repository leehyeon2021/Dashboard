package dashboard.controller;

import dashboard.model.dao.StaffDao;
import dashboard.model.dto.StaffDto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class StaffController {
    @Autowired
    public StaffDao staffDao;

    // 1. 사원 등록
    @PostMapping("/staff")
    public boolean sPost(@RequestBody StaffDto staffDto){
        boolean result = staffDao.sPost( staffDto );
        return result;
    }

    // 2. 사원 전체 목록
    @GetMapping("/staff")
    public List<StaffDto> sFindAll(){
        List<StaffDto> result = staffDao.sFindAll();
        return result;
    }

    // 3. 사원 수정
    @PutMapping("/staff")
    public boolean sUpdate(@RequestBody StaffDto staffDto){
        boolean result = staffDao.sUpdate( staffDto );
        return result;
    }

    // 4. 사원 삭제
    @DeleteMapping("/staff")
    public boolean sDelete(@RequestParam int scode){
        boolean result = staffDao.sDelete( scode );
        return result;
    }



}
