package com.websitebus.websitebus.controller;



import java.util.List;




import com.websitebus.websitebus.model.Penumpang;
import com.websitebus.websitebus.repository.PenumpangRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
public class LoginController {



    @Autowired
	PenumpangRepository penumpangRepo;

  
 
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

    // @GetMapping("/loginPenumpang")
    // public String loginpenumpang (Model nik){
    //     nik.addAttribute("data", new Penumpang());
    //     return "add/Login";
    // }


    // @GetMapping("/caribyNik")
    // public String cariNik(String nik){
    //     List <Penumpang> hasil = penumpangRepo.findBynik(nik);
    //     if (hasil.size()==0){
    //         return "add/kenihilan";
    //     } else {
    //         return "redirect:/indexPenumpang";
    //     }
    // }

    
    @GetMapping("/loginpenumpang")
    public String loginpenumpang (Model model){
        model.addAttribute("data", new Penumpang());
        return "add/Login";
    }

    @PostMapping("/loginresult")
    public String loginresult(@ModelAttribute("data")Penumpang penumpang, Model model){
        String alamathasil ="/";
        String nikInput = penumpang.getNik();
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

            // model.addAttribute("data", daftarNikResult.get(0));
            // alamathasil = "add/detailpenumpang";
            
        }
        return alamathasil;
        }

        @GetMapping("/bookingkeberangkatan")
        public String bookingKeberangkatan(Model model){
            
            return "add/home";
        }

        @GetMapping("/ketersediaan")
        public String beranda (Model model){
          
            return "new/showKetersediaan";
        }
        @GetMapping("/cari-keberangkatan")
        public String cariKeberangkatan (Model model){
          
            return "new/showCariKeberangkatan";
        }

        @GetMapping("/penumpang")
        public String penumpang (Model model){
          
            return "new/showDetailPenumpang";
        }

        @GetMapping("/pembatalan")
        public String pembatalan(Model model){
          
            return "new/showPembatalan";
        }



    

}
    


