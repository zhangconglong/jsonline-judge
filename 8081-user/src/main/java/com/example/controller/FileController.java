package com.example.controller;

import com.example.common.config.Result;
import com.example.util.common.AvatarUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @version: v1.0
 * @description： 文件处理
 * @author: zhangconglong on 2021/11/24 15:07
 */
@RestController
@RequestMapping("/user/avatar")
public class FileController {

    @Value("${server.port}") //从yaml、properties中读取端口
    private String port;
    @Value("${system-params.ip}") //从文件中读取ip地址
    private static String ip;

    /**
     * 上传头像
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadAvatar")
    public Result<?> uploadAvatar(@RequestHeader("Authorization") String token,
                                   MultipartFile file) throws IOException {

        //将文件保存到指定的目录下
        Result<?> result = new AvatarUtil().setAvatarInfo(ip, port, file);
        /**
         * 将当前头像保存到用户的数据库中
         */

        return result; //返回结果url
    }

    /**
     * 修改用户的头像
     * 把之前的图片删除，然后修改
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/modifyAvatar")
    public Result<?> modifyAvatar(@RequestHeader("Authorization") String token,
                                  MultipartFile file) throws IOException {

        //将文件保存到指定的目录下
        Result<?> result = new AvatarUtil().setAvatarInfo(ip, port, file);
        /**
         * 将当前头像修改到用户的数据库中
         */
        return result; //返回结果url
    }


    /**
     * 下载文件
     * @param pictureUUID
     * @param response
     */
//    @GetMapping("/{pictureUUID}") //下载文件
//    public void getFiles(@PathVariable String pictureUUID,
//                         HttpServletResponse response) { //用来接受前台传输过来的对象
//
//        OutputStream os; //输出流对象
//        String basePath = System.getProperty("user.dir") + "/3W/src/main/resources/files/"; //根路径
//        List<String> fileNames = FileUtil.listFileNames(basePath);//获取路径下的所有文件名称
//        String fileName = fileNames.stream().filter(name -> name.contains(pictureUUID)).findAny().orElse("");//找到参数一致的文件
//        try {
//            if (StrUtil.isNotEmpty(fileName)) {
//                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//                response.setContentType("application/octest-stream");
//                byte[] bytes = FileUtil.readBytes(basePath + fileName);//通过文件路径读取文件字节流
//                os = response.getOutputStream();//通过输出流返回文件
//                os.write(bytes);
//                os.flush();
//                os.close();
//            }
//        } catch (Exception e) {
//            System.out.println("文件下载失败");
//        }
//    }
}