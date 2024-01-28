package easysalesassistant.api.controllers;

import easysalesassistant.api.entity.Branch;
import easysalesassistant.api.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/branch",produces = MediaType.APPLICATION_JSON_VALUE)
public class BranchController {

    @Autowired
    BranchService branchService;

    @PostMapping(value = {"/",""})
    public Branch saveBranch(@RequestBody Branch branch){
        return branchService.saveBranch(branch);
    }
}
