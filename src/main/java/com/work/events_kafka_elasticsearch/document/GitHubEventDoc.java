package com.work.events_kafka_elasticsearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Map;


@Document(indexName = "github-events")
public class GitHubEventDoc {

    @Id
    private String id;
    private String eventType;
    private String createdAt;

    @Field(type = FieldType.Object)
    private Map<String, Object> actor, repo, payload;

    @Field(name = "public")
    private boolean isPublic;

    public GitHubEventDoc() {
    }

    public GitHubEventDoc(String id, String eventType, String createdAt, Map<String, Object> actor, Map<String, Object> repo, Map<String, Object> payload, boolean isPublic) {
        this.id = id;
        this.eventType = eventType;
        this.createdAt = createdAt;
        this.actor = actor;
        this.repo = repo;
        this.payload = payload;
        this.isPublic = isPublic;
    }

    public String getId() {
        return id;
    }

    public String getEventType() {
        return eventType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Map<String, Object> getActor() {
        return actor;
    }

    public Map<String, Object> getRepo() {
        return repo;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setActor(Map<String, Object> actor) {
        this.actor = actor;
    }

    public void setRepo(Map<String, Object> repo) {
        this.repo = repo;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
