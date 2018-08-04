package com.info.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.info.model.ServiceStaff;
import com.info.model.TeamLeader;
import com.info.service.ServiceStaffServiceIml;

@Controller
public class ServiceSaffController {

	@Autowired
	private ServiceStaffServiceIml serviceStaffServiceIml;

	@GetMapping(value = "/serviceRegistration")
	public ModelAndView serviceRegistration() {
		ModelAndView modelAndView = new ModelAndView();
		ServiceStaff serviceStaff = new ServiceStaff();
        modelAndView.addObject("serviceStaff", serviceStaff);
        modelAndView.setViewName("serviceRegistration");
        return modelAndView;
	}

	@PostMapping(value = "/serviceRegistration")
	public ModelAndView saveServiceStaff(@Valid ServiceStaff serviceStaff, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		if (result.hasErrors() | serviceStaffServiceIml.userExist(serviceStaff) == true) {
			modelAndView.addObject("userexist", "Konto z podanym adresem email już istenieje, podaj inny adress email");
			modelAndView.setViewName("serviceRegistration");
		} else {
			serviceStaffServiceIml.save(serviceStaff);
			modelAndView.addObject("successMessage", "Poprawna rejstracja!");
			modelAndView.addObject("serviceStaff", new TeamLeader());
			modelAndView.setViewName("serviceRegistration");
		}
		return modelAndView;
	}

}
