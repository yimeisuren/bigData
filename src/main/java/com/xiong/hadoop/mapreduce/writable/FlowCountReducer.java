package com.xiong.hadoop.mapreduce.writable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
    private FlowBean ValueOut;

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        int sumOfUpFlow = 0;
        int sumOfDownFlow = 0;
        for (FlowBean value : values) {
            sumOfUpFlow += value.getUpFlow();
            sumOfDownFlow += value.getDownFlow();
        }

        ValueOut.setUpFlow(sumOfUpFlow);
        ValueOut.setDownFlow(sumOfDownFlow);
        ValueOut.setSumFlow();
        context.write(key, ValueOut);
    }

}
