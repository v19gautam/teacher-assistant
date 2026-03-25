package com.teacherassistant.dto;

import lombok.Data;

import java.util.Map;

@Data
public class WorksheetResponse {

    private Long id;

    private String classLevel;

    private String subject;

    private String topic;

    private Map<String, Object> questions;

    private String shareId;
}