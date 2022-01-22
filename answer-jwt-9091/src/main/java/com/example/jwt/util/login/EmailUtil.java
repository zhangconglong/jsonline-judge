package com.example.jwt.util.login;

import com.example.common.config.Result;
import com.example.dao.vo.UserVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Component
public class EmailUtil {
    //@Autowired
    private final JavaMailSender mailSender = new JavaMailSenderImpl();//一定要用@Autowired

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 给前端输入的邮箱，发送验证码
     */
    public Result<?> sendMimeMail(String email, HttpSession session) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();//建立邮件消息
            mailMessage.setFrom(from); //发送者
            mailMessage.setTo(email); //接收者

            String code = randomCode();//生成随机数

            //将随机数放置到session中
            session.setAttribute("email",email);
            session.setAttribute("code",code);

            mailMessage.setSubject("【3W社在线答题】验证码邮件：");//主题
            mailMessage.setText("尊敬的用户，您收到的验证码是："+code);//内容

            mailSender.send(mailMessage);//发送
            return  Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(-1, "邮件发送失败");
        }
    }

    /**
     * 随机生成6位数的验证码
     * @return String code
     */
    public String randomCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    /**
     * 检验验证码是否一致
     */
    public boolean registered(UserVo userVo){
        //获取session中的验证信息
        String email = userVo.getEmail();
        String code = userVo.getCode();

        //获取表单中的提交的验证信息
        String voCode = userVo.getCode();

        //如果email数据为空，或者不一致，则注册失败
        if (email == null || email.isEmpty()){
            return false;
        }else if (!code.equals(voCode)){
            return false;
        }
        return true;//跳转成功页面
    }
}