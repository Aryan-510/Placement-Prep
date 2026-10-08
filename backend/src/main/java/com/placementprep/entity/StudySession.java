package com.placementprep.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity @Table(name="study_sessions")
public class StudySession {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotNull @Column(nullable=false) private LocalDate date=LocalDate.now();
    @NotBlank @Column(nullable=false) private String topic;
    @Min(1) @Column(nullable=false) private int durationMinutes;
    @Column(length=1000) private String notes;
    public Long getId(){return id;} public LocalDate getDate(){return date;} public void setDate(LocalDate v){date=v;} public String getTopic(){return topic;} public void setTopic(String v){topic=v;}
    public int getDurationMinutes(){return durationMinutes;} public void setDurationMinutes(int v){durationMinutes=v;} public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
}

