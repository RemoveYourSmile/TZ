package com.example.tz.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {
    public int findNthMin(String path, int n) throws Exception {
        FileInputStream fis = new FileInputStream(new File(path));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        List<Integer> numbers = new ArrayList<>();
        for (Row row : sheet) {
            Cell cell = row.getCell(0); // Первый столбец
            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                numbers.add((int) cell.getNumericCellValue());
            }
        }

        workbook.close();
        fis.close();

        if (n < 1 || n > numbers.size()) {
            throw new IllegalArgumentException("n вне диапазона");
        }

        int[] arr = numbers.stream().mapToInt(i -> i).toArray();
        return QuickSelect.findNthSmallest(arr, n);
    }
}
