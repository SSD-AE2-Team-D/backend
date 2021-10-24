package com.guidelk.tourism.controller;

import com.guidelk.tourism.entity.Role;
import com.guidelk.tourism.service.RoleService;
import com.guidelk.tourism.util.MasterDataStatus;
import com.guidelk.tourism.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_config@role_CREATE')")
    public ResponseEntity createRole(@RequestBody Role role) {
        return this.roleService.createRole(role);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_config@role_UPDATE')")
    public ResponseEntity updateRole(@RequestBody Role role) {
        return this.roleService.updateRole(role);
    }

    @DeleteMapping("{roleId}")
    @PreAuthorize("hasRole('ROLE_config@role_DELETE')")
    public ResponseEntity<Role> deleteRole(@PathVariable("roleId") Integer roleId) {
        return this.roleService.deleteRole(roleId);
    }

    @PostMapping("/roleSearch")
    @PreAuthorize("hasRole('ROLE_config@role_VIEW')")
    public List<Role> roleSearch(@RequestBody RoleVo roleVo) {
        return this.roleService.roleSearch(roleVo);
    }

    @GetMapping("/getMasterStatusList")
    @PreAuthorize("hasRole('ROLE_config@role_VIEW')")
    public List<MasterDataStatus> findStatusList() {
        return Arrays.asList(MasterDataStatus.values());
    }

}
