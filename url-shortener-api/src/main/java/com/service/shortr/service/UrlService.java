package com.service.shortr.service;

import org.springframework.stereotype.Service;

import com.service.shortr.persistance.entity.Url;
import com.service.shortr.persistance.repository.UrlRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @CachePut(value = "urls", key="#url.hash")
    public Url save(Url url) {
        return this.urlRepository.save(url);
    }

    @Cacheable(value = "urls", key = "#id")
    public Url findById(String id) throws Exception {
        Url record = this.urlRepository.findById(id).get();
        if(record == null) throw new IllegalArgumentException("Id doesn't exist");
        
        return record;
    }

    @CacheEvict(value = "urls", key="#id")
    public void deleteById(String id) throws Exception {
        if(!urlRepository.existsById(id)) {
            throw new Exception("Id doesn't exist");
        }
        
        this.urlRepository.deleteById(id);
    }
}