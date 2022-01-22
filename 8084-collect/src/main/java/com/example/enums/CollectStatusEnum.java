package com.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户点赞的状态
 */
@Getter
@AllArgsConstructor
public enum CollectStatusEnum {
    LIKE(1, "点赞"),
    UNLIKE(0, "取消点赞/未点赞"),
    ;

    private int code;

    private String msg;
}