package com.info.controller;

import javax.validation.Valid;

import com.info.model.StaffUpdate;
import com.info.repository.ServiceStaffRepository;
import com.info.service.impl.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.info.model.ServiceStaff;
import com.info.service.impl.ServiceStaffServiceIml;

@Controller
@RequestMapping(value = "/service")
public class ServiceSaffController {

	@Autowired
	private ServiceStaffServiceIml serviceStaffServiceIml;

	@Autowired
	private ServiceStaffRepository serviceStaffRepository;

	@Autowired
	private CommonService commonService;

	@GetMapping(value = "/home")
	public String serviceHome(Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("serviceStaff", serviceStaffRepository.findByEmail(authentication.getName()));
		return "servicepage";
	}

	@GetMapping(value = "/edit")
	public String edit(Model model){
		model.addAttribute("serviceStaff", serviceStaffServiceIml.setStaffUpdate());
		return "service-edit";
	}

	@PostMapping(value = "/edit")
	public String editAccount(@Valid StaffUpdate serviceStaff, BindingResult result, Model model){
		if (result.hasErrors()) {
			return "service-edit";
		} else {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			ServiceStaff fromAuth = serviceStaffRepository.findByEmail(authentication.getName());
			serviceStaffServiceIml.editAccount(serviceStaff, fromAuth.getId());
			model.addAttribute("successMessage", "Konto zostało edytowane!");
			model.addAttribute("serviceStaff", new ServiceStaff());
		}
		return "service-edit";
	}

}
