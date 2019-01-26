package com.baizhi;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

import java.io.IOException;

public class TestMapReduce2 {

    public static class MyMapper2 extends Mapper<LongWritable, Text,Text, NullWritable>{

        /**
         *
         * @param key  偏移量
         * @param value   line
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();

            String[] columns = line.split("\t");

            String column_1 = columns[0];

            Text k2 = new Text(column_1);

            NullWritable v2 = NullWritable.get();

            context.write(k2,v2);
        }
    }

    public static class MyReduce2 extends Reducer<Text,NullWritable,Text,NullWritable>{
        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            context.write(key,NullWritable.get());
        }
    }


    public static void main(String[] args) throws Exception{
        Job job = Job.getInstance();
        job.setJarByClass(TestMapReduce2.class);
        job.setJobName("seconde");
        job.setNumReduceTasks(2);


        //inputFormat
        FileInputFormat.addInputPath(job,new Path("/test3"));


        //map
        job.setMapperClass(MyMapper2.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        //shuffle


        //reduce
        job.setReducerClass(MyReduce2.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        //outputFormat
        FileOutputFormat.setOutputPath(job,new Path("/dest2"));

        job.waitForCompletion(true);



    }
}

