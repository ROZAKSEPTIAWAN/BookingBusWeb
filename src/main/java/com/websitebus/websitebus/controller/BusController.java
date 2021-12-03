package com.websitebus.websitebus.controller;

import java.util.List;


import com.websitebus.websitebus.model.Booking;

import com.websitebus.websitebus.model.Penumpang;
import com.websitebus.websitebus.model.Perusahaan;
import com.websitebus.websitebus.model.Keberangkatan;
import com.websitebus.websitebus.model.Fk;
import com.websitebus.websitebus.model.Jurusan;
import com.websitebus.websitebus.repository.BookingRepository;
import com.websitebus.websitebus.repository.JurusanRepository;
import com.websitebus.websitebus.repository.KeberangkatanRepository;
import com.websitebus.websitebus.repository.PenumpangRepository;
import com.websitebus.websitebus.repository.PerusahaanRepo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller


public class BusController {
    
	@Autowired
	PenumpangRepository penumpangRepo;
	
	@Autowired
	KeberangkatanRepository keberangkatanRepo;
	
	@Autowired
	BookingRepository bookingRepo;

	@Autowired
	JurusanRepository jurusanRepo;

	@Autowired
	PerusahaanRepo perusahaanRepo;
	
	@PostMapping("/insertPenumpang")
	public String postData (@RequestBody Penumpang penumpang) {
		penumpangRepo.save(penumpang);
		return "Data penumpang berhasil ditambahkan";
		
	}
	
	
	
	@GetMapping("/index")
	public List<Keberangkatan> index(){
		return keberangkatanRepo.findAllindex();
	}

	@GetMapping("/indexaneh")
	public List<Fk> indexData(){
		return keberangkatanRepo.findByidJurusan();

	}


	@GetMapping("/indexPerusahaan")
	public String getAllDataJPerusahaan (Model model){
		List<Perusahaan> data = perusahaanRepo.findAll();
		model.addAttribute("data",data);
		return "Perusahaan";
	}

	@GetMapping("/indexJurusan")
	public String getAllDataJurusan (Model model){
		List<Jurusan> data = jurusanRepo.findAll();
		model.addAttribute("data",data);
		return "Jurusan";
	}

	@GetMapping("/indexKeberangkatan")
	public String getAllDataPerusahaan (Model model){
		List<Keberangkatan> data = keberangkatanRepo.findAll();
		model.addAttribute("data",data);
		return "add/tes";
	}

	
	
	// adding data

	@GetMapping("/dashboard")
	public String dashboard( Model model) {
	
		return "add/Dashboard";
	}

	// penumpang
	@GetMapping("/addPenumpang")
	public String add (Model model ) {
		model.addAttribute("data",new Penumpang());
		return "add/addPenumpang";
	}

	@PostMapping("/save")
	public String saveData(@ModelAttribute("data") Penumpang penumpang) {
		penumpangRepo.save(penumpang);
		return "redirect:/indexPenumpang "; //meminta browser untuk request url lain
	}

	// Keberangkatan
	@GetMapping("/addKeberangkatan")
	public String addKeberangkatan (Model model ) {
		model.addAttribute("data",new Keberangkatan());
		return "add/addKeberangkatan";
	}


	@GetMapping("/indexBooking")
	public String getAllDataBooking (Model model){
		List<Booking> data = bookingRepo.findAll();
		model.addAttribute("data",data);
		return "Booking";
	}

	//add form booking
	@GetMapping("/booking")
	public String addBooking (Model model ) {
		model.addAttribute("data",new Booking());
		return "add/formbooking";
	}

	// state map detail booking
	@PostMapping("/detailBooking")
	public String getdetailBooking (@ModelAttribute ("data") Booking booking,Model model){
		bookingRepo.save(booking);
		List<Booking> data = bookingRepo. findByPenumpang(booking.getPenumpang());
		model.addAttribute("data",data.get(data.size()-1));
		return "add/detailbooking";
	}

	


	///cancel booking
	// @Transactional
	@GetMapping("/delete/{id}")
	public String delete (@PathVariable ("id") long id) {
		bookingRepo.deleteById(id);
		return "redirect:/indexBooking"; //meminta browser untuk request url lain
	}

	
	@GetMapping("/cancel")
	public String cancelBooking (Model model){
		List<Booking> data = bookingRepo.findAll();
		model.addAttribute("data",data);
		return "add/cancelBooking";
	}


    @PostMapping("/carikeberangkatanresult")
    public String cariKeberangkatanResult(@ModelAttribute("data") Keberangkatan formData, Model model2) {
        String alamatHasil = "/";
        String tanggal = formData.getTanggal();
        String terminalAwal = formData.getJurusan().getTerminal_awal();
        List<Fk> keberangkatanBeneran = keberangkatanRepo.getDetail(terminalAwal, tanggal);
        if (keberangkatanBeneran.size() == 0) {
            alamatHasil = "new/showKenihilanKeberangkatan";
        } else {
            model2.addAttribute("data", keberangkatanBeneran);
            alamatHasil = "new/showDetailKeberangkatan";
        }
        return alamatHasil;
    }
	

}
