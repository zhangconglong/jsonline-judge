package com.luyi.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用ES服务
 * 与【Product模块】的【ProductController类中的方法名】保存一致
 *
 * author: luyi
 * time: 2022/2/7 16:45
 */
@FeignClient(value="PRODUCT") //服务ID
public interface ProductClient {

    /*
     *一、零散传参
     */
    //1，传统web拼接：?type=xxx&num=15
    @GetMapping("/productList")
    String list(@RequestParam("type") String type,//OpenFeign要求必须写RequestParam的value
                @RequestParam("num") Integer num);

    //2，RestFull风格
//    @GetMapping("/productList/{type}/{num}")
//    Issue findIssueByTypeAndNum(@PathVariable("type") String type,
//                                 @PathVariable("num") Integer num);

   /*
    * 二、对象
    * 1，@RequestBody：把对象转成JSON字符串
    * 2，@RequestPart：以Form表单传递
    */
//    @PostMapping("/productList")
//    String list3(@RequestBody Issue issue);//不写，会是(null,null);

    /**
     * 三、集合
     */
    //返回一个List集合
//    @GetMapping("findAllProductByID")
//    List<Product> findAllProductByID(@PathVariable("id")Integer id);

    //返回一个分页查询的Map集合
}
