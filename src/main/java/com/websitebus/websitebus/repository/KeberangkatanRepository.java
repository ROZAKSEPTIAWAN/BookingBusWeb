package com.websitebus.websitebus.repository;

import com.websitebus.websitebus.model.Fk;
import com.websitebus.websitebus.model.Keberangkatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface KeberangkatanRepository extends JpaRepository<Keberangkatan,Long>{
    
    List<Keberangkatan> findAll();	

	
	@Query
	(value ="SELECT keberangkatan.id,jurusan.terminal_awal, jurusan.deskripsi, keberangkatan.tanggal as waktu, bus.nama_perusahaan as perusahaan, keberangkatan.kelas, keberangkatan.harga FROM keberangkatan INNER JOIN jurusan ON keberangkatan.id_jurusan = jurusan.id INNER JOIN bus ON keberangkatan.no_polisi = bus.nomor_polisi",nativeQuery = true)
	List<Fk> findAllindex();
	
	
	@Query
	(value= "SELECT keberangkatan.id, jurusan.deskripsi, keberangkatan.tanggal as waktu, bus.nama_perusahaan as perusahaan, keberangkatan.kelas, keberangkatan.harga FROM keberangkatan INNER JOIN jurusan ON keberangkatan.id_jurusan = jurusan.id INNER JOIN bus ON keberangkatan.no_polisi = bus.nomor_polisi WHERE jurusan.terminal_awal =?1 AND keberangkatan.tanggal LIKE ?2% AND bus.kapasitas > (SELECT COUNT(*) FROM booking WHERE booking.id_keberangkatan = keberangkatan.id )",nativeQuery = true)
	List<Fk> getDetail (String terminalawal,String tanggal);

	@Query
	(value = "SELECT jurusan.id FROM jurusan",nativeQuery= true)
	List<Fk> findByidJurusan();

	@Modifying
	@Query
	(value= "delete from booking where id = ?1",nativeQuery = true)
	void deleteByid(int id);

	@Query
	(value = "SELECT  booking.id, jurusan.terminal_awal, jurusan.deskripsi, keberangkatan.tanggal as waktu, bus.nama_perusahaan as perusahaan, keberangkatan.kelas, keberangkatan.harga FROM keberangkatan INNER JOIN jurusan ON keberangkatan.id_jurusan = jurusan.id INNER JOIN bus ON keberangkatan.no_polisi = bus.nomor_polisi INNER JOIN booking ON keberangkatan.id = booking.id_keberangkatan WHERE booking.nik = ?1 ",nativeQuery = true)
	List<Fk> findByNik(String nik_in);

}