package com.xiong.hadoop.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    IntWritable valueOut = new IntWritable();

    //对于每个key, 调用一个reduce()函数
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //聚合
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        //保存输出
        valueOut.set(sum);
        context.write(key, valueOut);
    }
}
