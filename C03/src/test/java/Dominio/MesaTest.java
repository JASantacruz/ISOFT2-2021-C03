package Dominio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Dominio.Mesa;

public class MesaTest {

	Mesa mesa;
	
	@Before
	public void before() {
		mesa=new Mesa(1,3);
	}
	
	@Test
	public void testGetId() {
		int expected=1;
		assertEquals(expected,mesa.getId());
	}
	
	@Test
	public void testGetContador() {
		int expected=3;
		assertEquals(expected,mesa.getContador());
	}
}
