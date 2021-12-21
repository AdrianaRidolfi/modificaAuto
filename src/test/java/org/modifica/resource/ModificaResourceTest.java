package org.modifica.resource;

import static io.restassured.RestAssured.given;

import javax.inject.Inject;

import org.modifica.db.DBMongo;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@TestHTTPEndpoint(ModificaResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ModificaResourceTest {
	
	@Inject
	DBMongo garage;
	
	
	@Test
	public void testModificaAuto() {
		String url = "/auto/" + 0 +"/modifica-colore/testMA";
		given()
		.when().patch(url)
		.then().statusCode(200);
	}

}
