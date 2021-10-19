package com.guidelk.tourism.serviceimpl;

import com.guidelk.tourism.entity.Authority;
import com.guidelk.tourism.entity.Module;
import com.guidelk.tourism.entity.Page;
import com.guidelk.tourism.repository.ModuleRepository;
import com.guidelk.tourism.repository.PageRepository;
import com.guidelk.tourism.service.PageService;
import com.guidelk.tourism.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;
    private final ModuleRepository moduleRepository;

    @Autowired
    public PageServiceImpl(PageRepository pageRepository,
                           ModuleRepository moduleRepository) {
        this.pageRepository = pageRepository;
        this.moduleRepository = moduleRepository;
    }

    @Override
    @Transactional
    public ResponseEntity createPage(Page page) {
        ResponseEntity responseEntity;
        Page dbPage = this.pageRepository.findByPageNameContainsIgnoreCaseAndStatusNot(page.getPageName(), MasterDataStatus.DELETED.getStatusSeq());
        if (dbPage != null) {
            responseEntity = new ResponseEntity<>("Duplicate Record", HttpStatus.BAD_REQUEST);
        } else {
            Optional<Module> module = this.moduleRepository.findById(page.getModuleId());
            for (Authority authority : page.getAuthorities()) {
                authority.setStatus(MasterDataStatus.APPROVED.getStatusSeq());
                authority.setAuthorityName("ROLE_" + module.get().getUrlPattern() + "@" + page.getUrlPattern() + "_" + authority.getAuthorityName());
            }
            this.pageRepository.save(page);
            responseEntity = new ResponseEntity<>(page, HttpStatus.CREATED);
        }

        return responseEntity;
    }
}
