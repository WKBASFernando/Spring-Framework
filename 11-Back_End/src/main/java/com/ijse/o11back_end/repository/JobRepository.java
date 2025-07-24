package com.ijse.o11back_end.repository;

import com.ijse.o11back_end.entity.Job;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE Job SET status='Deactivate' WHERE id=?", nativeQuery = true)
    void updateJobStatus(String id);
    List<Job> findJobByJobTitleContainingIgnoreCase(String keyword);
}
