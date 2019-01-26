package com.baizhi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Set;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HadoopDay1ApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test2() throws Exception{
        Configuration configuration = new Configuration();
        configuration.set("fs.default.name","hdfs://hadoop:8020");

        FileSystem fileSystem = FileSystem.get(configuration);

        FSDataInputStream inputStream = fileSystem.open(new Path("e:\\hadoop\\a.txt"));


        FSDataOutputStream outputStream = fileSystem.create(new Path("user/ee"));

        IOUtils.copyBytes(inputStream,outputStream,1024,true);


    }

    @Test
    public void testRedis(){
        // 使用通用模板 操作Set类型
        SetOperations setOps = redisTemplate.opsForSet();
      //  setOps.add("aaa", "123456");
        Set aaa = setOps.members("aaa");
        System.out.println(aaa);



        RedisOperations operations = setOps.getOperations();



     /*   Jedis jedis = new Jedis("192.168.194.139",7000);
        String p = jedis.get("g");
        System.out.println(p+"-------------------");*/
    }

}

