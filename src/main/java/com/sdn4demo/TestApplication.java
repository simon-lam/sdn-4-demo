package com.sdn4demo;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.sdn4demo.server.InProcessNeo4jServer;


@SpringBootApplication
public class TestApplication {

  @EnableTransactionManagement
  @EnableNeo4jRepositories(basePackages = "com.sdn4demo.repository")
  @Configuration
  static class Neo4jConfig extends Neo4jConfiguration {

    @Bean
    public Neo4jServer neo4jServer() {
      return new InProcessNeo4jServer();
    }

    @Override
    public SessionFactory getSessionFactory() {
      return new SessionFactory("com.sdn4demo.entity");
    }

  }
}
