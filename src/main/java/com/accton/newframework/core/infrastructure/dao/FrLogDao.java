package com.accton.newframework.core.infrastructure.dao;

import com.accton.newframework.core.infrastructure.entities.FrLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrLogDao extends JpaRepository<FrLogEntity,Long> {
}
