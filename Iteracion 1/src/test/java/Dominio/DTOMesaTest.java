package Dominio;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Persistencia.CamareroDAO;
import Persistencia.MesaDAO;

public class DTOMesaTest {
	static DTOMesa dtoMesa;
	static MesaDAO mesaDAO;
	static CamareroDAO camareroDAO;
	static Mesa mesa;
	static Camarero camarero;
	@BeforeClass
	public static void setUpBeforeClass() {
		dtoMesa = new DTOMesa();
		mesa= new Mesa(100,10);
		camarero=new Camarero(100,"Alex");
		mesaDAO=new MesaDAO();
		camareroDAO=new CamareroDAO();
		camareroDAO.Create("INSERT INTO Camarero (idCamarero, nombre) VALUES (100,'Alex')");
		mesaDAO.Create("INSERT INTO Mesa (idMesa, idCamarero) VALUES (100,100)");
	}
	@Test
	public void testLeerMesas() {
		LinkedList<Mesa>listaAux=new LinkedList<Mesa>();
		dtoMesa.leerMesas(listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	@Test
	public void testAsignarCamarero() {
		int actual=dtoMesa.asignarCamarero(camarero,100);
		int expected=1;
		assertEquals(expected, actual);
	}
	@Test
	public void testLeerMesa() {
		LinkedList<Mesa>listaAux=new LinkedList<Mesa>();
		dtoMesa.leerMesa(100,listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	@AfterClass
	public static void tearDownAfterClass() {
		camareroDAO.Delete("DELETE FROM Camarero WHERE idCamarero=100");
		mesaDAO.Delete("DELETE FROM Mesa WHERE idMesa=100");
	}

}
