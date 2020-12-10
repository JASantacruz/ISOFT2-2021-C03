package Presentacion;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import Dominio.Reserva;

public class IU_CancelarReservaTest {

	@Test
	public void test() {
		LinkedList<Reserva> lista=new LinkedList<Reserva>();
		IU_CancelarReserva iuCR=new IU_CancelarReserva(lista);
	}

}
