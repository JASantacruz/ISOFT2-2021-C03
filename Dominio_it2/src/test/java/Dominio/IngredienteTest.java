package Dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class IngredienteTest {

	Ingrediente ig = new Ingrediente("Atun", 15, 1);
	
	@Test
	public void getCantidadNecesariaTest() {
		assertEquals(1, ig.getCantidadNecesaria());
	}
	
	@Test
	public void GetNombreTest() {
		assertEquals("Atun", ig.getNombre());
	}

	@Test
	public void getStockTest() {
		assertEquals(15, ig.getStock());
	}
}
