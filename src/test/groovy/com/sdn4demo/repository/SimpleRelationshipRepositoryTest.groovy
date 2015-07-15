package com.sdn4demo.repository

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue

import org.springframework.beans.factory.annotation.Autowired

import com.sdn4demo.entity.SimpleNode
import com.sdn4demo.entity.SimpleRelationship
import com.sdn4demo.test.AbstractIntegrationTest


public class SimpleRelationshipRepositoryTest extends AbstractIntegrationTest {

  @Autowired private SimpleNodeRepository nodeRepository
  @Autowired private SimpleRelationshipRepository relationshipRepository

  def "Test update operation on relationship"() {
    setup:
    // Set up nodes
    SimpleNode parent = new SimpleNode(name: "parent")
    SimpleNode child = new SimpleNode(name: "child")
    nodeRepository.save([parent, child])
    assert parent.id != null
    assert child.id != null

    // Setup baseline relationship
    SimpleRelationship rel = new SimpleRelationship(parent: parent, child: child, property: "Some value")
    parent.relationships.add(rel)
    relationshipRepository.save(rel)
    assert rel.id != null

    printGraph()

    when:
    // Now create a third node
    SimpleNode other = new SimpleNode(name: "other")
    nodeRepository.save(other)
    other.id != null

    rel.child = other
    rel.property = "New value"
    relationshipRepository.save(rel)

    then:
    printGraph()
    assertSameGraph("CREATE (parent:SimpleNode { name: 'parent' }), " +
      "(child:SimpleNode { name: 'other' }), " +
      "(parent)-[:IS_RELATED_TO { property: 'New Value' }]->(child)")
  }

}
