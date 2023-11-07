package com.accton.newframework.core.infrastructure.dao;

import com.accton.newframework.core.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    @EntityGraph(attributePaths = "roles")
    Optional<UserEntity> findOneWithRolesByUserName(String userName);
    Optional<UserEntity> findUserEntitiesByUserName(String userName);

}
