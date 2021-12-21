package org.modifica.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class DBInterfaceTest {

	@Inject
	DBMongo garage;
	
	@Test
	void testModificaAuto() {
		garage.modificaAuto(0, "colore", "testMA");
		assertEquals(garage.getAuto(0).getColore(),"testMA");
		
	}
}
