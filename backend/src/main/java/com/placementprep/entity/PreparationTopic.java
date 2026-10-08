package com.placementprep.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity @Table(name="preparation_topics")
public class PreparationTopic {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank @Column(nullable=false) private String name;
    @NotBlank @Column(nullable=false) private String category;
    @NotBlank @Column(nullable=false) private String difficulty="MEDIUM";
    @Column(nullable=false) private boolean completed;
    @Column(length=1000) private String notes;
    public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
    public String getCategory(){return category;} public void setCategory(String v){category=v;} public String getDifficulty(){return difficulty;} public void setDifficulty(String v){difficulty=v;}
    public boolean isCompleted(){return completed;} public void setCompleted(boolean v){completed=v;} public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
}

