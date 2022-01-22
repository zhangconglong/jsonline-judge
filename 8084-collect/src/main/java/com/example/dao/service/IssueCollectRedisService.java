package com.example.dao.service;

public interface IssueCollectRedisService {

    void addIssueFavour(Integer issueId, Integer userId);

    void minusIssueFavour(Integer issueId, Integer userId);

}
