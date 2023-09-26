package com.df.cn.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * 异步回调：不带回调函数的 API
 * @author qindongfang
 * @date 2023/9/17
 **/
public class AsyncCustomProducerCallback {

    /*
     * 回调函数会在 producer 收到 ack 时调用，为异步调用，该方法有两个参数，分别是
     * RecordMetadata 和 Exception，如果 Exception 为 null，说明消息发送成功，如果
     * Exception 不为 null，说明消息发送失败。
     */
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.230.159:9092");
        props.put("acks", "all");
        props.put("retries", 1);//重试次数
        props.put("batch.size", 16384);//批次大小
        props.put("linger.ms", 1);//等待时间
//        props.put("buffer.memory", 33554432);//RecordAccumulator 缓冲区大小
        props.put("buffer.memory", 16384*3);//RecordAccumulator 缓冲区大小
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new
                KafkaProducer<>(props);
        for (int i = 0; i < 100; i++) {
//            producer.send(new ProducerRecord<String, String>("abctopic",
            producer.send(new ProducerRecord<String, String>("syncTopic",
//                    Integer.toString(i), Integer.toString(i)), new Callback() {
                    Integer.toString(i)+"_key", Integer.toString(i)+"_value"), new Callback() {
                //回调函数，该方法会在 Producer 收到 ack 时调用，为异步调用
                @Override
                public void onCompletion(RecordMetadata metadata,
                                         Exception exception) {
                    if (exception == null) {
                        System.out.println("success->" + metadata.offset());
                    } else {
                        exception.printStackTrace();
                    }
                }
            });
        }
        producer.close();
    }

}
