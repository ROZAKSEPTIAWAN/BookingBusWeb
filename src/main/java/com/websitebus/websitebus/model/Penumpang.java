package com.websitebus.websitebus.model;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name="Penumpang")
public class Penumpang {

    @Id
	private String nik;
	
	private String nama;
	private String telepon;
    private String password;
}




