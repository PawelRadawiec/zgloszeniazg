package com.info.service;

import com.info.model.TeamMember;
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

        List<TeamMember> teamMemberList = teamMemberRepository.getAllMembers(auth.getName());

        int rowNum = 1;
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

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream("teammember.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        return workbook;

    }


}
