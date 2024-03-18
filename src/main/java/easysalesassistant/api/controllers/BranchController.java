package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.branch.BranchDTO;
import easysalesassistant.api.dto.branch.BranchGetDTO;
import easysalesassistant.api.services.IBranchService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/branch",produces = MediaType.APPLICATION_JSON_VALUE)
public class BranchController {

    IBranchService branchService;

    BranchController(IBranchService branchService){
        this.branchService = branchService;
    }

    @PostMapping(value = {"/",""})
    public BranchDTO saveBranch(@RequestBody BranchDTO branch){
        return branchService.saveBranch(branch);
    }

    @GetMapping(value = {"/{idBranch}"})
    public BranchGetDTO getBranch(@PathVariable("idBranch") Long idBranch){
        return branchService.getBranchById(idBranch);
    }

    @PostMapping(value = {"/{idBranch}"})
    public BranchGetDTO updateBranch(@RequestBody BranchDTO branch,@PathVariable("idBranch") Long idBranch){
        return branchService.updateBranch(idBranch,branch);
    }

    @DeleteMapping(value = {"/{idBranch}"})
    public void deleteBranch(@PathVariable("idBranch") Long idBranch){
        branchService.deleteBranch(idBranch);
    }
}
