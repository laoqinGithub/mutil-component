package com.df.cn.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * 手动提交-同步提交 offset
 * @author qindongfang
 * @date 2023/9/17
 **/
@Slf4j(topic = "UnAutoSyncCommitCustomConsumer")
public class UnAutoSyncCommitCustomConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        //Kafka 集群
        props.put("bootstrap.servers", "node1:9092,node2:9092,node3:9092");
        //消费者组，只要 group.id 相同，就属于同一个消费者组
        props.put("group.id", "test");
        props.put("enable.auto.commit", "false");//关闭自动提交 offset
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("testSyncTopic"));//消费者订阅主题
        while (true) {
            //消费者拉取数据
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
//                log.info(String.format("offset = %d, key = %s, value = %s %n ",
//                        record.offset(), record.key(), record.value()));
                System.out.println(String.format("offset = %d, key = %s, value = %s %n",
                        record.offset(), record.key(), record.value()));
            }
            //同步提交，当前线程会阻塞直到 offset 提交成功
            consumer.commitSync();
        }
    }

}
