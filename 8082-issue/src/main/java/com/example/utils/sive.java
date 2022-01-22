//package com.luyi.utils;
//
//import java.io.File;
//import java.io.FileReader;
//
//public class sive {
//    public void sive(){
//        File file=new File(path);//json文件路径
//        List<reportData> reportData=new ....//实体对象的list集合
//        JSONReader reader=new JSONReader(new FileReader(file));
//        int count=0;
//        read.starArray();//开始读json数组
//        while(readser.hasNext()){
//            read.startObject();//开始取json对象
//            JSONObject json=new JSONObject();
//            while(reader.hasNext()){
//                string key=reader.readString();//json的key
//                string value=reader.readObject().toString();//json的value
//                json.put(key,value);
//            }
//            reportData data=JSON.parseObject(json.toString(),reportData.class)//jso格式转换对象
//            reportData.add(data);
//            count++;
//            if(count%5000==0){//每5000条数据插入一次数据库，数据库操作用的mybatis的循环insert
//                mapper.save(reportData);
//                reportData.clear();//清空list
//            }
//            reader.endObject();
//        }
//        reader.endArray();
//        reader.close();
//        if(reportData.size()>0){//如果读完之后，size>0，说明list还有数据，数据不足5000
//            mapper.save(erportData)
//        }
//
//    }
//}