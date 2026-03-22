package com.teacherassistant.dto;

import lombok.Data;

@Data
public class WorksheetRequest {

    private Long userId;
    private String classLevel;
    private String subject;
    private String topic;
    private String difficulty;
    private int count;

}