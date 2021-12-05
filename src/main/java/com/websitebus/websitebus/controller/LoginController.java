package com.websitebus.websitebus.controller;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;

import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.websitebus.websitebus.model.Booking;
import com.websitebus.websitebus.model.Keberangkatan;
import com.websitebus.websitebus.model.Penumpang;
import com.websitebus.websitebus.repository.BookingRepository;
import com.websitebus.websitebus.repository.KeberangkatanRepository;
import com.websitebus.websitebus.repository.PenumpangRepository;
import com.websitebus.websitebus.model.Fk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    public String nik_in;
    
    public final String UPLOAD_DIR = "websitebus/src/main/resources/static/assets/images/";

    @Autowired
	PenumpangRepository penumpangRepo;

    @Autowired
    KeberangkatanRepository keberangkatanRepo;

    @Autowired
    BookingRepository BookingRepo;


    
    @GetMapping("/home")
    public String MainHome (Model model){
        return "new/penumpang/MainHome";
    }

    @GetMapping("/about")
    public String about (Model model){
        return "new/about";
    }

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
                alamathasil="redirect:/home";
            
            }else{
                alamathasil="add/kenihilan";
            }
           
        }
        return alamathasil;
        }

        // penumpang
        @GetMapping("/addPenumpang")
        public String addpenumpang (Model model ) {
            model.addAttribute("data",new Penumpang());
            return "add/addPenumpang";
        }
    
        @PostMapping("/save")
        public String saveDatapenumpang (@ModelAttribute("data") Penumpang penumpang) {
            penumpangRepo.save(penumpang);
            return "redirect:/loginpenumpang "; //meminta browser untuk request url lain
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
            List<Fk> data = keberangkatanRepo.findAllindex();
            model.addAttribute("dataavailable", data);
            return "new/showKetersediaan";
        }




        @GetMapping("/cari-keberangkatan")
        public String cariKeberangkatan (Model model){
            model.addAttribute("formData", new Keberangkatan());
            model.addAttribute("listKeberangkatan", keberangkatanRepo.findAll());
            return "new/showCariKeberangkatan";
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





        @GetMapping("/penumpang/profile")
        public String penumpangProfile (Model model){
            List<Penumpang> penum=penumpangRepo.findBynik(nik_in);
            model.addAttribute("rowData", penum);
            return "new/penumpang/showDetailPenumpang_profile";
        }

        @GetMapping("/penumpang/pemesanan")
        public String penumpangPemesanan (Model model){
            List<Penumpang> penum=penumpangRepo.findBynik(nik_in);
            List<Fk> listbooking=keberangkatanRepo.findByNik(nik_in);
            model.addAttribute("rowData", penum);
            model.addAttribute("lsBooking", listbooking);
            model.addAttribute("delete", new Booking());
            return "new/penumpang/showDetailPenumpang_pemesanan";
        }

        @Transactional
        @GetMapping("/penumpang/pemesanan/cancel/{delete}")
        public String deletePemesanan(@PathVariable ("delete") long id){
            BookingRepo.deleteById(id);
            return"redirect:/penumpang/pemesanan";
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

        @GetMapping("/penumpang/profile/upload")
        public String penumpangProfileUpload (Model model){
            List<Penumpang> penum=penumpangRepo.findBynik(nik_in);
            model.addAttribute("rowData", penum);
            return "new/penumpang/showFormUploadPic";
        }

        @PostMapping("/upload")
        public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
            
            // check if file is empty
            if (file.isEmpty()) {
                return "redirect:/showKenihilanKeberangkatan";
            }
           
            // normalize the file path
  
            List<Penumpang> obj= penumpangRepo.findBynik(nik_in);
            String fileName=obj.get(0).getNama()+".jpg";
    
            // save the file on the local file system
            try {
                Path path = Paths.get(UPLOAD_DIR + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // return success response
            return "redirect:/penumpang/profile";
            }


    

}
    


