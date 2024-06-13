package com.rko.rxlocate.helper;

import com.rko.rxlocate.dto.DistrictPrescriptionProjection;
import com.rko.rxlocate.dto.DivisionPrescriptionProjection;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {

    public static String[] DIVISION_HEADERS = {
            "Drug Name",
            "Division Name",
            "Prescription Count"
    };

    public static String[] DISTRICT_HEADERS = {
            "District Name",
            "Prescription Count"
    };

    public static String SHEET_NAME = "drug_overview";

    public static ByteArrayInputStream divisionDataToExcel(List<DivisionPrescriptionProjection> list) {

        try{
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Sheet sheet = workbook.createSheet(SHEET_NAME);

            Row row = sheet.createRow(0);
            for(int i = 0; i < DIVISION_HEADERS.length; i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(DIVISION_HEADERS[i]);
            }

            int rowIndex = 1;
            for(DivisionPrescriptionProjection dp : list){
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue(dp.getDrugName());
                dataRow.createCell(1).setCellValue(dp.getDivisionName());
                dataRow.createCell(2).setCellValue(dp.getPrescriptionCount());
            }

            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ByteArrayInputStream districtDataToExcel(List<DistrictPrescriptionProjection> list) {

        try{
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Sheet sheet = workbook.createSheet(SHEET_NAME);

            Row row = sheet.createRow(0);
            for(int i = 0; i < DISTRICT_HEADERS.length; i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(DISTRICT_HEADERS[i]);
            }

            int rowIndex = 1;
            for(DistrictPrescriptionProjection dp : list){
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue(dp.getDistrictName());
                dataRow.createCell(1).setCellValue(dp.getPrescriptionCount());
            }

            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
