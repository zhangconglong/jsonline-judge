package com.example.common.dto;

import lombok.Data;

import java.time.LocalDate;

//@TableName("issue")
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class IssueES {

//    @JsonProperty("id")
    private String issueId;
    private String title;
    private String body;
    private String author;
    private String label;
    private LocalDate releaseTime;
}
