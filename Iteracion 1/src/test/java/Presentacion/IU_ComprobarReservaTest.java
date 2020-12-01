package Presentacion;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import Dominio.Camarero;
import Dominio.Reserva;

public class IU_ComprobarReservaTest {

	@Test
	public void test() {
		LinkedList<Reserva> listaReserva=new LinkedList<Reserva> ();
		LinkedList<Camarero> listaCamarero=new LinkedList<Camarero> ();
		IU_ComprobarReserva iuCR=new IU_ComprobarReserva(listaReserva,listaCamarero);
	}

}
