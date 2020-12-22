package Dominio;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Locale;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

//import Dominio.DTOReserva;
//import Dominio_it1.Reserva;
import Persistencia.Agente;

public class DTOReservaTest {
	
	static DTOReserva dtoReserva;
	static Agente agente;
	static LocalDateTime fecha;
	static LocalDateTime turno;

	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm").localizedBy(new Locale("es-ES"));
	
	@BeforeClass
	public static void SetUpBeforeClass() {
		dtoReserva=new DTOReserva();
		agente= new Agente();
		
		LocalTime hm = LocalTime.parse("14:30", df);
		LocalDate s = LocalDate.now();
		fecha = LocalDateTime.of(s, hm);
		turno=dtoReserva.obtenerTurno();
		try {
			agente.Insert("INSERT INTO Mesa (idMesa, estado) VALUES (100,'libre')");
			agente.Insert("INSERT INTO Mesa (idMesa, estado) VALUES (102,'reservada')");
			agente.Insert("INSERT INTO Mesa (idMesa, estado) VALUES (101,'pidiendo')");
			agente.Insert("INSERT INTO Camarero (idCamarero, nombre) VALUES (240,'pidiendo')");
			agente.Insert("INSERT INTO Reserva (idReserva, num_comensales,tiempoReservada, nombre, restaurante) VALUES (200,4,'2020-12-12 14:30:00','Julian', 1)");
			agente.Insert("INSERT INTO Reserva (idReserva, num_comensales,tiempoReservada, nombre, restaurante) VALUES (201,2,'2020-12-12 13:00:00','Julian', 1)");
			agente.Insert("INSERT INTO Reserva (idReserva, num_comensales,tiempoReservada, nombre, restaurante) VALUES (202,4,'2021-12-12 14:30:00','prueba', 1)");
			agente.Insert("INSERT INTO Reserva (idReserva, num_comensales,tiempoReservada, nombre, restaurante) VALUES (204,4,'"+turno+"','prueba', 1)");
			agente.Insert("INSERT INTO Reserva (idReserva, num_comensales,tiempoReservada, nombre, restaurante) VALUES (205,4,'2021-12-12 14:30:00','prueba', 1)");
			agente.Insert("INSERT INTO Reserva (idReserva, num_comensales,tiempoReservada, nombre, restaurante) VALUES (206,4,'2021-12-12 14:30:00','prueba', 1)");
			agente.Insert("INSERT INTO Reserva (idReserva, num_comensales,tiempoReservada, nombre, restaurante) VALUES (207,4,'2021-12-12 14:30:00','prueba', 1)");
			agente.Insert("INSERT INTO MesaCamareroReserva (idMesa, idCamarero,idReserva, turno)"
					+ " VALUES (101,240,205,'"+turno+"')");
			agente.Insert("INSERT INTO MesaCamareroReserva (idMesa, idCamarero,idReserva, turno)"
					+ " VALUES (102,240,207,'"+turno+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testLeerReservas() {
		LinkedList<Integer>listaAux=new LinkedList<Integer>();
		dtoReserva.leerReservas(listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	@Test
	public void testLeerTodasReservas() {
		LinkedList<Integer>listaAux=new LinkedList<Integer>();
		dtoReserva.leerTodasReservas(listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	@Test
	public void testAnadirReserva() {
		int actual=dtoReserva.anadirReserva("2",fecha,"prueba", "3");
		int expected=1;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLeerReservasAux() {
		LinkedList<Integer>listaAux=new LinkedList<Integer>();
		dtoReserva.leerReservasAux(101, turno,listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	@Test
	public void testCambiarTiempoEstado() {
		LinkedList<Integer>listaAux=new LinkedList<Integer>();
		int actual=dtoReserva.cambiarTiempoEstado("101","pidiendo");
		int expected=0;
		assertNotEquals(expected, actual);
	}

	@Test
	public void testLeerReserva() {
		LinkedList<Reserva>listaAux=new LinkedList<Reserva>();
		dtoReserva.leerReserva("206", listaAux);
		int actual=listaAux.size();
		int expected=1;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testObtenerFechaReserva() {
		LinkedList<Reserva>listaAux=new LinkedList<Reserva>();
		LocalDateTime actual=dtoReserva.obtenerFechaReserva("205");
		assertNotNull(actual);
	}
	
	@Test
	public void testComprobarClienteTardio() {
		dtoReserva.comprobarClienteTardio();
	}

	@AfterClass
	public static void TearDownAfterClass() {
		try {
			ResultSet rs=agente.Read("SELECT idReserva FROM Reserva WHERE num_comensales=2 AND "+ "tiempoReservada='"+fecha+"' AND nombre='prueba'");
			if(rs.next()) agente.Delete("DELETE FROM Reserva WHERE idReserva="+rs.getInt(1));
			agente.Delete("DELETE FROM Reserva WHERE idReserva=200");
			agente.Delete("DELETE FROM Reserva WHERE idReserva=201");
			agente.Delete("DELETE FROM Reserva WHERE idReserva=202");
			agente.Delete("DELETE FROM Reserva WHERE idReserva=204");
			agente.Delete("DELETE FROM Reserva WHERE idReserva=205");
			agente.Delete("DELETE FROM Reserva WHERE idReserva=206");
			agente.Delete("DELETE FROM Reserva WHERE idReserva=207");
			agente.Delete("DELETE FROM Camarero WHERE idCamarero=240");
			agente.Delete("DELETE FROM Mesa WHERE idMesa=100");
			agente.Delete("DELETE FROM Mesa WHERE idMesa=101");
			agente.Delete("DELETE FROM Mesa WHERE idMesa=102");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
