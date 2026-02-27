package dashboard.controller;

import dashboard.model.dao.VacationDao;
import dashboard.model.dto.VacationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard/vacation")
public class VacationController {
    @Autowired private VacationDao vacationDao;

    @PostMapping
    public boolean vPost(@RequestBody VacationDto vacationDto){
        boolean result = vacationDao.vPost(vacationDto);
        return result;
    }

    @GetMapping
    public List<VacationDto> vFindAll(){
        List<VacationDto> result = vacationDao.vFindAll();
        return result;
    }

    @DeleteMapping
    public boolean dDelete(@RequestParam int vcode){
        boolean result = vacationDao.vDelete(vcode);
        return result;
    }


}
