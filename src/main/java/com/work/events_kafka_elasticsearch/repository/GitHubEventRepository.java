package com.work.events_kafka_elasticsearch.repository;

import com.work.events_kafka_elasticsearch.document.GitHubEventDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitHubEventRepository extends ElasticsearchRepository<GitHubEventDoc, String> {
}
