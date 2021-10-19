package com.guidelk.tourism.controller;

import com.guidelk.tourism.entity.Authority;
import com.guidelk.tourism.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("authorities")
public class AuthorityController {

    private final AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping("/defaultAuthorities")
    @PreAuthorize("hasRole('ROLE_config@page_VIEW')")
    public List<Authority> getDefaultAuthorityList() {
       return this.authorityService.getDefaultAuthorityList();
    }
}
