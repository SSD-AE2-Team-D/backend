package com.guidelk.tourism.service;

import com.guidelk.tourism.entity.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface PageService {
    ResponseEntity createPage(Page page);
}
