package com.placementprep.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "applications")
public class JobApplication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank @Column(nullable = false) private String companyName;
    @NotBlank @Column(nullable = false) private String jobRole;
    private String location;
    @NotNull @Column(nullable = false) private LocalDate applicationDate = LocalDate.now();
    @NotNull @Enumerated(EnumType.STRING) @Column(nullable = false) private ApplicationStatus status = ApplicationStatus.APPLIED;
    @NotNull @Enumerated(EnumType.STRING) @Column(nullable = false) private JobType jobType = JobType.FULL_TIME;
    @Column(length = 1000) private String notes;
    public Long getId(){return id;} public String getCompanyName(){return companyName;} public void setCompanyName(String v){companyName=v;}
    public String getJobRole(){return jobRole;} public void setJobRole(String v){jobRole=v;} public String getLocation(){return location;} public void setLocation(String v){location=v;}
    public LocalDate getApplicationDate(){return applicationDate;} public void setApplicationDate(LocalDate v){applicationDate=v;}
    public ApplicationStatus getStatus(){return status;} public void setStatus(ApplicationStatus v){status=v;}
    public JobType getJobType(){return jobType;} public void setJobType(JobType v){jobType=v;} public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
}

