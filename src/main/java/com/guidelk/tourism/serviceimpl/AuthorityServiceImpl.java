package com.guidelk.tourism.serviceimpl;

import com.guidelk.tourism.entity.*;
import com.guidelk.tourism.repository.AuthorityRepository;
import com.guidelk.tourism.service.AuthorityService;
import com.guidelk.tourism.util.DefaultAuthority;
import com.guidelk.tourism.util.MasterDataStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Authority> getDefaultAuthorityList() {
        List<Authority> authorityList = new ArrayList<>();
        for (DefaultAuthority defaultAuthority : DefaultAuthority.values()) {
            Authority authority = new Authority();
            authority.setAuthorityName(defaultAuthority.getAuthorityName());
            authorityList.add(authority);
        }
        return authorityList;
    }

    @Override
    public List<Authority> getAuthorityList(String userName, Integer organizationId) {
        List<Authority> authorityList = new ArrayList<>();
        try {
            JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
            QOrganization qOrganization = QOrganization.organization;
            QUser qUser = QUser.user;
            QPage qPage = QPage.page;
            QAuthority qAuthority = QAuthority.authority;
            QModule qModule = QModule.module;
            QRole qRole = QRole.role;
            authorityList = queryFactory.select(qAuthority).distinct()
                    .from(qOrganization, qUser, qRole, qPage, qModule, qAuthority)
                    .where(qOrganization.organizationId.eq(organizationId))
                    .where(qUser.userName.eq(userName))
                    .where(qUser.enabled.eq(true))
                    .where(qOrganization.status.ne(MasterDataStatus.DELETED.getStatusSeq()))
                    .where(qUser.status.ne(MasterDataStatus.DELETED.getStatusSeq()))
                    .where(qUser.status.ne(MasterDataStatus.DELETED.getStatusSeq()))
                    .where(qRole.status.ne(MasterDataStatus.DELETED.getStatusSeq()))
                    .where(qAuthority.status.ne(MasterDataStatus.DELETED.getStatusSeq()))
                    .where(qModule.status.ne(MasterDataStatus.DELETED.getStatusSeq()))
                    .fetch();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return authorityList;
    }
}
