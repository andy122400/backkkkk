package com.accton.newframework.core.infrastructure.dao;

import com.accton.newframework.core.infrastructure.entities.FrListDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrListDetailDao extends JpaRepository<FrListDetailEntity,Long> {
}
