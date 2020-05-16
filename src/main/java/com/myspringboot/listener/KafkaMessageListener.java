package com.myspringboot.listener;

import com.myspringboot.pojo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 我们通过@KafkaListener注解来监听名称为test的Topic，消费者分组的组名为test-consumer。
 */
@Component
public class KafkaMessageListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param message
     * @KafkaListener 除了可以指定Topic名称和分组id外，我们还可以同时监听来自多个Topic的消息:
     */
    @KafkaListener(topics = "test", groupId = "test-consumer")
    public void listen(Message message) {
        logger.info("接收消息: {}", message);

    }
//    @KafkaListener(topics = "test", groupId = "test-consumer")
//    public void listen(String message) {
//        logger.info("接收消息: {}", message);
//
//    }
//    @KafkaListener(topics = "test", groupId = "test-consumer")
//    public void listen(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//        logger.info("接收消息: {},partition{}", message,partition);
//    }
/*
    @KafkaListener(groupId = "test-consumer",
            topicPartitions = @TopicPartition(topic = "test",
                    partitionOffsets = {
                            @PartitionOffset(partition = "0", initialOffset = "0")
                    }))
    public void listen(@Payload String message,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        logger.info("接收消息: {}，partition：{}", message, partition);
    }

    @KafkaListener(groupId = "test-consumer",
            topicPartitions = @TopicPartition(topic = "test", partitions = { "0", "1" }))*/
}
