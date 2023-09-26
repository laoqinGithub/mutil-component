package com.df.cn.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * 自动提交offset
 *
 * @author qindongfang
 * @date 2023/9/17
 **/
@Slf4j(topic = "AutoCommitCustomConsumer")
public class AutoCommitCustomConsumer {

    /*
     * KafkaConsumer：需要创建一个消费者对象，用来消费数据
     * ConsumerConfig：获取所需的一系列配置参数
     * ConsuemrRecord：每条数据都要封装成一个 ConsumerRecord 对象
     * 为了使我们能够专注于自己的业务逻辑，Kafka 提供了自动提交 offset 的功能。
     * 自动提交 offset 的相关参数：
     * enable.auto.commit：是否开启自动提交 offset 功能
     * auto.commit.interval.ms：自动提交 offset 的时间间隔
     */
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node1:9092,node2:9092,node3:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true"); // 是否开启自动提交 offset 功能
        props.put("auto.commit.interval.ms", "1000"); // 自动提交 offset 的时间间隔
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("testSyncTopic"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
//                log.info(String.format("offset = %d, key = %s, value = %s %n",
//                        record.offset(), record.key(), record.value()));
                  System.out.println(String.format("offset = %d, key = %s, value = %s %n",
                            record.offset(), record.key(), record.value()));
        }
    }

}
