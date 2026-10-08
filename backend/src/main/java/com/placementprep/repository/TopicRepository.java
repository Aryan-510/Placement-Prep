package com.placementprep.repository;
import com.placementprep.entity.PreparationTopic; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface TopicRepository extends JpaRepository<PreparationTopic,Long> { List<PreparationTopic> findByCategoryIgnoreCase(String category); long countByCompletedTrue(); }

