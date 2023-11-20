package com.accton.newframework.core.infrastructure.dao;


import com.accton.newframework.core.infrastructure.entities.AutoNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoNumberDao extends JpaRepository<AutoNumberEntity,Long> {
}
