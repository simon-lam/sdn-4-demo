package com.sdn4demo.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.sdn4demo.entity.SimpleNode;

public interface SimpleNodeRepository extends GraphRepository<SimpleNode> {

}
