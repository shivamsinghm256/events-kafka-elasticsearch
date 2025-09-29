# Events Kafka Elasticsearch

A Spring Boot application that consumes **GitHub Events** from Kafka and indexes them into **Elasticsearch** for real-time search and analytics.

---

## 🚀 Features
- Kafka consumer for GitHub events
- Stores events into Elasticsearch
- Flexible schema using `JsonNode`/`Map` for dynamic payloads
- Configurable via `application.properties`

---

## 🛠 Tech Stack
- **Java 17+**
- **Spring Boot 3**
- **Apache Kafka**
- **Elasticsearch**
- **Spring Data Elasticsearch**
- **Docker (optional, for Kafka & Elasticsearch setup)**

---

## ⚙️ Setup & Run

### 1️⃣ Start Dependencies (Kafka + Elasticsearch + Kibana)
You can follow the link below. </br>
For Kafka -> https://kafka.apache.org/quickstart
For Elastic search -> https://www.elastic.co/docs/deploy-manage/deploy/self-managed/install-elasticsearch-docker-basic

### 2️⃣ Configure Application
Edit `src/main/resources/application.properties:`

```
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=github-consumer-group 
github.topic=github-events
spring.elasticsearch.uris=http://localhost:9200 
```

### 3️⃣ Run the Application
`./mvnw spring-boot:run`

### 📂 Project Structure
```
src/main/java/com/work/events_kafka_elasticsearch
├── config        # Elasticsearch config
├── consumer      # Kafka consumer
├── document      # Elasticsearch document mapping
├── repository    # Spring Data Elasticsearch repo
└── EventsKafkaElasticsearchApplication.java
```
### 📊 Elasticsearch Index

The application stores events in the index: **github-events**

Fields:
``` 
id
eventType
createdAt
actor
repo
payload
isPublic
```
### 🔮 Future Improvements
- Add REST API to query events
-  Grafana dashboards for monitoring
- Integrate with Logstash / Beats for pipeline enrichment