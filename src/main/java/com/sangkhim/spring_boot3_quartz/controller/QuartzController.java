package com.sangkhim.spring_boot3_quartz.controller;

import com.sangkhim.spring_boot3_quartz.model.dto.JobDTO;
import com.sangkhim.spring_boot3_quartz.schedule.JobScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class QuartzController {

  private final JobScheduler jobScheduler;

  @GetMapping("/v1/quartz")
  public ResponseEntity<String> quartz() {
    JobDTO jobDTO = new JobDTO();
    jobDTO.setFrom("info@borey.app");
    jobDTO.setTo("sangkhim@gmail.com");
    jobDTO.setSubject("Hello");
    jobDTO.setBody("Hello from Spring Boot");
    jobScheduler.execute(jobDTO);
    return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
  }
}
