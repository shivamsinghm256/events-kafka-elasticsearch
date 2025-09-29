package com.work.events_kafka_elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EventsKafkaElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsKafkaElasticsearchApplication.class, args);
	}

}
