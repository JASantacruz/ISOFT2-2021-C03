package Dominio;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class RepeticionTest {
	
	static Repeticion rep;
	
	@BeforeClass
	public static void SetUpBeforeClass() {
		rep=new Repeticion("prueba",5);
	}

	@Test
	public void testGetNombre() {
		String actual=rep.getNombre();
		String expected="prueba";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetRepeticiones() {
		int actual=rep.getRepeticiones();
		int expected=5;
		assertEquals(expected, actual);
	}

}
