package Dominio;

import static org.junit.Assert.*;

import org.junit.*;

public class RecetaTest {

	
	Receta rec = new Receta("Cebolla", 3);
	
	
	@Test
	public void getNombreTest() {
		assertEquals("Cebolla", rec.getNombreIngrediente());
	}
	
	@Test
	public void setNombreTest() {
		rec.setNombreIngrediente("Patata");
	}

	@Test
	public void getCantidadTest() {
		assertEquals(3, rec.getCantidad());
	}

	@Test
	public void setCantidadTest() {
		rec.setCantidad(1);
	}
}
