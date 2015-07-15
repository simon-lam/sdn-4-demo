package com.sdn4demo.entity;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class SimpleNode {

  @GraphId
  private Long id;

  @Relationship(type="IS_RELATED_TO")
  private List<SimpleRelationship> relationships = new ArrayList<SimpleRelationship>();

  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<SimpleRelationship> getRelationships() {
    return relationships;
  }

  public void setRelationships(List<SimpleRelationship> relationships) {
    this.relationships = relationships;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
