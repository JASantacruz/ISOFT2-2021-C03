package Dominio;

import static org.junit.Assert.*;

import org.junit.*;

public class AlimentoTest {

	Alimento al;
	@Before
	public void beforeTest() {
		al = new Alimento(1507, "Patatas bravas", "Entrante");
	}
	
	@Test
	public void testCodigo() {
		assertEquals(1507, al.getCodigo());
	}
	
	@Test
	public void testNombre() {
		assertEquals("Patatas bravas", al.getNombre());
	}

	@Test
	public void testTipo() {
		assertEquals("Entrante", al.getTipo());
	}
	
}
