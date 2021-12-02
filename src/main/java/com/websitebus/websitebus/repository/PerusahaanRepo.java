package com.websitebus.websitebus.repository;

import com.websitebus.websitebus.model.Perusahaan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PerusahaanRepo extends JpaRepository<Perusahaan,Long>{
    
}
