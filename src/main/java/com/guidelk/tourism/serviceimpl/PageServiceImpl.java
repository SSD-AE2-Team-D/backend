package com.guidelk.tourism.serviceimpl;

import com.guidelk.tourism.entity.*;
import com.guidelk.tourism.repository.ModuleRepository;
import com.guidelk.tourism.repository.PageRepository;
import com.guidelk.tourism.service.PageService;
import com.guidelk.tourism.util.MasterDataStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;
    private final ModuleRepository moduleRepository;
    private final Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

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

    @Override
    public List<Page> getPagesByModule(Integer moduleId) {
        List<Page> pages = new ArrayList<>();
        try {
            JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
            QPage qPage = QPage.page;

            pages = queryFactory.select(qPage).distinct()
                    .from(qPage)
                    .where(qPage.moduleId.eq(moduleId))
                    .where(qPage.status.notIn(MasterDataStatus.DELETED.getStatusSeq()))
                    .orderBy(qPage.orderIndex.asc())
                    .fetch();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return pages;
    }
}
