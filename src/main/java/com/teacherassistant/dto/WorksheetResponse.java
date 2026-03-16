package com.teacherassistant.dto;

import lombok.Data;

@Data
public class WorksheetResponse {

    private Long id;

    private String classLevel;

    private String subject;

    private String topic;

    private String questionsJson;

}