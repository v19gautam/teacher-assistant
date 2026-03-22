package com.teacherassistant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teacherassistant.dto.WorksheetRequest;
import com.teacherassistant.dto.WorksheetResponse;
import com.teacherassistant.model.Worksheet;
import com.teacherassistant.service.WorksheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/worksheet")
@CrossOrigin("*")
public class WorksheetController {

    @Autowired
    private WorksheetService worksheetService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateWorksheet(@RequestBody WorksheetRequest request){

        return ResponseEntity.ok(worksheetService.generateWorksheet(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getWorksheets(@PathVariable Long userId){

        return ResponseEntity.ok(worksheetService.getWorksheetsByUser(userId));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorksheet(@PathVariable Long id){

        return ResponseEntity.ok(worksheetService.getWorksheetById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorksheet(@PathVariable Long id) {

        Map<String, String> response = worksheetService.deleteWorksheet(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {

        byte[] pdf = worksheetService.generatePdf(id);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=worksheet.pdf")
                .header("Content-Type", "application/pdf")
                .body(pdf);
    }
}