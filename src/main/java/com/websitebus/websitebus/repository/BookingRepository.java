package com.websitebus.websitebus.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.websitebus.websitebus.model.Booking;
import com.websitebus.websitebus.model.Penumpang;

import java.util.List;



public interface BookingRepository extends JpaRepository<Booking,Long> {
	
	@Modifying
	@Query
	(value= "delete from booking where id = ?1",nativeQuery = true)
	void deleteByid(int id);

	@Query
	(value ="SELECT booking.id ,booking.nik, penumpang.nama,booking.id_keberangkatan,keberangkatan.tanggal,keberangkatan.no_polisi, keberangkatan.kelas,keberangkatan.harga FROM keberangkatan INNER JOIN booking ON keberangkatan.id = booking.id_keberangkatan INNER JOIN penumpang on penumpang.nik = booking.nik",nativeQuery = true)
	List<Booking> findalldetail();
	List<Booking> findByPenumpang(Penumpang nik);
    
}
