package com.teacherassistant.controller;

import com.teacherassistant.dto.WorksheetRequest;
import com.teacherassistant.dto.WorksheetResponse;
import com.teacherassistant.model.Worksheet;
import com.teacherassistant.service.WorksheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/worksheet")
@CrossOrigin("*")
public class WorksheetController {

    @Autowired
    private WorksheetService worksheetService;

    @PostMapping("/generate")
    public WorksheetResponse generateWorksheet(@RequestBody WorksheetRequest request){

        return worksheetService.generateWorksheet(request);
    }

    @GetMapping("/user/{userId}")
    public List<WorksheetResponse> getWorksheets(@PathVariable Long userId){

        return worksheetService.getWorksheetsByUser(userId);

    }

    @GetMapping("/{id}")
    public WorksheetResponse getWorksheet(@PathVariable Long id){

        return worksheetService.getWorksheetById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorksheet(@PathVariable Long id) {

        worksheetService.deleteWorksheet(id);
        return ResponseEntity.ok("Worksheet deleted successfully");
    }
}