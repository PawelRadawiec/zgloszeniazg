package com.info.controller;

import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import com.info.service.CommonService;
import com.info.service.TeamLeaderService;
import com.info.service.TeamMemberService;
import com.info.service.XlsxReport;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class TeamLeaderController {

    @Autowired
    private TeamLeaderService teamLeaderService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private XlsxReport xlsxReport;

    @Autowired
    private CommonService commonService;


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        TeamLeader teamLeader = new TeamLeader();
        modelAndView.addObject("teamLeader", teamLeader);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid TeamLeader teamLeader, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if(commonService.checkEmail(teamLeader.getEmail())){
            modelAndView.addObject("userexist", "Konto z podanym adresem email już istenieje, podaj inny adress email");
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("registration");
        }else{
            teamLeaderService.save(teamLeader);
            modelAndView.addObject("successMessage", "Poprawna rejstracja!");
            modelAndView.addObject("teamLeader", new TeamLeader());
            modelAndView.setViewName("registration");
        }
        return modelAndView;

    }

    @GetMapping(value = "/teamleader/getall")
    public ModelAndView teamLeaderPage(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("teamLeader", teamLeaderService.findByEmail(authentication.getName()));
        modelAndView.setViewName("newteamleader");
        return modelAndView;
    }

    @GetMapping(value = "/teamleader/registerTeamMember")
    public ModelAndView registerTeamMember(){
        ModelAndView modelAndView = new ModelAndView();
        TeamMember teamMember = new TeamMember();
        modelAndView.addObject("teamMember", teamMember);
        modelAndView.setViewName("memberregistration");
        return modelAndView;
    }

    @PostMapping(value = "/teamleader/registerTeamMember")
    public ModelAndView createTeamMember(@Valid TeamMember teamMember, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("memberregistration");
        }else{
            teamMemberService.save(teamMember);
            modelAndView.addObject("successMessage", "Członek drużyny został poprawnie dodany");
            modelAndView.addObject("teamMember", new TeamMember());
            modelAndView.setViewName("memberregistration");
        }
        return modelAndView;
    }

    @GetMapping(value = "/teamleader/memberlist")
    public ModelAndView getListOfMembers(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("memberlist", teamMemberService.getAllMembers(auth.getName()));
        modelAndView.setViewName("teamleaderpage");
        return modelAndView;
    }

    @GetMapping(value = "/teamleader/edit/{id}")
    public ModelAndView changeTeamMember(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        TeamMember teamMember = this.teamMemberService.findById(id);
        modelAndView.addObject("path", "teamleader");
        modelAndView.addObject("teamMember", teamMember);
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @PostMapping(value = "/teamleader/edit/{id}")
    public ModelAndView editTeamMember(@Valid TeamMember teamMember,  @PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teamMember", teamMember);
        modelAndView.addObject("path", "teamleader");
        teamMemberService.editTeamMember(teamMember, id);
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @GetMapping(value = "/teamleader/delete/{id}")
    public ModelAndView deleteMember(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        teamMemberService.deleteById(id);
        modelAndView.setViewName("redirect:/teamleader/getall");
        return modelAndView;
    }

    @DeleteMapping  (value = "/teamleader/delete/{id}")
    public ModelAndView deleteMemberById(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        teamMemberService.deleteById(id);
        modelAndView.setViewName("redirect:/teamleader/getall");
        return modelAndView;
    }


    @RequestMapping(value = "/teamleader/getFile", method =  RequestMethod.GET)
    public void downloadSPreddSheet(HttpServletResponse response) throws Exception{
        XSSFWorkbook wb = null;
        try {
            wb = this.xlsxReport.generateXlsx();

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment; filename=team_member.xlsx");
            wb.write(response.getOutputStream());
        } catch (IOException ioe) {
            throw new RuntimeException("Error writing spreadsheet to output stream");
        } finally {
            if (wb != null) {
                wb.close();
            }
        }
    }

}
