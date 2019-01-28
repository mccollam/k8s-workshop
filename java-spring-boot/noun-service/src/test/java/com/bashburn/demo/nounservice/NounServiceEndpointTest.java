package com.bashburn.demo.nounservice;

import static org.hamcrest.Matchers.notNullValue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "management.metrics.export.signalfx.access-token=invalid_token")
public class NounServiceEndpointTest {
  @Value("${local.server.port}")
  private int port;

  @Before
  public void setup() {
    RestAssured.baseURI = String.format("http://localhost:%s/api", port);
  }

  @Test
  public void testNounService() {
    RestAssured
        .when()
          .get("noun")
        .then()
          .assertThat()
            .statusCode(200)
            .body(notNullValue());
  }
}
