package Dominio;

import java.time.LocalDateTime;

public class Prevision {

	private String ingrediente;
	private int cantidad;
	private LocalDateTime fecha;
	
	public Prevision(String ingrediente, int cantidad, LocalDateTime fecha) {
		super();
		this.setIngrediente(ingrediente);
		this.setCantidad(cantidad);
		this.setFecha(fecha);
	}
	
	public String getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
}
