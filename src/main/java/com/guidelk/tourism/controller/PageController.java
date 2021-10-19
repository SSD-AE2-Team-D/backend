package com.guidelk.tourism.controller;

import com.guidelk.tourism.entity.Organization;
import com.guidelk.tourism.entity.Page;
import com.guidelk.tourism.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pages")
public class PageController {

    private final PageService pageService;

    @Autowired
    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_config@page_CREATE')")
    public ResponseEntity createPage(@RequestBody Page page) {
        return this.pageService.createPage(page);
    }
}
