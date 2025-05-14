package com.example.tz.controllers;

import com.example.tz.models.TzRequest;
import com.example.tz.services.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TzController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/min")
    public int findMin(@RequestBody TzRequest request) throws Exception {
        return excelService.findNthMin(request.getFilepath(), request.getN());
    }
}
