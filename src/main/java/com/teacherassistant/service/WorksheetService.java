package com.teacherassistant.service;

import com.teacherassistant.dto.WorksheetRequest;
import com.teacherassistant.dto.WorksheetResponse;
import com.teacherassistant.exception.ResourceNotFoundException;
import com.teacherassistant.model.User;
import com.teacherassistant.model.Worksheet;
import com.teacherassistant.repository.UserRepository;
import com.teacherassistant.repository.WorksheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorksheetService {

    @Autowired
    private WorksheetRepository worksheetRepository;

    @Autowired
    private UserRepository userRepository;

    public WorksheetResponse generateWorksheet(WorksheetRequest request){

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + request.getUserId()));

        Worksheet worksheet = new Worksheet();
        worksheet.setUser(user);
        worksheet.setClassLevel(request.getClassLevel());
        worksheet.setSubject(request.getSubject());
        worksheet.setTopic(request.getTopic());

        String questions = """
        {
            "questions":[
                {"question":"Sample Question 1","answer":"Answer 1"},
                {"question":"Sample Question 2","answer":"Answer 2"}
            ]
        }
        """;

        worksheet.setQuestionsJson(questions);

        Worksheet saved = worksheetRepository.save(worksheet);

        return mapToResponse(saved);
    }

    private WorksheetResponse mapToResponse(Worksheet worksheet){

        WorksheetResponse response = new WorksheetResponse();

        response.setId(worksheet.getId());
        response.setClassLevel(worksheet.getClassLevel());
        response.setSubject(worksheet.getSubject());
        response.setTopic(worksheet.getTopic());
        response.setQuestionsJson(worksheet.getQuestionsJson());

        return response;
    }

    public List<WorksheetResponse> getWorksheetsByUser(Long userId){

        List<Worksheet> worksheets = worksheetRepository.findByUserId(userId);
        return worksheets.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public WorksheetResponse getWorksheetById(Long id){

        return worksheetRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(()->new ResourceNotFoundException("Worksheet not found with id: " + id));
    }

    public Map<String, String> deleteWorksheet(Long id) {

        Map<String, String> responseMap = new HashMap<>();
        Worksheet worksheet = worksheetRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Worksheet not found with id: " + id));

        worksheetRepository.delete(worksheet);
        responseMap.put("Message", "Worksheet deleted successfully");
        return responseMap;
    }

}