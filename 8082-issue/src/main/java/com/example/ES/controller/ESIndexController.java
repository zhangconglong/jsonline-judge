package com.example.ES.controller;

import com.example.ES.util.ESIndexUtil;
import com.example.common.dto.IssueES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/issue")
public class ESIndexController {

    @Autowired
    ESIndexUtil esIndexUtil;

    @GetMapping("/createIndex")
    public void createIndex() throws IOException {
        IssueES issue = new IssueES();
        esIndexUtil.createIndex("issue","doc");
    }
}
