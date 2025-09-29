package com.work.events_kafka_elasticsearch.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.events_kafka_elasticsearch.document.GitHubEventDoc;
import com.work.events_kafka_elasticsearch.repository.GitHubEventRepository;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GitHubEventConsumer {
    private final GitHubEventRepository gitHubEventRepository;
    private final MeterRegistry meterRegistry;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public GitHubEventConsumer(GitHubEventRepository gitHubEventRepository, MeterRegistry meterRegistry) {
        this.gitHubEventRepository = gitHubEventRepository;
        this.meterRegistry = meterRegistry;
    }

    @KafkaListener(topics = "github-events", groupId = "github-consumer-group")
    public void consumeEvent(String message){
        try {
            JsonNode node = objectMapper.readTree(message);
            GitHubEventDoc doc = new GitHubEventDoc();
            doc.setId(node.get("id").asText());
            doc.setEventType(node.get("type").asText());
            doc.setCreatedAt(node.get("created_at").asText());
            doc.setPublic(node.get("public").asBoolean());

            // Convert nested JSON nodes to Map
            doc.setActor(objectMapper.convertValue(node.get("actor"), Map.class));
            doc.setRepo(objectMapper.convertValue(node.get("repo"), Map.class));
            doc.setPayload(objectMapper.convertValue(node.get("payload"), Map.class));

            gitHubEventRepository.save(doc);


            meterRegistry.counter("kafka.consume.success").increment();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            meterRegistry.counter("kafka.consume.errors").increment();
        }
    }
}
