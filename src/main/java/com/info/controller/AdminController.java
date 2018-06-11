package com.info.controller;

import com.info.service.AdminServiceImpl;
import com.info.service.XlsxReport;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private XlsxReport xlsxReport;

    @GetMapping()
    public ModelAndView getAllTeamLeaders(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teamLeaderList", adminService.getAllTeamLeader());
        modelAndView.addObject("adminName", adminService.getAdminFromSession().getFirstName());
        modelAndView.setViewName("adminteamleader");
        return modelAndView;
    }

    @GetMapping(value = "/teammemberlist")
    public ModelAndView getAllTeamMember(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teamMemberlist", adminService.getAllTeamMember());
        modelAndView.addObject("adminName", adminService.getAdminFromSession().getFirstName());
        modelAndView.setViewName("adminteamleader");
        return modelAndView;
    }

    @GetMapping(value = "/details/{id}/{email}")
    public ModelAndView getDetails(@PathVariable("id") int id,
                                   @PathVariable("email")String teamLeaderMail){
        ModelAndView modelAndView =  new ModelAndView();
        modelAndView.addObject("teamLeader", adminService.getDetails(id));
        modelAndView.addObject("memberlist", adminService.getTeamMembersByLeader(teamLeaderMail));
        modelAndView.setViewName("details");
        return modelAndView;
    }

    @RequestMapping(value = "/getFile", method =  RequestMethod.GET)
    public void allTeamMemberReport(HttpServletResponse response) throws Exception {
        XSSFWorkbook wb = null;
        try {
            wb = this.xlsxReport.generateXlsx();

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment; filename=czlonkowie_druzyn.xlsx");
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
