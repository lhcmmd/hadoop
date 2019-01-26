package com.baizhi.test;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class TestMapReduce {

    /**
     *    k1   LongWritable
     *    v1   Text
     *
     *
     *    k2   Text
     *    v2   IntWritable
     */

    public static class MyMap extends Mapper<LongWritable,Text,Text, IntWritable> {
        Text k2 = new Text();
        IntWritable v2 = new IntWritable();
        @Override
        /**
         *  k1  key      0
         *  v1  value    suns   xiaohei
         */
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] words = line.split("\t");

            for (String word:words) {
                k2.set(word);
                v2.set(1);
                context.write(k2,v2);
            }
        }
    }

    public static class MyReduce extends Reducer<Text,IntWritable,Text,IntWritable>{
        Text k3 = new Text();
        IntWritable v3 = new IntWritable();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int result = 0;
            for (IntWritable value:values) {
                result+=value.get();
            }

            k3.set(key);
            v3.set(result);

            context.write(k3,v3);
        }
    }

    public static void main(String[] args)throws Exception {
        Job job = Job.getInstance();
        job.setJarByClass(TestMapReduce.class);
        job.setJobName("first");

        //inputFormat
        TextInputFormat.addInputPath(job,new Path("/src/data"));

        //map
        job.setMapperClass(MyMap.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //shuffle 自动完成

        //reduce
        job.setReducerClass(MyReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //outputFormat
        TextOutputFormat.setOutputPath(job,new Path("/dest1"));


        job.waitForCompletion(true);


    }
}
