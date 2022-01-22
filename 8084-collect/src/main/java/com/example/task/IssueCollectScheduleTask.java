package com.example.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.common.pojo.Issue;
import com.example.common.pojo.IssueCollectRecord;
import com.example.dao.mapper.IssueMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
@Slf4j
public class IssueCollectScheduleTask {

    private Logger logger = LoggerFactory.getLogger(IssueCollectScheduleTask.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private RedisTemplate redisTemplate;
//    @Resource
//    private IssueCollectMapper issueCollectMapper;
    @Resource
    private IssueMapper issueMapper;

    /**
     * 同步某篇文章总的点赞数到MySQL
     */
    private void issueCollectCount(Integer issueId, Set<Long> userIdSet) {
        LambdaQueryWrapper<Issue> wrapper = Wrappers.lambdaQuery();
        //计算出该批次的用户点赞量
        Integer collectCount = userIdSet.size();
        //将总点赞量插入到数据库中
        if(issueId!=null){
            Issue issue = new Issue(issueId, collectCount);
            wrapper.eq(Issue::getIssueId, issueId);
            issueMapper.update(issue, wrapper);
        }
    }

    /**
     * 同步用户喜欢的文章
     * @param issueId
     * @param userIdSet
     */
    private void collectInsert(Integer issueId, Set<Integer> userIdSet) {
        for (Integer userId : userIdSet) {
            //遍历userlist，将该条收藏记录添加到收藏表中
            IssueCollectRecord collectRecord = new IssueCollectRecord(issueId, userId);
//            issueCollectMapper.insert(collectRecord);
        }
    }

    /**
     * 定时Redis持久化到数据库
     */
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void redisDataToMySQL() {

        logger.info("time:{}，开始执行Redis数据持久化到MySQL任务", LocalDateTime.now().format(formatter));

        //更新文章总的点赞数
        //获取变量中的指定map键是否有值
        // 如果存在该map键则获取值，没有则返回null
//        Map<String, String> articleCountMap = redisTemplate.opsForHash().entries(ARTICLE_LIKED_USER_KEY);

        //进行插入
//        for (Map.Entry<String, String> entry : articleCountMap.entrySet()) {
//           String articleId = entry.getKey();
//            Set<Long> userIdSet = FastjsonUtil.deserializeToSet(entry.getValue(), Long.class);
//            //1.同步某篇文章总的点赞数到MySQL
//            issueCollectCount(articleId, userIdSet);
//            //2.同步用户喜欢的文章
//            collectInsert(articleId, userIdSet);
 //       }

        logger.info("time:{}，结束执行Redis数据持久化到MySQL任务", LocalDateTime.now().format(formatter));
    }
}
