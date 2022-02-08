package com.example.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@TableName("issue")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {

    @TableId(type = IdType.AUTO)
    @JsonProperty("id")
    private Integer issueId;
    private String title;
    private String body;
    private String author;
    private String label;
    private LocalDate releaseTime;
    private LocalDate timeStr;

    @JsonIgnoreProperties({"collectionCount", "category"})
    private Integer collectionCount;
    private String category;

  public Issue(Integer issueId, Integer collectionCount) {
    this.issueId = issueId;
    this.collectionCount = collectionCount;
  }

}
