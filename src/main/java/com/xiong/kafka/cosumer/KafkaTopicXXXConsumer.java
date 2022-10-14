package com.xiong.kafka.cosumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.Properties;

public class KafkaTopicXXXConsumer {
    public static void main(String[] args) {
        // 1. 创建Kafka消费者对象
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        // 2. 设置订阅的topic
        ArrayList<String> topics = new ArrayList<>();
        topics.add("root");
        kafkaConsumer.subscribe(topics);

        // 3. 关闭资源
        kafkaConsumer.close();
    }
}
