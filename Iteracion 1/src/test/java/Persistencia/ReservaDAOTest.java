package Persistencia;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Dominio.Reserva;

public class ReservaDAOTest {
	static ReservaDAO reservaDAO;
	public ExpectedException expectedException = ExpectedException.none();//no sabemos si se pone rule antes
	@BeforeClass
	public static void setUpBeforeClass() {
		//date=new Date(120,3,5,14,30,0);
		reservaDAO=new ReservaDAO();
		reservaDAO.Create("INSERT INTO Reserva (idReserva, num_comensales,estado,fecha,idMesa,turno, nombre)"
				+ " VALUES (100,1,'ocupada','2020-12-12 14:30:00',3,'tarde','Julian')");
		reservaDAO.Create("INSERT INTO Reserva (idReserva, num_comensales,estado,fecha,idMesa,turno, nombre)"
				+ " VALUES (99,1,'ocupada','2020-12-12 14:30:00',3,'tarde','Julian')");
		//reservaDAO.Create("INSERT INTO Reserva (id, idCamarero) VALUES (97,1)");
	}
	@Test
	public void testCreateException() {
		//insertar una reserva que ya existe
		expectedException.expect(SQLException.class);
		reservaDAO.Create("INSERT INTO Reserva (idReserva, num_comensales,estado,fecha,idMesa,turno, nombre)"
				+ " VALUES (100,1,'ocupada','2020-12-12 14:30:00',3,'tarde','Julian')");
	}
	@Test
	public void testUpdate() {
		int actual=reservaDAO.Update("UPDATE Reserva SET nombre='Pepe' WHERE idReserva=100");
		int expected=1;
		assertEquals(expected, actual);
	}
	@Test
	public void testUpdateException() {
		expectedException.expect(SQLException.class);
		reservaDAO.Update("UPDATE Reserva SET idMesa=130 WHERE idReserva=100");
	}
	@Test
	public void testDelete() {
		int actual=reservaDAO.Delete("DELETE From Reserva WHERE idReserva=99");
		int expected=1;
		assertEquals(expected, actual);
	}
	@Test
	public void testDeleteException() {
		expectedException.expect(SQLException.class);
		reservaDAO.Delete("DELETE From Reserva WHERE idReserva=fail");
	}
	@Test
	public void testRead() {
		LinkedList<Reserva>listaAux=new LinkedList<Reserva>();
		reservaDAO.Read("SELECT * FROM Reserva",listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	@Test
	public void testReadException() {
		LinkedList<Reserva>listaAux=new LinkedList<Reserva>();
		expectedException.expect(SQLException.class);
		reservaDAO.Read("SELECT fail From Reserva",listaAux);
	}
	@AfterClass
	public static void tearDownAfterClass() {
		reservaDAO.Delete("DELETE From Reserva WHERE idReserva=100");
		//reservaDAO.Delete("DELETE From Reserva WHERE idReserva=97");
	}
}