package com.teacherassistant.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;

}
