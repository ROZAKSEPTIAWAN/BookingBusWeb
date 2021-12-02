package com.websitebus.websitebus.service;

import com.websitebus.websitebus.repository.KeberangkatanRepository;


import org.springframework.beans.factory.annotation.Autowired;


public class KeberangkatanService {

    @Autowired
    KeberangkatanRepository keberangkatanRepo;

  


    public static Object findByDeskripsiAndTanggal(String deskripsi) {
        return null;
    }
    
}
