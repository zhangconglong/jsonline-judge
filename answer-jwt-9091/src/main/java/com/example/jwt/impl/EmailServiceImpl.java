package com.example.jwt.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.common.config.ErrorCode;
import com.example.common.config.Result;
import com.example.common.pojo.User;
import com.example.dao.vo.UserVo;
import com.example.dao.vo.UserVoToUser;
import com.example.jwt.common.mapper.UserMapper;
import com.example.jwt.util.login.EmailUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class EmailServiceImpl { //implements UserService

    @Resource
    private EmailUtil emailUtil;
    @Resource
    UserMapper userMapper;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
//    @Resource
//    UserServiceImpl userServiceImpl;

    /**
     * 通过邮箱登录
     * @param email
     * @param password
     * @return
     */
    public Result<?> emailLogin(String email, String password){
        System.out.println("=================");
        System.out.println("email = " + email);
        System.out.println("password = " + password);

        if(StringUtils.isBlank(email) || StringUtils.isBlank(password)){
            return Result.error(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        //从MYSQL中查询该用户
        //User res = userServiceImpl.findUserByEmail(email, password);
        User res = findUserByEmail(email, password);

        if (res == null){
            return Result.error(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        try{
            ObjectMapper mapper = new ObjectMapper();
            //自己生成的token
            //将结果传入redis
            redisTemplate.opsForValue().set(token, mapper.writeValueAsString(res), 1, TimeUnit.DAYS);
            //使用sa-token登录，，？？？？？
            StpUtil.login(res.getUserId());
            System.out.println("TokenInfo： "+StpUtil.getTokenInfo());
            System.out.println("TokenName： "+StpUtil.getTokenName());
            return Result.success();
        } catch (LockedAccountException e){
            return Result.error(-1, "账号被锁定");
        } catch (UnknownAccountException | IncorrectCredentialsException e){
            return Result.error(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        } catch (AuthenticationException e){
            return Result.error(-1, "认证失败");
        } catch (JsonProcessingException e) {
            return Result.error(-1, "JSON字符串转换失败");
        }
    }

    /**
     * 注册需要邮箱验证码功能
     * @param userVo
     * @return
     */
    public Result<?> emailRegister(UserVo userVo){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getEmail,userVo.getEmail()));

        System.out.println(userVo.getEmail());
        System.out.println(userVo.getPassword());

        if (res != null){
            return Result.error(-1, "该邮箱已经被注册");
        }else {
            if (userVo.getPassword() == null) {
                return Result.error(-1, "密码不能为空");
            }
            else {
                if (emailUtil.registered(userVo)){ //验证邮箱
                 //   String password = new SimpleHash("MD5", userVo.getPassword(),"3W", 2).toString(); //MD5+salt加密
                 //   System.out.println("加密后的密码为 : " + password);
                    User user = UserVoToUser.toUser(userVo);
                    long newId = YitIdHelper.nextId();
                    user.setUserId(newId);
                    System.out.println("雪花算法生成的ID为：" + newId);
                    userMapper.insert(user);
                    return Result.success();
                }else {
                    return Result.error(-1, "验证码错误,请重新尝试");
                }
            }
        }
    }

    /**
     * 实现方法
     */
    /**
     * token的合法性校验
     * @return
     */
    private Result<?> findUserByToken(){
        return Result.success();
    }

    //@Override
    public User findUserById(int id) {
        return null;
    }

    /**
     * 根据token解析出用户信息
     * @param token
     * @return
     */
    //@Override
    public User findUserByToken(String token) {

        if (StringUtils.isBlank(token)){
            return null;
        }
//        Map<String, Object> map = JwtUtils.checkToken(token);

        //从redis中取出token
        String userJson = redisTemplate.opsForValue().get(token);

        System.out.println("userJson = " + userJson);

        if (StringUtils.isBlank(userJson)){
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //将JSON字符串转化成java对象
            return objectMapper.readValue(userJson, new TypeReference<User>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据邮箱
     * @param email
     * @param password
     * @return
     */
    //@Override
    public User findUserByEmail(String email, String password) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getEmail, email)
                .eq(User::getPassword, password));

        return res;
    }
}
