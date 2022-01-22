package com.example.pay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pay.entity.Book;
import org.springframework.stereotype.Repository;
@Repository
public interface BookMapper extends BaseMapper<Book> {
    // 根据用户id查询图书信息
//    List<Book> findByUserId(@Param("userId") Integer userId);
}
