package com.finasolutions.finance.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.finasolutions.finance.persistence.entity.FinanicalRecord;
import com.finasolutions.finance.persistence.repository.FinanicalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FinanicalRecordService {
    private final FinanicalRepository finanicalRepository;
    
    @CacheEvict(value = "records", key="'all'")
    public FinanicalRecord save(FinanicalRecord record) throws IllegalArgumentException {
        return this.finanicalRepository.save(record);
    }
    
    @Cacheable(value = "records", key="'all'")
    public List<FinanicalRecord> getAllRecords() {
        return this.finanicalRepository.findAll();
    }

    @CacheEvict(value = "records", key="'all'")
    public void deleteById(long id) throws IllegalArgumentException {
        if(!this.finanicalRepository.existsById(id)) {
            throw new IllegalArgumentException("No record with " + id + " exist");
        }

        this.finanicalRepository.deleteById(id);
    }
}
