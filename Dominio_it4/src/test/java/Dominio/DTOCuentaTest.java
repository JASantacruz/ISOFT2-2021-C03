package Dominio;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Persistencia.Agente;

public class DTOCuentaTest {
	
	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm").localizedBy(new Locale("es-ES"));
	static public ExpectedException expectedException = ExpectedException.none();
	static LocalDateTime pruebaTime;
	static DTOCuenta dtoCuenta;
	static Agente agente;
	
	@BeforeClass
	public static void SetUpBeforeClass() {
		pruebaTime=DTOReserva.obtenerTurno();
		dtoCuenta=new DTOCuenta();
		agente=new Agente();
		try {
			agente.Insert("INSERT INTO Mesa (idMesa, estado) VALUES (900, 'ocupada')");
			agente.Insert("INSERT INTO Comanda (idComanda, idMesa, turno) VALUES(900, 900, '"+pruebaTime+"')");
			agente.Insert("INSERT INTO Carta (codigo, nombre, tipo, precio) VALUES (900, 'test', 'Postre', 9)");
			agente.Insert("INSERT INTO Pedido (comanda, codigo, cantidad) VALUES (900, 900, 2)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCalcularPrecio() {
		double actual=dtoCuenta.calcularPrecio("900");
		double expected=18.0;
		assertEquals(expected, actual, 0.001);
	}
	
	@Test
	public void testDevolverCuenta() {
		String actual=dtoCuenta.devolverCuenta("900").getPostre().get(0).getNombre();
		String expected="test";
		assertEquals(expected, actual);
	}
	
	@AfterClass
	public static void TearDownAfterClass() {
		try {
			agente.Delete("DELETE FROM Mesa WHERE idMesa=900");
			agente.Delete("DELETE FROM Comanda WHERE idComanda=900");
			agente.Delete("DELETE FROM Carta WHERE codigo=900");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
