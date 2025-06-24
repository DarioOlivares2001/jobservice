package com.matchwork.jobservice.repository;

import com.matchwork.jobservice.model.Job;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(value = "SELECT * FROM trabajos ORDER BY DBMS_RANDOM.VALUE FETCH FIRST :limit ROWS ONLY", nativeQuery = true)
     List<Job> findRandomJobs(@Param("limit") int limit);

    List<Job> findByCreatorIdOrderByFechaPublicacionDesc(Long creatorId);
}
