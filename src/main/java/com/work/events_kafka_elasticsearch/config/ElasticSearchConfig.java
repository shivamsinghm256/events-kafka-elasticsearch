package com.work.events_kafka_elasticsearch.config;

import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.net.ssl.SSLContext;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.work.events_kafka_elasticsearch.repository")
public class ElasticSearchConfig extends ElasticsearchConfiguration {
    @Value("${spring.data.elasticsearch.username}")
    private String username;
    @Value("${spring.data.elasticsearch.password}")
    private String password;
    @Override
    @Bean
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedToLocalhost()
                .usingSsl(sslContext())
                .withBasicAuth(username, password)
                .build();
    }

    private static SSLContext sslContext(){
        try {
            return new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
