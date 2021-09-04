package com.example.prhack.converters;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;

import static java.sql.Types.BOOLEAN;
import static java.sql.Types.NUMERIC;
import static org.apache.poi.ss.usermodel.DataValidationConstraint.ValidationType.FORMULA;
import static org.apache.tomcat.util.bcel.classfile.ElementValue.STRING;

// xsls to text
public class TextFileWriter implements Runnable{

    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();
        try {

            String excelFilePath = "/home/riki/PromrelationBankHack/dataset/Спецификация и пример запроса API/Коды номенклатур документов.xlsx";
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            File file = new File("/home/riki/PromrelationBankHack/dataset/Спецификация и пример запроса API/Коды номенклатур документов.xlsx");
            Iterator<Row> iterator = firstSheet.iterator();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue());
                            sb.append(cell.getStringCellValue() + ", ");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue());
                            sb.append(String.valueOf(cell.getBooleanCellValue() + ", "));
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue());
                            sb.append(String.valueOf(cell.getNumericCellValue() + ", "));
                            break;
                        case FORMULA:
                            System.out.print(cell.getCellFormula());
                            sb.append(String.valueOf(cell.getCellFormula() + ", "));
                            break;
                        default :
                            //System.out.print(cell.getStringCellValue());
                            //sb.append(cell.getStringCellValue() + ", ");
                    }
                    System.out.print(" - ");
                }
                System.out.println();
                sb.append("\n");
            }
            workbook.cloneSheet(0);
            inputStream.close();
            Path path = Paths.get("/home/riki/PromrelationBankHack/writedatasamp.txt");
            Files.write(path, Arrays.asList(sb.toString()));

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        run();
    }
}