package com.example.pay.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.config.Result;
import com.example.common.pojo.User;
import com.example.pay.entity.Book;
import com.example.pay.mapper.BookMapper;
import com.example.pay.mapper.OrderMapper;
import com.example.pay.entity.Order;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @version: v1.0
 * @description：订单接口
 * @author: zhangconglong on 2021/11/26 20:04
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Resource
    OrderMapper OrderMapper;

    @Resource
    BookMapper bookMapper;

    /**
     * 保存订单
     * @param Order
     * @return
     */
    @PostMapping
    public Result<?> save(@RequestBody Order Order) {
        OrderMapper.insert(Order);
        return Result.success();
    }

    /**
     * 更新订单
     * @param Order
     * @return
     */
    @PutMapping
    public Result<?> update(@RequestBody Order Order) {
        OrderMapper.updateById(Order);
        return Result.success();
    }

    /**
     * 根据id删除订单
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        OrderMapper.deleteById(id);
        return Result.success();
    }

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(OrderMapper.selectById(id));
    }

    /**
     * 根据书号生成订单
     * @param bookId
     * @return
     */
    @GetMapping("/buy/{bookId}")
    public Result<?> buy(@PathVariable Long bookId) {
        //根据id查出来书名
        Book book = bookMapper.selectById(bookId);
        //根据雪花算法生成要支付的单号
        String orderNo = IdUtil.getSnowflake().nextIdStr();
        String payUrl = "http://localhost:9090/alipay/pay?subject=" + book.getName() + "&traceNo=" + orderNo + "&totalAmount=" + book.getPrice();

        //设置新订单的基本信息
        User user = getUser();
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setTotalPrice(book.getPrice());
        order.setPayPrice(book.getPrice());
        order.setTransportPrice(BigDecimal.ZERO);
        order.setUserId(user.getId());
        order.setUsername(user.getUserName());
        order.setName(book.getName());
        save(order);
        // 新建订单，扣减库存
        return Result.success(payUrl);
    }

    /**
     * 模糊查询订单-分页查询
     * @param pageNum
     * @param pageSize
     * @param search
     * @return
     */
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Order> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Order::getOrderNo, search);
        }
        Page<Order> OrderPage = OrderMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(OrderPage);
    }
}