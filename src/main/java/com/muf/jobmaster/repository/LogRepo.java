package com.muf.jobmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.muf.jobmaster.model.LogModel;

@Repository
public interface LogRepo extends JpaRepository<LogModel, String> {
    
}
