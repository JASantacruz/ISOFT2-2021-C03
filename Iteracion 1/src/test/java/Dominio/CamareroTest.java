package Dominio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Dominio.Camarero;

public class CamareroTest {
	
	static Camarero camarero;
	@BeforeClass
	public static void setUpBeforeClass() {
		camarero=new Camarero(1,"Arturo");
	}
	@Test
	public void testGetIdCamarero() {
		int expected=1;
		assertEquals(expected,camarero.getIdCamarero());
	}

	@Test
	public void testGetNombre() {
		String expected="Arturo";
		assertEquals(expected,camarero.getNombre());
	}
}
