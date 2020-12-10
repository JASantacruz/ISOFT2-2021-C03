package Dominio;

import static org.junit.Assert.*;

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

import Persistencia.CamareroDAO;
import Persistencia.MesaDAO;
import Persistencia.ReservaDAO;

public class DTOReservaTest {
	
	static DTOReserva dtoReserva;
	static MesaDAO mesaDAO;
	static CamareroDAO camareroDAO;
	static ReservaDAO reservaDAO;

	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm").localizedBy(new Locale("es-ES"));
	
	@BeforeClass
	public static void setUpBeforeClass() {
		dtoReserva=new DTOReserva();
		mesaDAO = new MesaDAO();
		camareroDAO=new CamareroDAO();
		camareroDAO.Create("INSERT INTO Camarero (idCamarero, nombre) VALUES (100,'Alex')");
		mesaDAO.Create("INSERT INTO Mesa (idMesa, idCamarero) VALUES (100,100)");
		
		LocalTime hm = LocalTime.parse("14:30", df);
		LocalDate s = LocalDate.now();
		LocalDateTime fecha = LocalDateTime.of(s, hm);
		reservaDAO.Create("INSERT INTO Reserva (idReserva, num_comensales,estado,fecha,idMesa,turno, nombre)"
				+ " VALUES (100,1,'ocupada','2020-12-12 14:30:00',3,'tarde','Julian')");
		reservaDAO.Create("INSERT INTO Reserva (idReserva, num_comensales,estado,fecha,idMesa,turno, nombre)"
				+ " VALUES (101,1,'ocupada','2020-12-12 14:30:00',3,'tarde','Julian')");
	}
	@Test
	public void testLeerReservas() {
		LinkedList<Reserva>listaAux=new LinkedList<Reserva>();
		dtoReserva.leerReservas(listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	@Test
	public void testAnadirReserva() {
		LocalTime hm = LocalTime.parse("14:30", df);
		LocalDate s = LocalDate.now();
		LocalDateTime fecha = LocalDateTime.of(s, hm);
		int actual=dtoReserva.anadirReserva("2","ocupada",fecha,"100","tarde","Alex");
		int expected=1;
		assertEquals(expected, actual);
	}
	@Test
	public void testBorrarReserva() {
		int actual=dtoReserva.borrarReserva("100");
		int expected=1;
		assertEquals(expected, actual);
	}
	@Test
	public void testCambiarEstado() {
		int actual=dtoReserva.cambiarEstado(101,"libre");
		int expected=1;
		assertEquals(expected, actual);
	}
	@Test
	public void testLeerReserva() {
		LinkedList<Reserva>listaAux=new LinkedList<Reserva>();
		dtoReserva.leerReserva(101, listaAux);
		int actual=listaAux.size();
		int expected=1;
		assertEquals(expected, actual);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		LinkedList<Reserva>listaAux=new LinkedList<Reserva>();
		dtoReserva.leerReservas(listaAux);
		camareroDAO.Delete("DELETE FROM Camarero WHERE idCamarero=100");
		mesaDAO.Delete("DELETE FROM Mesa WHERE idMesa=100");
		reservaDAO.Delete("DELETE FROM Reserva WHERE idMesa="+listaAux.get(listaAux.size()-1).getId());
		reservaDAO.Delete("DELETE FROM Reserva WHERE idMesa=101");
	}
}
