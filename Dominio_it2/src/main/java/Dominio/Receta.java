package Dominio;

public class Receta {
	
	private String nombreIngrediente;
	private int cantidad;
	
	public Receta(String nombreIngrediente, int cantidad) {
		super();
		this.nombreIngrediente = nombreIngrediente;
		this.cantidad = cantidad;
	}
	
	public String getNombreIngrediente() {
		return nombreIngrediente;
	}
	public void setNombreIngrediente(String nombreIngrediente) {
		this.nombreIngrediente = nombreIngrediente;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
