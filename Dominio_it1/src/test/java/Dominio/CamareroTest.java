package Dominio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Dominio.Camarero;

public class CamareroTest {
	
	Camarero camarero;
	@Before	
	public void before() {
		camarero=new Camarero(500,"Arturo");
	}
	@Test
	public void testGetIdCamarero() {
		int expected=500;
		assertEquals(expected,camarero.getIdCamarero());
	}

	@Test
	public void testGetNombre() {
		String expected="Arturo";
		assertEquals(expected,camarero.getNombre());
	}
}
