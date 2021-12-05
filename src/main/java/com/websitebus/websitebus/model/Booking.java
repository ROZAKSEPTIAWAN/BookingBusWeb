package com.websitebus.websitebus.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="Booking")
public class Booking {
    @Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(unique =false,name ="id_keberangkatan",referencedColumnName="id")
	private Keberangkatan keberangkatan;

	// @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name ="nik",referencedColumnName="nik")
	private Penumpang penumpang;

    
}
