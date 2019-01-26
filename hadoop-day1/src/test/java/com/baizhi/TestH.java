package com.baizhi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.FileOutputStream;

public class TestH {
    @Test
    public void test2() throws Exception{
        Configuration configuration = new Configuration();
        configuration.set("fs.default.name","hdfs://hadoop:8020");

        FileSystem fileSystem = FileSystem.get(configuration);

        FSDataInputStream inputStream = fileSystem.open(new Path("/user/bb"));

        FileOutputStream outputStream = new FileOutputStream("e:\\hadoop\\a.txt");

        IOUtils.copyBytes(inputStream,outputStream,1024,true);


    }
}
