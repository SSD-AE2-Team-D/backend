package com.guidelk.tourism.repository;

import com.guidelk.tourism.entity.RefreshToken;
import com.guidelk.tourism.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    @Query(value = "delete from tourism.refresh_token where user_id =?1", nativeQuery = true)
    public void removeOnUserId(Integer userId);
}
