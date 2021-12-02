package com.websitebus.websitebus.repository;
import java.util.List;
import com.websitebus.websitebus.model.Penumpang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PenumpangRepository extends JpaRepository<Penumpang,String>{

    List<Penumpang> findBynik (String nik);

    @Query
	(value = "SELECT password FROM penumpang WHERE nik=?1",nativeQuery= true)
    String findByPassword(String nik);
    
}
   
