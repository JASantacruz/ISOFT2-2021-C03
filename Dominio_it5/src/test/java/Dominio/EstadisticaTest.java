package Dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class EstadisticaTest {
	
	Estadistica est = new Estadistica("Prueba", "15");
	
	@Test
	public void getNombreTest() {
		assertEquals("Prueba", est.getNombre());
	}
	@Test
	public void setNombreTest() {
		est.setNombre("Prueba_2");
		assertEquals("Prueba_2", est.getNombre());
	}
	
	@Test
	public void getValorTest() {
		assertEquals("15", est.getValor());
	}
	
	@Test
	public void setValorTest() {
		est.setValor("16");
		assertEquals("16", est.getValor());
	}

}
