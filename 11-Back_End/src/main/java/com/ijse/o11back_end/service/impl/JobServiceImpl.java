package com.ijse.o11back_end.service.impl;

import com.ijse.o11back_end.dto.JobDTO;
import com.ijse.o11back_end.entity.Job;
import com.ijse.o11back_end.exceptions.ResourceNotFoundException;
import com.ijse.o11back_end.exceptions.ValidationException;
import com.ijse.o11back_end.repository.JobRepository;
import com.ijse.o11back_end.service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
    //logic to save job
    public void saveJob(JobDTO jobDTO) {
        if (jobDTO.getJobTitle()==null){
            throw new ValidationException("Job title is required");
        }
        jobRepository.save(modelMapper.map(jobDTO, Job.class));
    }

    @Override
    public void updateJob(JobDTO jobDTO) {
        jobRepository.save(modelMapper.map(jobDTO, Job.class));
    }

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        if (jobs.isEmpty()){
            throw new ResourceNotFoundException("No Job Found");
        }
        return modelMapper.map(jobs, new TypeToken<List<JobDTO>>() {}.getType());
    }

    @Override
    public void changeJobStatus(String id) {
        jobRepository.updateJobStatus(id);
    }

    @Override
    public List<JobDTO> getAllJobsByKeyword(String keyword) {
        List<Job> list = jobRepository.findJobByJobTitleContainingIgnoreCase(keyword);
        return modelMapper.map(list, new TypeToken<List<JobDTO>>() {}.getType());
    }

    @Override
    public Page<JobDTO> getPaginatedJobs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Job> jobPage = jobRepository.findAll(pageable);
        return jobPage.map(this::convertToDTO); // Convert Job to JobDTO
    }

    private JobDTO convertToDTO(Job job) {
        return new ModelMapper().map(job, JobDTO.class);
    }

}
