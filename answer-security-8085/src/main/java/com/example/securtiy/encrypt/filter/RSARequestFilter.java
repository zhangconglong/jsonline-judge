//package com.example.securtiy.encrypt.filter;
//
//import com.google.common.base.Charsets;
//import com.google.common.base.Strings;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import com.netflix.zuul.http.HttpServletRequestWrapper;
//import com.netflix.zuul.http.ServletInputStreamWrapper;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StreamUtils;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import pers.fjl.encrypt.rsa.RsaKeys;
//import pers.fjl.encrypt.service.RsaService;
//
//import javax.annotation.Resource;
//import javax.crypto.BadPaddingException;
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URLDecoder;
//
///**
// * @version: v1.0
// * @description： 加密过滤器，对于未进行
// * @author: zhangconglong on 2021/11/23 21:13
// */
//@Component
//@CrossOrigin
//public class RSARequestFilter {
//
//    @Resource
//    private RsaService rsaService;
//    /*定义token头*/
//    private String tokenHeader = "Authorization";
//
//    /**
//     * 设置解密操作需要在转发之前执行
//     * @return
//     */
//    @Override
//    public String filterType() {
//        return FilterConstants.PRE_TYPE;
//    }
//
//    /**
//     * 设置过滤器的执行顺序
//     * @return
//     */
//    @Override
//    public int filterOrder() {
//        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
//    }
//
//    /**
//     * 是否使用过滤器
//     * @return
//     */
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        /*
//         * 1. 从request body中读取出加密后的请求参数
//         * 2. 将加密后的请求参数用私钥解密
//         * 3. 将解密后的请求参数写回request body中
//         * 4. 转发请求
//         */
//
//        //获取容器
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        HttpServletResponse response = ctx.getResponse();
//        String token =  request.getHeader(this.tokenHeader);
//
//        String requestData = null; //存放加密后的数据
//        String decryptData = null; //存放解密后的数据
//        //网关从前端接收过来的request后，还要再加上token到头转发request，否则后端服务器会拦截
//        ctx.addZuulRequestHeader("Authorization",token);
//        //需要设置request请求头中的Content-Type为json格式，否则api接口模块就需要进行url转码操作
//        ctx.addZuulRequestHeader("Content-Type", String.valueOf(MediaType.APPLICATION_JSON) + ";charset=UTF-8");
//
//        try {
//            ServletInputStream inputStream = request.getInputStream(); //通过request获取inputStream
//
//            //从inputStream中得到加密后的数据
//            requestData = StreamUtils.copyToString(inputStream, Charsets.UTF_8);
//            //设置解密数据的编码格设为UTF-8
//            String s = URLDecoder.decode(requestData, "UTF-8");
//            //将空格替换为+
//            String s2 = s.replace(' ', '+');
//            if (requestData != null && s2 != null) {
////                System.out.println("加密后" + requestData);
////                System.out.println("替换后" + s2);
//            }
//            //解密
//            if (!Strings.isNullOrEmpty(s2)) {
//                try {
//                    decryptData = rsaService.RSADecryptDataPEM(s2, RsaKeys.getServerPrvKeyPkcs8());
//                } catch (BadPaddingException e) {
////                    System.out.println("网关发送的是明文数据");
//                }
////                System.out.println("解密后" + decryptData);
//            }
//
//            if (!Strings.isNullOrEmpty(decryptData)) {
//                byte[] bytes = decryptData.getBytes();
//                //使用RequestContext进行数据的转发
//                ctx.setRequest(new HttpServletRequestWrapper(request) {
//
//                    @Override
//                    public String getHeader(String name) {
//                        return token;
//                    }
//
//                    @Override
//                    public ServletInputStream getInputStream() throws IOException {
//                        return new ServletInputStreamWrapper(bytes);
//                    }
//
//                    @Override
//                    public int getContentLength() {
//                        return bytes.length;
//                    }
//
//                    @Override
//                    public long getContentLengthLong() {
//                        return bytes.length;
//                    }
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}