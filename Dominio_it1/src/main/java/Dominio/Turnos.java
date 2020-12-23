package Dominio;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author joses
 * Interfaz Turnos.
 */
public interface Turnos {
	/**
	 * Variable DF.
	 */
	DateTimeFormatter DF = DateTimeFormatter.ofPattern("HH:mm")
			.localizedBy(new Locale("es-ES"));
	/**
	 * Variable TURNO1COMIDA.
	 */
	LocalTime TURNO_1_COMIDA = LocalTime.parse("13:00", DF);
	/**
	 * Variable TURNO2COMIDA.
	 */
	LocalTime TURNO_2_COMIDA = LocalTime.parse("14:30", DF);
	/**
	 * Variable TURNO3COMIDA.
	 */
	LocalTime TURNO_3_COMIDA = LocalTime.parse("16:00", DF);
	/**
	 *  Variable TURNO1CENA.
	 */
	LocalTime TURNO_1_CENA = LocalTime.parse("20:00", DF);
	/**
	 * Variable TURNO2CENA.
	 */
	LocalTime TURNO_2_CENA = LocalTime.parse("21:30", DF);
	/**
	 * Variable TURNO3CENA.
	 */
	LocalTime TURNO_3_CENA = LocalTime.parse("23:00", DF);
	/**
	 * Variable LIMITETURNO.
	 */
	LocalTime LIMITE_TURNO = LocalTime.parse("00:30", DF);
}