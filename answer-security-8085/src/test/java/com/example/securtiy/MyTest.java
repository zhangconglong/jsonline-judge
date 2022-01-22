package com.example.securtiy;

import com.example.securtiy.encrypt.rsa.RsaKeys;
import com.example.securtiy.encrypt.service.RsaServiceImpl;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @version: v1.0
 * @description：
 * @author: zhangconglong on 2021/11/26 21:29
 */
@Component
public class MyTest {

    @Resource
    RsaServiceImpl rsaService = new RsaServiceImpl();

    @Test
    public void test01() throws Exception {
        //进行加密字符串
        String encryData = rsaService.RSAEncryptDataPEM("大家好，这个是我是张从龙", RsaKeys.getServerPubKey());
        System.out.println("这是使用公钥解码的结果："+encryData);
        //进行解密
        String decryptData = rsaService.RSADecryptDataPEM(encryData, RsaKeys.getServerPrvKeyPkcs8());
        System.out.println("这是使用私钥解码的结果："+decryptData);
    }
}