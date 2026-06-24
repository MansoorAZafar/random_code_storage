package com.service.shortr.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.service.shortr.persistance.entity.Url;

public interface UrlRepository extends JpaRepository<Url, String> {}
