package com.guidelk.tourism.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphQuerydslPredicateExecutor;
import com.guidelk.tourism.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, EntityGraphJpaRepository<User, Integer>, EntityGraphQuerydslPredicateExecutor<User> {
    User findByUserNameIgnoreCaseAndStatusNot(String userName, Integer status);

    User findByEmailIgnoreCaseAndStatusNot(String email, Integer status);

    User findByUserNameIgnoreCase(String userName);
}
