package com.sangkhim.spring_boot3_quartz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sangkhim.spring_boot3_quartz.model.dto.JobDTO;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaApp {

  public static void main(String[] args) throws JsonProcessingException {
    JobDTO jobDTO = JobDTO.builder().from("from").to("to").subject("subject").body("body").build();

    Map<String, Object> map = Map.of("key1", new ObjectMapper().writeValueAsString(jobDTO));

    log.info(map.toString());

    JobDTO newJobDTO = new ObjectMapper().readValue(map.get("key1").toString(), JobDTO.class);

    log.info(newJobDTO.toString());
  }
}
