package com.websitebus.websitebus.controller;

import java.util.List;

import com.websitebus.websitebus.model.resume;
import com.websitebus.websitebus.model.skills;
import com.websitebus.websitebus.repository.ResumeRepository;
import com.websitebus.websitebus.repository.skillsrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class doracontroller {

	@Autowired
	skillsrepo skillsRepo;

	@Autowired
	ResumeRepository resumeRepo;

    

	@GetMapping("/")
	public String getAllskills (Model model){
		List<skills> data = skillsRepo.findAll();
		List<skills> skill = skillsRepo.findAll();
		List<resume> employment = resumeRepo.getEmployment();
		List<resume> education = resumeRepo.getEducation();
		model.addAttribute("data",data);
		model.addAttribute("skill", skill);
		model.addAttribute("employment", employment);
		model.addAttribute("education", education);
		return "dora/index";
	}

	// @GetMapping("/")
	// public String getIndexrsume(Model model){
	// 	List<skills> skill = skillsRepo.findAll();
	// 	List<resume> employment = resumeRepo.getEmployment();
	// 	model.addAttribute("data", skill);
	// 	model.addAttribute("data", employment);
	// 	return "dora/index";
	// } 


}
