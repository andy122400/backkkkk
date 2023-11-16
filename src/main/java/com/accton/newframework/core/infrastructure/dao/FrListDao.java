package com.accton.newframework.core.infrastructure.dao;

import com.accton.newframework.core.infrastructure.entities.FrListEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FrListDao extends JpaRepository<FrListEntity,Long>, JpaSpecificationExecutor<FrListEntity> {

    @EntityGraph(attributePaths = "detail")
    Optional<FrListEntity> findOneWithDetailById(Long id);
}
