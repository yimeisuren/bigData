package com.xiong.kafka.producer.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class XPartitioner implements Partitioner {

    /**
     * 自定义分区功能的实现
     *
     * @param topic
     * @param key
     * @param keyBytes
     * @param value      接收到的值, 需要进行强制类型转换
     * @param valueBytes
     * @param cluster
     * @return
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String message = value.toString();

        int result;

        if (message.contains("x")) {
            result = 0;
        } else {
            result = 1;
        }

        return result;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
