package dashboard.controller;

import dashboard.model.dao.DepartmentDao;
import dashboard.model.dto.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard/department")
public class DepartmentController {
    @Autowired private DepartmentDao departmentDao;

    // 부서 등록
    @PostMapping
    public boolean dPost(@RequestParam String dname){
        boolean result = departmentDao.dPost(dname);
        return result;
    }

    // 부서 전체 목록
    @GetMapping
    public List<DepartmentDto> dFindAll(){
        List<DepartmentDto> result = departmentDao.dFindAll();
        return result;
    }

    // 부서 수정
    @PutMapping
    public boolean dUpdate(@RequestBody DepartmentDto departmentDto){
        boolean result = departmentDao.dUpdate(departmentDto);
        return result;
    }

    //부서 삭제
    @DeleteMapping
    public boolean dDelete(@RequestParam int dcode){
        boolean result = departmentDao.dDelete(dcode);
        return result;
    }
}
