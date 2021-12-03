package com.websitebus.websitebus.controller;



import java.util.List;

import com.websitebus.websitebus.model.Booking;
import com.websitebus.websitebus.model.Keberangkatan;
import com.websitebus.websitebus.model.Penumpang;
import com.websitebus.websitebus.repository.BookingRepository;
import com.websitebus.websitebus.repository.KeberangkatanRepository;
import com.websitebus.websitebus.repository.PenumpangRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    public String nik_in;

    @Autowired
	PenumpangRepository penumpangRepo;

    @Autowired
    KeberangkatanRepository keberangkatanRepo;

    @Autowired
    BookingRepository BookingRepo;
    @GetMapping("/indexPenumpang")
	public String getAllDataPenumpang (Model model){
		List<Penumpang> data = penumpangRepo.findAll();
		model.addAttribute("data",data);
		return "Penumpang";
	}

    @GetMapping("/indexPenumpanghome")
	public String getAllDataPenumpangHome (Model model){
		List<Penumpang> data = penumpangRepo.findAll();
		model.addAttribute("data",data);
		return "add/indexPenumpang";
	}

    
    @GetMapping("/loginpenumpang")
    public String loginpenumpang (Model model){
        model.addAttribute("data", new Penumpang());
        return "add/Login";
    }

    @PostMapping("/loginresult")
    public String loginresult(@ModelAttribute("data")Penumpang penumpang, Model model){
        String alamathasil ="/";
        String nikInput = penumpang.getNik();
        nik_in=nikInput;
        List<Penumpang> daftarNikResult = penumpangRepo.findBynik(nikInput);
        if(daftarNikResult.size()==0){
            alamathasil = "add/kenihilan";
        } else {
            String passwordDb=penumpangRepo.findByPassword(nikInput);
            String passwordinput=penumpang.getPassword();
            if(passwordDb.equals(passwordinput)){
                alamathasil="redirect:/bookingkeberangkatan";
            
            }else{
                alamathasil="add/kenihilan";
            }
           
        }
        return alamathasil;
        }

        @GetMapping("/bookingkeberangkatan")
        public String bookingKeberangkatan(Model model){
            model.addAttribute("listKeberangkatan", keberangkatanRepo.findAll());
            model.addAttribute("data",new Booking());
            model.addAttribute("nikPenumpang", nik_in);
            return "add/home";
        }

        @PostMapping("/bookingprocess")
	    public String bookingProcess (@ModelAttribute ("data") Booking booking, Model model){
		    BookingRepo.save(booking); 
		    return "new/showBookingResult";
	    }



        @GetMapping("/ketersediaan")
        public String beranda (Model model){
          
            return "new/showKetersediaan";
        }
        @GetMapping("/cari-keberangkatan")
        public String cariKeberangkatan (Model model){
            model.addAttribute("formData", new Keberangkatan());
            return "new/showCariKeberangkatan";
        }


        @GetMapping("/penumpang/profile")
        public String penumpangProfile (Model model){
            List<Penumpang> penum=penumpangRepo.findBynik(nik_in);
            model.addAttribute("rowData", penum);
            return "new/penumpang/showDetailPenumpang_profile";
        }

        @GetMapping("/penumpang/pemesanan")
        public String penumpangPemesanan (Model model){
            List<Penumpang> penum=penumpangRepo.findBynik(nik_in);
            model.addAttribute("rowData", penum);
            return "new/penumpang/showDetailPenumpang_pemesanan";
        }

        @GetMapping("/penumpang/histori")
        public String penumpangHistori (Model model){
            List<Penumpang> penum=penumpangRepo.findBynik(nik_in);
            model.addAttribute("rowData", penum);
            return "new/penumpang/showDetailPenumpang_histori";
        }

        @GetMapping("/penumpang/pembatalan")
        public String penumpangPembatalan(Model model){
            List<Penumpang> penum=penumpangRepo.findBynik(nik_in);
            model.addAttribute("rowData", penum);
            return "new/penumpang/showDetailPenumpang_pembatalan";
        }

        @GetMapping("/pembatalan")
        public String pembatalan(Model model){
            List<Penumpang> penum=penumpangRepo.findBynik(nik_in);
            model.addAttribute("rowData", penum);
            return "new/showPembatalan";
        }



    

}
    


