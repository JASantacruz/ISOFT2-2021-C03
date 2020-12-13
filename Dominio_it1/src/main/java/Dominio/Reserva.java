package Dominio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Reserva implements Estado{

	private int id;
	private int numComensales;
	private String estado;
	private Date fecha;
	private int mesa; 
	private String turno;
	private String nombre;
	
	public Reserva(final int id, int numComensales, String estado, Date fecha, int mesa, String turno, String nombre) {
		super();
		this.setId(id);
		this.setNumComensales(numComensales);
		this.setEstado(estado);
		this.setFecha(fecha);
		this.setMesa(mesa);
		this.setTurno(turno);
		this.setNombre(nombre);

	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getMesa() {
		return mesa;
	}
	
	public void setMesa(int mesa) {
		this.mesa = mesa;
	}
	
	public int getNumComensales() {
		return numComensales;
	}
	
	public void setNumComensales(int numComensales) {
		this.numComensales = numComensales;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	

}
