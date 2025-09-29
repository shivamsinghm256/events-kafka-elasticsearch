package com.work.events_kafka_elasticsearch.producer;

import com.fasterxml.jackson.databind.JsonNode;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GitHubEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MeterRegistry meterRegistry;

    private final WebClient webClient = WebClient.create("https://api.github.com");

    public GitHubEventProducer(KafkaTemplate<String, String> kafkaTemplate, MeterRegistry meterRegistry) {
        this.kafkaTemplate = kafkaTemplate;
        this.meterRegistry = meterRegistry;
    }

    @Scheduled(fixedRate = 300000)
    public void fetchAndSendEvents(){
        meterRegistry.counter("github.api.poll.count").increment();
        webClient.get()
                .uri("/events")
                .retrieve()
                .bodyToFlux(JsonNode.class)
                .doOnError(error -> meterRegistry.counter("github.api.errors").increment())
                .subscribe(event -> {
                    kafkaTemplate.send("github-events", event.toString())
                            .whenComplete((Result, ex) ->{
                                if (ex==null){
                                    meterRegistry.counter("kafka.produce.success").increment();
                                }
                                else {
                                    meterRegistry.counter("kafka.produce.errors").increment();
                                }
                            });
                        }
                );
    }

}
