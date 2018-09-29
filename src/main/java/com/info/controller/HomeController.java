package com.info.controller;

import com.info.model.Admin;
import com.info.model.ServiceStaff;
import com.info.model.TeamLeader;
import com.info.repository.AdminRepository;
import com.info.repository.ServiceStaffRepository;
import com.info.repository.TeamLeaderRepository;
import com.info.service.CommonService;
import com.info.service.ServiceStaffServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class HomeController {

    @Autowired
    private ServiceStaffServiceIml serviceStaffServiceIml;

    @Autowired
    private ServiceStaffRepository serviceStaffRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    TeamLeaderRepository teamLeaderRepository;

    @Autowired
    private AdminRepository adminRepository;


    @RequestMapping(value = "/default")
    public ModelAndView deafultAfterLogin(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminRepository.findAdminByEmail(authentication.getName());
        TeamLeader teamLeader =  teamLeaderRepository.findByEmail(authentication.getName());
        ServiceStaff serviceStaff =  serviceStaffRepository.findByEmail(authentication.getName());

        if(admin != null){
            return new ModelAndView("redirect:" + "/admin");
        } else if(teamLeader != null && teamLeader.getRole().equals("TEAM_LEADER")) {
            return new ModelAndView("redirect:" + "/teamleader/getall");
        }else if(serviceStaff != null && serviceStaff.getRole().equals("SERVICE_STAFF")){
            return new ModelAndView("redirect:" + "/service/home");
        }else {
            modelAndView.setViewName("login");
            return modelAndView;
        }

    }

    @GetMapping(value = "/access-denied")
    public ModelAndView access(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("accesdenimed");
        return modelAndView;
    }


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

        if(commonService.checkEmail(serviceStaff.getEmail())){
            modelAndView.addObject("userexist", "Konto z podanym adresem email już istenieje, podaj inny adress email");
            modelAndView.setViewName("serviceRegistration");
            return modelAndView;
        }
        if (result.hasErrors()) {
            modelAndView.setViewName("serviceRegistration");
        } else {
            serviceStaffServiceIml.save(serviceStaff);
            modelAndView.addObject("successMessage", "Poprawna rejstracja!");
            modelAndView.addObject("serviceStaff", new ServiceStaff());
            modelAndView.setViewName("serviceRegistration");
        }
        return modelAndView;
    }

}
