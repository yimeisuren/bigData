package com.xiong.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaTopic_04_XXXPartitionProducerWithCallback {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        // 指定分区后, 必须要指定key, 那么key是什么东西呢?
        // 如果仅仅指定key, 会按照 hash(key) % partitions_count 来分配分区.
        // 如果将key指定为mysql中的某张表的名字, 那么就可以将该表中的数据全部导入到一个分区中
        ProducerRecord<String, String> record = new ProducerRecord<>("root", 1, "", "root");

        kafkaProducer.send(record,
                // 回调函数
                (metadata, exception) -> {
                    if (exception == null) {
                        System.out.println("metadata.topic() = " + metadata.topic());
                        System.out.println("metadata = " + metadata);
                    } else {
                        System.out.println("exception = " + exception);
                    }
                });
        kafkaProducer.close();
    }
}
