package com.example.jwt.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.example.common.config.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 处理用户的头像的工具类
 */
public class AvatarUtil {

    private static final String picture_dir = "/spring-cloud-user-server-8081/src/main/resources/files/"; //文件的上传地址


    public Result<?> setAvatarInfo(String ip, String port, MultipartFile file) throws IOException { //用来接受前台传输过来的文件
        String originalFilename = file.getOriginalFilename();//获取上传的文件名称——文件名重复

        //定义文件的唯一标识（前缀）:使用hutool的UUID
        String flag = IdUtil.fastSimpleUUID();
        //获取springboot-vue文件的路径
        String rootFilePath = System.getProperty("user.dir") + picture_dir + flag + "_" + originalFilename;

        FileUtil.writeBytes(file.getBytes(), rootFilePath); //文件保存
        System.out.println("文件保存的服务器ip为 = " + ip);

        return Result.success(ip + ":" + port + "/files/" + flag); //返回结果url
    }

}