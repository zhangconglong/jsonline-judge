package com.example.securtiy.encrypt.service;

/**
 * @version: v1.0
 * @description： rsa加密解密服务接口
 * @author: zhangconglong on 2021/11/23 22:10
 */
public interface RsaService {

	/**
     * 字符串-RSA解密
     * @param encryptData
     * @return
     * @throws Exception
     */
	public String RSADecryptDataPEM(String encryptData, String prvKey) throws Exception;

	/**
     * 字符数组-RSA解密
     * @param encryptBytes 要被加密的
     * @return
     * @throws Exception
     */
	public String RSADecryptDataBytes(byte[] encryptBytes, String prvKey) throws Exception;

    /**
     * 字符串-RSA加密
     * @param data 要被加密的字符串
     * @return
     * @throws Exception
     */
	public String RSAEncryptDataPEM(String data, String pubKey) throws Exception;

	/**
	 * 获取RAS加密算法
	 * @return
	 */
	public String getRsaAlgorithm();
}