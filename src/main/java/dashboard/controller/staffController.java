package dashboard.controller;

import dashboard.model.dto.staffDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class staffController {
    @Autowired
    public staffDto staffDto;
}
