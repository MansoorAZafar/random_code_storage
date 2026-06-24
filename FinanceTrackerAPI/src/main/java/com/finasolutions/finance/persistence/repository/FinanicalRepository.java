package com.finasolutions.finance.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finasolutions.finance.persistence.entity.FinanicalRecord;

public interface FinanicalRepository 
    extends JpaRepository<FinanicalRecord, Long> {}
