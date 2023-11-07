package com.accton.newframework.core.infrastructure.dao;

import com.accton.newframework.core.infrastructure.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<RoleEntity,Long> {
    Optional<RoleEntity> findRoleEntitiesByCode(String code);
}
