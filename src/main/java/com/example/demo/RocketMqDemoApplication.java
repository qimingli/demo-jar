package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MQProducer;

@SpringBootApplication
public class RocketMqDemoApplication {

	public static final Logger LOGGER = LoggerFactory.getLogger(RocketMqDemoApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RocketMqDemoApplication.class, args);
	}
	
	private String nameServer = "10.8.34.67:9876;10.8.34.68:9876";

	private String groupName="orderGroup";

	private String topic="scm2InnovationCenter_resp";

	
	private String orderTag = "order";

	private int sendMsgTimeout=10000;
	
	
/*	rocketmq.nameServer=10.8.34.67:9876;10.8.34.68:9876
	rocketmq.groupName=orderGroup
	rocketmq.topic=scm2InnovationCenter_resp
	rocketmq.tags.orderTag=order
	rocketmq.sendMsgTimeout=10000	*/
/*	@Autowired
    private MessageListener messageListener;*/

	@Bean
	public MQProducer getRocketMQProducer() throws MQClientException {

		DefaultMQProducer producer = new DefaultMQProducer(groupName);
		producer.setNamesrvAddr(nameServer);
		producer.setSendMsgTimeout(sendMsgTimeout);
		producer.start();
		LOGGER.info(String.format("producer is start ! groupName:[%s],namesrvAddr:[%s]", groupName, nameServer));
		return producer;
	}

/*	@Bean
	public DefaultMQPushConsumer getRocketMQConsumer() throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
		consumer.setNamesrvAddr(groupName);
		consumer.setMessageListener(messageListener);
		consumer.subscribe(topic, orderTag);
		consumer.start();
		LOGGER.info("consumer is start !!! groupName:{},topic:{},namesrvAddr:{}", groupName, topic, nameServer);
		return consumer;
	}*/
}
