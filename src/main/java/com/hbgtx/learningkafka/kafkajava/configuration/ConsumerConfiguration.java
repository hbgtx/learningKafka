package com.hbgtx.learningkafka.kafkajava.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.hbgtx.learningkafka.kafkajava.util.ConstantUtils.GROUP_ID;
import static com.hbgtx.learningkafka.kafkajava.util.ConstantUtils.KAFKA_BROKER;

@Configuration
public class ConsumerConfiguration {

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigurations());
    }

    @Bean
    public Map<String, Object> consumerConfigurations() {
        Map<String, Object> configurations = new HashMap<>();

        configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        configurations.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return configurations;
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
