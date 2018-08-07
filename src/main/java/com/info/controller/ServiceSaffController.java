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

	@GetMapping(value = "/home")
	public String serviceHome(Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("serviceStaff", serviceStaffRepository.findByEmail(authentication.getName()));
		return "servicepage";
	}

}
