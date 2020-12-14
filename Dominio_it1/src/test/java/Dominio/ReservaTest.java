package Dominio;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Dominio.Reserva;

public class ReservaTest {

	static Reserva reserva;
	
	@BeforeClass
	public static void SetUpBeforeClass() {
		reserva=new Reserva(500,4,"prueba");
	}
	
	@Test
	public void testGetId() {
		int expected=500;
		assertEquals(expected,reserva.getId());
	}
	
	@Test
	public void testGetNumComensales() {
		int expected=4;
		assertEquals(expected,reserva.getNumComensales());
	}
	
	@Test
	public void testGetNombre() {
		String expected="prueba";
		assertEquals(expected,reserva.getNombre());
	}

}
