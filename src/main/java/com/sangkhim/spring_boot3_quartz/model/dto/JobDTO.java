package com.sangkhim.spring_boot3_quartz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDTO {

  private String from;
  private String to;
  private String subject;
  private String body;
}
