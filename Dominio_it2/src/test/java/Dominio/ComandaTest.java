package Dominio;

import static org.junit.Assert.*;
import java.util.LinkedList;

import org.junit.*;

public class ComandaTest {

	Comanda com;
	Mesa mesa;
	LinkedList<String> ingredientes;
	
	@Before
	public void beforeTest() {
		mesa = new Mesa(105, "Libre");
		com = new Comanda(104, mesa, ingredientes);
	}
	
	
	@Test
	public void testId() {
		assertEquals(104, com.getIdComanda());
	}
	
	@Test
	public void testMesa() {
		Mesa mesaTest = new Mesa(105, "Libre");
		assertEquals(mesaTest.getId(), com.getMesa().getId());
	}
	
	@Test
	public void testLista() {
		LinkedList<String> listaTest = new LinkedList<String>();
		assertEquals(listaTest.size(), 0);
	}

}
