package com.xiong.kafka.producer;

import com.xiong.kafka.producer.partitioner.XPartitioner;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaTopic_05_XXXCustomPartitionerProducerWithCallback {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 使用自定义的分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, XPartitioner.class);

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>("root", 1, "", "root");
        kafkaProducer.send(record,
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
