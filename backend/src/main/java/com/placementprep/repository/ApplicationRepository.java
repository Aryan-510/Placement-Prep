package com.placementprep.repository;
import com.placementprep.entity.*; import org.springframework.data.jpa.repository.JpaRepository;
public interface ApplicationRepository extends JpaRepository<JobApplication,Long> { long countByStatus(ApplicationStatus status); }

