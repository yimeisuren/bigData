package com.xiong.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaTopic_01_XXXProducer {
    public static void main(String[] args) {
        // 1. 创建Kafka Topic的生产者
        //1.1 配置项
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9092,slave001:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //1.2 创建对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        // 2. 发送消息
        kafkaProducer.send(new ProducerRecord<>("root", "root"));

        // 3. 关闭资源
        kafkaProducer.close();

    }
}
