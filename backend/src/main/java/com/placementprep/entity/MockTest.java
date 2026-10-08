package com.placementprep.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity @Table(name="mock_tests")
public class MockTest {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank @Column(nullable=false) private String testName;
    @NotBlank @Column(nullable=false) private String subject;
    @NotNull @Column(nullable=false) private LocalDate date=LocalDate.now();
    @Min(0) @Column(nullable=false) private double score;
    @DecimalMin("0.01") @Column(nullable=false) private double totalMarks;
    @AssertTrue(message="score must not exceed total marks") public boolean isScoreWithinTotal(){return score<=totalMarks;}
    @Transient public double getPercentage(){return totalMarks<=0?0:score/totalMarks*100;}
    public Long getId(){return id;} public String getTestName(){return testName;} public void setTestName(String v){testName=v;} public String getSubject(){return subject;} public void setSubject(String v){subject=v;}
    public LocalDate getDate(){return date;} public void setDate(LocalDate v){date=v;} public double getScore(){return score;} public void setScore(double v){score=v;} public double getTotalMarks(){return totalMarks;} public void setTotalMarks(double v){totalMarks=v;}
}

