package com.sdn4demo.test

import org.neo4j.graphdb.GraphDatabaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration

import spock.lang.Specification

import com.graphaware.common.policy.InclusionPolicies
import com.graphaware.common.policy.all.IncludeAllNodes
import com.graphaware.common.policy.all.IncludeAllRelationships
import com.graphaware.common.policy.none.IncludeNoNodeProperties
import com.graphaware.common.policy.none.IncludeNoRelationshipProperties
import com.graphaware.test.unit.GraphUnit
import com.sdn4demo.TestApplication
import com.sdn4demo.server.InProcessNeo4jServer

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = TestApplication.class)
abstract class AbstractIntegrationTest extends AbstractBaseIntegrationTest {

  @Autowired
  private InProcessNeo4jServer neo4jServer

  /**
   * Seems to use GraphUnit, we need Neo4j transactions to commit before making assertions on backing
   * graph DB. This means @Transactional annotation should not be applied and some manual cleanup is necessary
   */
  def cleanup() {
    neo4jServer.clearDatabase()
  }

  protected GraphDatabaseService getGraphDbService() {
    return neo4jServer.graphDatabaseService
  }

  protected void printGraph() {
    GraphUnit.printGraph(getGraphDbService())
  }

  protected void assertSameGraphStructure(String graphCypher) {
    GraphUnit.assertSameGraph(getGraphDbService(), graphCypher, new InclusionPolicies(
        IncludeAllNodes.getInstance(),
        IncludeNoNodeProperties.getInstance(),
        IncludeAllRelationships.getInstance(),
        IncludeNoRelationshipProperties.getInstance()))
  }

  protected void assertSameGraph(String graphCypher) {
    GraphUnit.assertSameGraph(getGraphDbService(), graphCypher)
  }
}

/**
 * Inherit TestExecutionListeners set in @IntegrationTest class
 */
@IntegrationTest
abstract class AbstractBaseIntegrationTest extends Specification {
}