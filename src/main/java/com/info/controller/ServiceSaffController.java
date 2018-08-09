package com.info.controller;

import javax.validation.Valid;

import com.info.repository.ServiceStaffRepository;
import com.info.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.info.model.ServiceStaff;
import com.info.model.TeamLeader;
import com.info.service.ServiceStaffServiceIml;

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

	@GetMapping(value = "/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id){
		ServiceStaff serviceStaff = serviceStaffServiceIml.getStaffById(id);
		model.addAttribute("path", "service");
		model.addAttribute("serviceStaff", serviceStaff);
		return "serviceRegistration";
	}

	@PostMapping(value = "/edit")
	public String editAccount(@Valid ServiceStaff serviceStaff, BindingResult result, Model model){
		model.addAttribute("path", "service");
		if(commonService.checkEmail(serviceStaff.getEmail())){
			model.addAttribute("userexist", "Konto z podanym adresem email już istenieje, podaj inny adress email");
			return "serviceRegistration";
		}
		if (result.hasErrors()) {
			return "serviceRegistration";
		} else {
			serviceStaffServiceIml.updateAccount(serviceStaff);
			model.addAttribute("successMessage", "Konto zostało edytowane!");
			model.addAttribute("serviceStaff", new ServiceStaff());
		}
		return "serviceRegistration";
	}

}
