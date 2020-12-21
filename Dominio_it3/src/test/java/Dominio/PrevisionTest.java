package Dominio;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.Test;

public class PrevisionTest {
	
	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm").localizedBy(new Locale("es-ES"));
	static Prevision prev;
	static LocalDateTime pruebaTime;

	@BeforeClass
	public static void SetUpBeforeClass() {
		pruebaTime= LocalDateTime.parse("2020:12:15 14:30", df);
		prev=new Prevision("prueba",5, pruebaTime);
	}

	@Test
	public void testGetIngrediente() {
		String actual=prev.getIngrediente();
		String expected="prueba";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetCantidad() {
		int actual=prev.getCantidad();
		int expected=5;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetFecha() {
		LocalDateTime actual=prev.getFecha();
		LocalDateTime expected=pruebaTime;
		assertEquals(expected, actual);
	}

}
