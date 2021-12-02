package com.websitebus.websitebus.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="Bus")
public class Bus {
    @Id
	private String nomor_polisi;
	
	private int kapasitas;
	private String nama_supir;

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique =true,name ="nama_perusahaan",referencedColumnName="nama")
	private Perusahaan perusahaan;

    
    
}
