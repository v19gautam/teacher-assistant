package com.teacherassistant.repository;

import com.teacherassistant.model.Worksheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorksheetRepository extends JpaRepository<Worksheet, Long> {

    List<Worksheet> findByUserId(Long userId);

}