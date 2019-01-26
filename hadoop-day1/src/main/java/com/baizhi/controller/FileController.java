package com.baizhi.controller;

import com.baizhi.util.MD5Utils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.util.MD5FileUtils;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Set;

@Controller
@RequestMapping("file")
public class FileController {
    @Autowired
    RedisTemplate redisTemplate;


    @RequestMapping("fileUp")
    @ResponseBody
    public void fileUp( MultipartFile hfile) throws Exception{
        String subName = hfile.getOriginalFilename();


        String fileMD5String = MD5Utils.getFileMD5String(hfile.getInputStream());
        System.out.println("MD5： "+fileMD5String);
        // 使用通用模板 操作Set类型
        SetOperations setOps = redisTemplate.opsForSet();
        Set aaa = setOps.members(fileMD5String);
        System.out.println(aaa+"+++++");
        if(aaa.isEmpty()){
            setOps.add(fileMD5String,subName);

            Configuration configuration = new Configuration();
            configuration.set("fs.default.name","hdfs://hadoop:8020");

            FileSystem fileSystem = FileSystem.get(configuration);

            FSDataOutputStream outputStream = fileSystem.create(new Path("/user/ee"));

            //FileInputStream inputStream = new FileInputStream("e:\\hadoop\\a.txt");
            FileInputStream inputStream = (FileInputStream) hfile.getInputStream();
            IOUtils.copyBytes(inputStream,outputStream,1024,true);
        }else{
            System.out.println("存在");
        }
    }
}
