package Dominio;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Dominio.DTOCamarero;
import Persistencia.Agente;

public class DTOCamareroTest {
	static Agente agente;
	static DTOCamarero dtoCamarero;
	
	@BeforeClass
	public static void SetUpBeforeClass() {
		dtoCamarero = new DTOCamarero();
		agente = new Agente();
		try {
			agente.Insert("INSERT INTO Camarero (idCamarero, nombre) VALUES (100,'prueba')");
			agente.Insert("INSERT INTO Aviso (idAviso, descripcion, idCamarero, turno) "
					+ "VALUES (900, 'test',100, '"+DTOReserva.obtenerTurno()+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	public void testLeerCamareroId() {
		LinkedList<Camarero>listaAux=new LinkedList<Camarero>();
		dtoCamarero.leerCamareroId(100,listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	
	@Test
	public void testLeerAvisos() {
		LinkedList<String>listaAux=new LinkedList<String>();
		dtoCamarero.leerAvisos("prueba",listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	
	@AfterClass
	public static void TearDown() {
		try {
			agente.Delete("DELETE FROM Camarero WHERE idCamarero=100");
			agente.Delete("DELETE FROM Aviso WHERE idAviso=900");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
