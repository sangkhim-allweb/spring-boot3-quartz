package com.sangkhim.spring_boot3_quartz.controller;

import com.sangkhim.spring_boot3_quartz.model.dto.PostDTO;
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
    PostDTO postDTO = new PostDTO();
    jobScheduler.execute(postDTO);
    return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
  }
}
