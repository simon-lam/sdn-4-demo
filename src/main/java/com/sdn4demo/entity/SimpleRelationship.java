package com.sdn4demo.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity
public class SimpleRelationship {

  private Long id;

  private String property;

  @StartNode
  private SimpleNode parent;

  @EndNode
  private SimpleNode child;

  public SimpleRelationship() { }

  public SimpleRelationship(SimpleNode parent, SimpleNode child, String percentage) {
    this.parent = parent;
    this.child = child;
    this.property = percentage;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public SimpleNode getParent() {
    return parent;
  }

  public void setParent(SimpleNode parent) {
    this.parent = parent;
  }

  public SimpleNode getChild() {
    return child;
  }

  public void setChild(SimpleNode child) {
    this.child = child;
  }

}