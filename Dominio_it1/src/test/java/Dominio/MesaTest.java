package Dominio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Dominio.Mesa;

public class MesaTest {

	Mesa mesa;
	
	@Before
	public void before() {
		mesa=new Mesa(500,"libre");
	}
	
	@Test
	public void testGetId() {
		int expected=500;
		assertEquals(expected,mesa.getId());
	}
	
	@Test
	public void testGetContador() {
		String expected="libre";
		assertEquals(expected,mesa.getEstado());
	}
}
