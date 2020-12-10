package Dominio;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Dominio.DTOCamarero;

public class DTOCamareroTest {

	static DTOCamarero dtoCamarero;
	@BeforeClass
	public static void setUpBeforeClass() {
		dtoCamarero = new DTOCamarero();
	}
	@Test
	public void testLeerCamareros() {
		LinkedList<Camarero>listaAux=new LinkedList<Camarero>();
		dtoCamarero.leerCamareros(listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	@Test
	public void testLeerCamarero() {
		LinkedList<Camarero>listaAux=new LinkedList<Camarero>();
		dtoCamarero.leerCamarero(1,listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
}
