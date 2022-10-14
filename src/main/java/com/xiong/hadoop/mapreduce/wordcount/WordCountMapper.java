package com.xiong.hadoop.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * KEYIN,
 * VALUEIN,
 * KEYOUT,
 * VALUEOUT
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text keyOut;
    private final IntWritable valueOut = new IntWritable(1);

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //map是一行一行处理数据
        //获取文档中的每一行, 对应value, 为了方便字符串的处理, 将其转化为String类型
        String s = value.toString();
        //根据业务编写相关代码
        String[] words = s.split(" ");
        for (String word : words) {
            keyOut.set(word);
            //context保存数据和配置环境等, 用于mapper和reducer之间的数据传递
            context.write(keyOut, valueOut);
        }
    }

    @Override
    public void run(Context context) throws IOException, InterruptedException {
        super.run(context);
    }
}
