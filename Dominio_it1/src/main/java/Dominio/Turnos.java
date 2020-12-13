package Dominio;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public interface Turnos {
	DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm").localizedBy(new Locale("es-ES"));
	LocalTime Turno1Comida=LocalTime.parse("13:00", df);
	LocalTime Turno2Comida=LocalTime.parse("14:30", df);
	LocalTime Turno3Comida=LocalTime.parse("16:00", df);
	LocalTime Turno1Cena=LocalTime.parse("20:00", df);
	LocalTime Turno2Cena=LocalTime.parse("21:30", df);
	LocalTime Turno3Cena=LocalTime.parse("23:00", df);
	LocalTime limiteTurno=LocalTime.parse("00:30", df);
}
