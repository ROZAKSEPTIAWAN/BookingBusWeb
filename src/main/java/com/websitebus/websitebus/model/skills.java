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
@Table(name ="skills")


public class skills {
    
    @Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
    long id;

    String judul;
    int persen;
}
