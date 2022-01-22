package com.example.securtiy.encrypt.service;


import com.example.securtiy.encrypt.rsa.Base64Utils;
import com.example.securtiy.encrypt.rsa.RSA;
import org.springframework.stereotype.Service;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;

/**
 * @version: v1.0
 * @description： RSA加解密的实现类
 * @author: zhangconglong on 2021/11/23 22:10
 */
@Service("RsaService")
public class RsaServiceImpl implements RsaService {

    /**
     * 字符串-RSA解密
     * @param encryptData 已经被加密的字符串
     * @return
     * @throws Exception
     */
    public String RSADecryptDataPEM(String encryptData, String prvKey) throws Exception {
        //将字符串分解为字符数组后，再进行解密
    	byte[] encryptBytes = encryptData.getBytes();
        byte[] prvdata = RSA.decryptByPrivateKey(Base64Utils.decode(encryptData), prvKey);

        String outString = new String(prvdata, "UTF-8");
        return outString;
    }

    /**
     * 【解密】-使用公钥，对已加密的字符数组解密
     * @param encryptBytes 已经被加密的字符数组
     * @param prvKey
     * @return
     * @throws Exception
     */
    @Override
	public String RSADecryptDataBytes(byte[] encryptBytes, String prvKey)throws Exception {

    	byte[] prvdata = RSA.decryptByPrivateKey(encryptBytes, prvKey);
        String outString = new String(prvdata, "utf-8");
        return outString;
	}

    /**
     * 字符串-RSA加密
     * @param data
     * @return
     * @throws Exception
     */
    public String RSAEncryptDataPEM(String data, String pubKey) throws Exception {

        byte[] pubdata = RSA.encryptByPublicKey(data.getBytes("UTF-8"), pubKey);
        String outString = new String(Base64Utils.encode(pubdata));

        return outString;
    }

    /**
     * 获取加密算法的名称
     * @return
     */
	@Override
	public String getRsaAlgorithm() {
		KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return keyFactory.getAlgorithm();
	}
}