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
	public static void setUpBeforeClass() {
		Date date=new Date(120,3,5,14,30,0);
		reserva=new Reserva(1,4,"ocupado",date,2,"tarde","Alejandro");
		
	}
	
	@Test
	public void testGetId() {
		int expected=1;
		assertEquals(expected,reserva.getId());
	}
	
	@Test
	public void testGetFecha() {
		Date expected=new Date(120,3,5,14,30,0);
		assertEquals(expected,reserva.getFecha());
	}
	
	@Test
	public void testGetMesa() {
		int expected=2;
		assertEquals(expected,reserva.getMesa());
	}
	
	@Test
	public void testGetNumComensales() {
		int expected=4;
		assertEquals(expected,reserva.getNumComensales());
	}
	
	@Test
	public void testGetEstado() {
		String expected="ocupado";
		assertEquals(expected,reserva.getEstado());
	}
	
	@Test
	public void testGetTurno() {
		String expected="tarde";
		assertEquals(expected,reserva.getTurno());
	}
	@Test
	public void testGetNombre() {
		String expected="Alejandro";
		assertEquals(expected,reserva.getNombre());
	}

}
