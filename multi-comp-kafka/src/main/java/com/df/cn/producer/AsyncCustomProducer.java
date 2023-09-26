package com.df.cn.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 异步回调：不带回调函数的 API
 * @author qindongfang
 * @date 2023/9/17
 **/
public class AsyncCustomProducer {
    /*
     * KafkaProducer：需要创建一个生产者对象，用来发送数据
     * ProducerConfig：获取所需的一系列配置参数
     * ProducerRecord：每条数据都要封装成一个 ProducerRecord 对象
     */
    public static void main(String[] args) {
        Properties props = new Properties();
        //kafka 集群，broker-list
//        props.put("bootstrap.servers", "node1:9092");
        props.put("bootstrap.servers", "192.168.230.159:9092");
        props.put("acks", "all");
        //重试次数
        props.put("retries", 1);
        //批次大小
        props.put("batch.size", 16384);
        //等待时间
        props.put("linger.ms", 1);
        //RecordAccumulator 缓冲区大小
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        System.out.println("producer send start ...");
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String, String>("demotopic",
                    Integer.toString(i), Integer.toString(i)+"_abc_string"));
        }
        producer.close();
        System.out.println("producer send success ...");
    }

}





