package com.info.service.impl;

import com.info.model.Admin;
import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import com.info.repository.AdminRepository;
import com.info.repository.AdminTeamMemberRepository;
import com.info.repository.TeamLeaderRepository;
import com.info.repository.TeamMemberRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.List;

@Service
public class XlsxReport {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private AdminTeamMemberRepository adminTeamMemberRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TeamLeaderRepository teamLeaderRepository;

    private static String[] columns = { "Imię", "Naziwsko", "Email",
            "Miasto", "Ulica", "Numer telefonu", "Dieta", "Drużyna" };

    public XSSFWorkbook generateXlsx() throws Exception{
        XSSFWorkbook workbook = new  XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Teamledaers");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        XSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        XSSFRow headerRow = sheet.createRow(0);

        for(int i = 0; i<columns.length; i++){
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(searchUserRole(auth).equals("TEAM_LEADER")){
            teamMemberLoad(sheet);
        }
        if(searchUserRole(auth).equals("ADMIN")){
            allTeamMember(sheet);
        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream("teammember.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        return workbook;
    }


    private  String searchUserRole(Authentication auth){
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        Object user = teamLeaderRepository.findByEmail(auth1.getName());
        if(user instanceof TeamLeader){
            return "TEAM_LEADER";
        }
        Object user2  = adminRepository.findAdminByEmail(auth1.getName());
        if(user2 instanceof Admin ){
            return "ADMIN";
        }
        return null;
    }

    private void allTeamMember(XSSFSheet sheet){
        int rowNum = 1;
        List<TeamMember> allTeamMember = adminTeamMemberRepository.getAll();
        for(TeamMember teamMember: allTeamMember){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(teamMember.getFirstName());
            row.createCell(1).setCellValue(teamMember.getLastName());
            row.createCell(2).setCellValue(teamMember.getHomeCity());
            row.createCell(3).setCellValue(teamMember.getStreet());
            row.createCell(4).setCellValue(teamMember.getPhoneNumber());
            row.createCell(5).setCellValue(teamMember.getMealCategory());
            row.createCell(6).setCellValue(teamMember.getTeamName());
            row.createCell(7).setCellValue(teamMember.getTeamLeaderEmail());
        }
    }

    private void teamMemberLoad(XSSFSheet sheet){
        int rowNum = 1;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<TeamMember> teamMemberList = teamMemberRepository.getAllMembers(auth.getName());
        for(TeamMember teamMember: teamMemberList){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(teamMember.getFirstName());
            row.createCell(1).setCellValue(teamMember.getLastName());
            row.createCell(2).setCellValue(teamMember.getHomeCity());
            row.createCell(3).setCellValue(teamMember.getStreet());
            row.createCell(4).setCellValue(teamMember.getPhoneNumber());
            row.createCell(5).setCellValue(teamMember.getMealCategory());
            row.createCell(6).setCellValue(teamMember.getTeamName());
        }
    }


}
