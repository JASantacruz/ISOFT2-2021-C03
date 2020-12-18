package Dominio;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Persistencia.Agente;

public class DTOMesaTest {
	static Agente agente;
	static DTOMesa dtoMesa;
	static Mesa mesa;
	static Camarero camarero;
	@BeforeClass
	public static void SetUpBeforeClass() {
		agente = new Agente();
		dtoMesa = new DTOMesa();
		mesa= new Mesa(100,"libre");
		camarero=new Camarero(100,"prueba");
		try {
			LocalDateTime turno=DTOReserva.obtenerTurno();
			agente.Insert("INSERT INTO Camarero (idCamarero, nombre) VALUES (100,'prueba')");
			agente.Insert("INSERT INTO Reserva (idReserva, num_comensales,tiempoReservada, nombre)"
					+ " VALUES (200,4,'2020-12-12 14:30:00','pruebaNombre')");
			agente.Insert("INSERT INTO Mesa (idMesa, estado) VALUES (100,'libre')");
			agente.Insert("INSERT INTO Mesa (idMesa, estado) VALUES (101,'ocupada')");
			agente.Insert("INSERT INTO Mesa (idMesa, estado) VALUES (102,'ocupada')");
			agente.Insert("INSERT INTO Mesa (idMesa, estado) VALUES (103,'pidiendo')");
			agente.Insert("INSERT INTO MesaCamareroReserva (idMesa, idCamarero, idReserva, turno) "
					+ "VALUES (100,null,200,'"+turno+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	public void testLeerMesasOcupadas() {
		LinkedList<Mesa>listaAux=new LinkedList<Mesa>();
		dtoMesa.leerMesasOcupadas(listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	@Test
	public void testLeerMesasPorCamarero() {
		LinkedList<Mesa>listaAux=new LinkedList<Mesa>();
		dtoMesa.leerMesasPorCamarero(listaAux,camarero.getNombre());
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	@Test
	public void testAsignarCamarero() {
		int actual=dtoMesa.asignarCamarero(camarero,"200");
		int expected=1;
		assertEquals(expected, actual);
	}
	@Test
	public void testCambiarMesaOcupada() {
		int actual=dtoMesa.cambiarMesaOcupada("200");
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
	@Test
	public void testConsultarEstadoMesa() {
		String actual=dtoMesa.consultarEstadoMesa("101");
		String expected="ocupada";
		assertEquals(expected, actual);
	}
	@Test
	public void testCambiarEstado() {
		int actual=dtoMesa.cambiarEstado("102","pidiendo");
		int expected=1;
		assertEquals(expected, actual);
	}
	@Test
	public void testSecuenciarEstado() {
		String actual=dtoMesa.secuenciarEstado("ocupada");
		String expected="pidiendo";
		assertEquals(expected, actual);
	}
	@Test
	public void testEstadoCorrecto() {
		boolean actual=dtoMesa.estadoCorrecto("103");
		boolean expected=true;
		assertEquals(expected, actual);
	}
	@Test
	public void testActualizarEstadoMesasPorTurno() {
		dtoMesa.actualizarEstadoMesasPorTurno();
	}
	
	@AfterClass
	public static void TearDown() {
		try {
			agente.Delete("DELETE FROM Camarero WHERE idCamarero=100");
			agente.Delete("DELETE FROM Mesa WHERE idMesa=100");
			agente.Delete("DELETE FROM Mesa WHERE idMesa=101");
			agente.Delete("DELETE FROM Mesa WHERE idMesa=102");
			agente.Delete("DELETE FROM Mesa WHERE idMesa=103");
			agente.Delete("DELETE FROM Reserva WHERE idReserva=200");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
