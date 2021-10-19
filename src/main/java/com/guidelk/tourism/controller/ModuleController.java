package com.guidelk.tourism.controller;

import com.guidelk.tourism.entity.Module;
import com.guidelk.tourism.service.ModuleService;
import com.guidelk.tourism.util.MasterDataStatus;
import com.guidelk.tourism.vo.ModuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("modules")
public class ModuleController {

    private final ModuleService moduleService;

    @Autowired
    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @PostMapping
     @PreAuthorize("hasRole('ROLE_config@module_CREATE')")
    public ResponseEntity createModule(@RequestBody Module module) {
        return this.moduleService.createModule(module);
    }

    @PutMapping
     @PreAuthorize("hasRole('ROLE_config@module_UPDATE')")
    public ResponseEntity updateModule(@RequestBody Module module) {
        return this.moduleService.updateModule(module);
    }

    @DeleteMapping("{moduleId}")
    @PreAuthorize("hasRole('ROLE_config@module_DELETE')")
    public ResponseEntity<Module> deleteModule(@PathVariable("moduleId") Integer moduleId) {
        return this.moduleService.deleteModule(moduleId);
    }

    @PostMapping("/moduleSearch")
    @PreAuthorize("hasRole('ROLE_config@module_VIEW')")
    public List<Module> moduleSearch(@RequestBody ModuleVo moduleVo) {
        return this.moduleService.moduleSearch(moduleVo);
    }

    @GetMapping("/getMasterStatusList")
    // @PreAuthorize("hasRole('ROLE_config@module_VIEW')")
    public List<MasterDataStatus> findStatusList() {
        return Arrays.asList(MasterDataStatus.values());
    }

}
