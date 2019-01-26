package com.baizhi.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;


import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TestHDFS {
   // @Test
    /**
     * hdfs 下载文件
     */
    public void test1() throws Exception{

        Configuration configuration = new Configuration();
        configuration.set("fs.default.name","hdfs://hadoop:8020");

        FileSystem fileSystem = FileSystem.get(configuration);


        FSDataInputStream inputStream = fileSystem.open(new Path("/test/data"));


        IOUtils.copyBytes(inputStream,System.out,1024,true);

    }

   // @Test
    public void test2() throws Exception{
        Configuration configuration = new Configuration();
        configuration.set("fs.default.name","hdfs://hadoop:8020");

        FileSystem fileSystem = FileSystem.get(configuration);

        FSDataInputStream inputStream = fileSystem.open(new Path("/test/data"));

        FileOutputStream outputStream = new FileOutputStream("d:\\suns.txt");

        IOUtils.copyBytes(inputStream,outputStream,1024,true);


    }

   // @Test
    public void test3()throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.default.name","hdfs://hadoop:8020");

        FileSystem fileSystem = FileSystem.get(configuration);

        FSDataOutputStream outputStream = fileSystem.create(new Path("/test/test1"));

        FileInputStream inputStream = new FileInputStream("d:\\suns.txt");

        IOUtils.copyBytes(inputStream,outputStream,1024,true);
    }

   // @Test
    public void test4()throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.default.name","hdfs://hadoop:8020");

        FileSystem fileSystem = FileSystem.get(configuration);

        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/xiaojr"));

        for (FileStatus fs: fileStatuses) {
            System.out.println(fs);
        }

        //fileSystem.delete();

        //fileSystem.mkdirs(new Path("/xiaojr/xiaowb"));

//        RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fileSystem.listFiles(new Path("/test"), true);
//
//         while(locatedFileStatusRemoteIterator.hasNext()){
//             LocatedFileStatus next = locatedFileStatusRemoteIterator.next();
//             System.out.println(next);
//         }


    }

}
