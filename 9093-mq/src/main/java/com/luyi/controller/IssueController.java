package com.luyi.controller;

import com.example.common.pojo.Issue;
import com.luyi.feignClient.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OpenFeign 服务调用
 * author: luyi
 * time: 2022/2/7 15:16
 */
@RestController
public class IssueController {

    @Autowired
    ProductClient productClient;

    /*
     * 一，零散参数传递
     */
    //1，传统web拼接：?type=xxx&num=15
    @GetMapping("/getProductList")
    public String getProductList(@RequestParam String type,
                                 @RequestParam Integer num ){
        //OpenFeign 调用远程服务
        return productClient.list(type, num);
    }

    //2，RestFull风格
    @PostMapping("/findIssueByTypeAndNum")
    public Issue findIssueByTypeAndNum(@RequestParam String type,
                                       @RequestParam Integer num ){
        return productClient.findIssueByTypeAndNum(type, num);//返回一个对象
    }


    /*
     * 二、对象
     */
    public String getProductList(@RequestBody Issue issue){
        return productClient.list3(issue);
    }

    /*
     * 三、数组与集合传递
     */
    //返回对象集合List
    public List<Product> findAllProductByID(@PathVariable("id") Integer id){
        return productClient.findAllProductByID(id);
    }
}
