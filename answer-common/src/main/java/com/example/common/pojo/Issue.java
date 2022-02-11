package com.example.common.pojo;

import com.alibaba.fastjson.annotation.JSONField;
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
    @JSONField(name = "id")
    @JsonProperty("id")
    private Integer issueId;
    private String title;
    private String body;
    private String author;
    private String label;

    @JSONField(serialize =false, deserialize=false)
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDate releaseTime;

    @JsonIgnoreProperties({"collectionCount", "category"})
    @JSONField(serialize =false, deserialize=false)
    private Integer collectionCount;
    @JSONField(serialize =false, deserialize=false)
    private String category;

  public Issue(Integer issueId, Integer collectionCount) {
    this.issueId = issueId;
    this.collectionCount = collectionCount;
  }
}
