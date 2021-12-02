package com.websitebus.websitebus.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name ="resume")


public class resume {

    
    @Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
    long id;

    String judul;
    String periode;
    String deskripsi;
    String kategori;
    
}
