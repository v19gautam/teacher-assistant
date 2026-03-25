package com.teacherassistant.repository;

import com.teacherassistant.model.Worksheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorksheetRepository extends JpaRepository<Worksheet, Long> {

    List<Worksheet> findByUserId(Long userId);

    Optional<Worksheet> findByShareId(String shareId);
}