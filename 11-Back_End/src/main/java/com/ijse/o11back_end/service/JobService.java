package com.ijse.o11back_end.service;

import com.ijse.o11back_end.dto.JobDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {
    void saveJob(JobDTO jobDTO);
    void updateJob(JobDTO jobDTO);
    List<JobDTO> getAllJobs();
    void changeJobStatus(String id);
    List<JobDTO> getAllJobsByKeyword(String status);
    Page<JobDTO> getPaginatedJobs(int page, int size);

}
