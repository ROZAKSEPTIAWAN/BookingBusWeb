package com.websitebus.websitebus.repository;

import java.util.List;

import com.websitebus.websitebus.model.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResumeRepository extends JpaRepository<resume,Long>{
    @Query
    (value = "select*from resume where kategori = 'Employment'",nativeQuery = true)
    List<resume> getEmployment();

    @Query
    (value = "select*from resume where kategori = 'education'",nativeQuery = true)
    List<resume> getEducation();


}
