package com.websitebus.websitebus.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="Keberangkatan")
public class Keberangkatan {
    @Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	private int harga;
	private String kelas;
	private String tanggal;

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique =false,name ="no_polisi",referencedColumnName="nomor_polisi")
	private Bus bus;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique =false,name ="id_jurusan",referencedColumnName="id")
	private Jurusan jurusan;

    
}
