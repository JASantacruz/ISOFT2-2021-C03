package Dominio;

import static org.junit.Assert.*;

import org.junit.*;

public class BebidaTest {

	Bebida be;
	
	
	@Before
	public void beforeTest() {
		be = new Bebida(1504,"Nestea", 15);
	}
	
	@Test
	public void testCodigo() {
		assertEquals(1504, be.getCodigo());
	}
	
	@Test
	public void testNombre() {
		assertEquals("Nestea", be.getNombre());
	}

	@Test
	public void testStock() {
		assertEquals(15, be.getStock());
	}
}
