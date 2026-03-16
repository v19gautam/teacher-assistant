package com.teacherassistant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Worksheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String classLevel;

    private String subject;

    private String topic;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String questionsJson;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}