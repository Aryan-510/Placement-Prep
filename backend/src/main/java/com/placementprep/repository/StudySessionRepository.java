package com.placementprep.repository;
import com.placementprep.entity.StudySession; import org.springframework.data.jpa.repository.JpaRepository; import org.springframework.data.jpa.repository.Query;
public interface StudySessionRepository extends JpaRepository<StudySession,Long> {
    @Query("select coalesce(sum(session.durationMinutes), 0) from StudySession session")
    Long sumDurationMinutes();
}

