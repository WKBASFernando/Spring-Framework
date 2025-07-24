package com.ijse.o11back_end.controller;

import com.ijse.o11back_end.dto.JobDTO;
import com.ijse.o11back_end.service.JobService;
import com.ijse.o11back_end.service.impl.JobServiceImpl;
import com.ijse.o11back_end.util.APIResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("api/v1/job")
@RequiredArgsConstructor
@CrossOrigin
public class JobController {
    //constructor injection (Required Args Constructor Annotation eka damme meakata)
    private final JobService jobService;
    @PostMapping("create")
    public ResponseEntity<APIResponse> createJob(@Valid @RequestBody JobDTO jobDTO) {
        log.info("Job Created");
        jobService.saveJob(jobDTO);
        return ResponseEntity.ok(
                new APIResponse(
                        200,
                        "Success",
                        "Job Created"
                )
        );
    }

    @PutMapping("update")
    public ResponseEntity<APIResponse> updateJob(@RequestBody JobDTO jobDTO) {
        jobService.updateJob(jobDTO);
        return ResponseEntity.ok(
                new APIResponse(
                        200,
                        "Success",
                        "Job Updated"
                )
        );
    }

    @PatchMapping("status/{id}")
    public ResponseEntity<APIResponse> changeStatus(@PathVariable String id) {
        jobService.changeJobStatus(id);
        return ResponseEntity.ok(
                new APIResponse(
                        200,
                        "Success",
                        "Status Changed"
                )
        );
    }

    @GetMapping("search/{keyword}")
    public ResponseEntity<APIResponse> searchJob(@PathVariable String keyword) {
        List<JobDTO> jobDTOS = jobService.getAllJobsByKeyword(keyword);
        return ResponseEntity.ok(
                new APIResponse(
                        200,
                        "Success",
                        jobDTOS
                )
        );
    }

    @GetMapping("/paginated")
    public ResponseEntity<APIResponse> getPaginatedJobs(@RequestParam int page, @RequestParam int size) {
        Page<JobDTO> jobDTOPage = jobService.getPaginatedJobs(page, size);
        return ResponseEntity.ok(
                new APIResponse(
                        200,
                        "Success",
                        jobDTOPage
                )
        );
    }

    @GetMapping("getAll")
    public ResponseEntity<APIResponse> getAllJobs(){
        List<JobDTO> jobDTOS = jobService.getAllJobs();
        return ResponseEntity.ok(
                new APIResponse(
                        200,
                        "Success",
                        jobDTOS
                )
        );
    }

}
