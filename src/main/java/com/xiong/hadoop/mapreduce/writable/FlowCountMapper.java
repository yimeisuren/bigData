package com.xiong.hadoop.mapreduce.writable;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountMapper extends Mapper<IntWritable, Text, Text, FlowBean> {
    private FlowBean ValueOut;
    private Text keyOut;

    @Override
    protected void map(IntWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String[] fields = s.split(" +");

        keyOut.set(fields[0]);
        ValueOut.setUpFlow(Integer.parseInt(fields[1]));
        ValueOut.setDownFlow(Integer.parseInt(fields[2]));

        context.write(keyOut, ValueOut);
    }
}
