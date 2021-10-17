package com.guidelk.tourism.service;

import com.guidelk.tourism.entity.Role;
import com.guidelk.tourism.vo.RoleVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleService {

    ResponseEntity createRole(Role role);

    ResponseEntity updateRole(Role role);

    ResponseEntity<Role> deleteRole(Integer roleId);

    List<Role> roleSearch(RoleVo roleVo);
}
