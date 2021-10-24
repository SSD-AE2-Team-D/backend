package com.guidelk.tourism.service;

import com.guidelk.tourism.entity.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PageService {
    ResponseEntity createPage(Page page);

    List<Page> getPagesByModule(Integer moduleId);
}
