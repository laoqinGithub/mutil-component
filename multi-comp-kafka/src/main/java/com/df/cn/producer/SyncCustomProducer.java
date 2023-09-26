package com.df.cn.producer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 同步发送 API
 *
 * @author qindongfang
 * @date 2023/9/17
 **/
@Slf4j(topic = "SyncCustomProducer")
public class SyncCustomProducer {

    /*
     * 同步发送的意思就是，一条消息发送之后，会阻塞当前线程，直至返回 ack。
     * 由于 send 方法返回的是一个 Future 对象，根据 Futrue 对象的特点，我们也可以实现同
     * 步发送的效果，只需在调用 Future 对象的 get 方发即可
     */
    public static void main(String[] args) throws ExecutionException,
            InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node1:9092,node2:9092,node3:9092");//kafka 集群，broker-list
        props.put("acks", "all");
        props.put("retries", 1);//重试次数
        props.put("batch.size", 16384);//批次大小
        props.put("linger.ms", 1);//等待时间
        props.put("buffer.memory", 33554432);//RecordAccumulator 缓冲区大小
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("testSyncTopic",
                    2, Integer.toString(i), Integer.toString(i) + "_sync_async");
            log.info("product into: " + JSON.toJSONString(producerRecord));
            producer.send(producerRecord).get();
        }
        producer.close();
    }

}
