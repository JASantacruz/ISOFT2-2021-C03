package Dominio;

public class Ingrediente {
	private String nombre;
	private int stock;
	private int cantidadNecesaria;
	public Ingrediente(String nombre, int stock, int cantidadNec) {
		this.setNombre(nombre);
		this.setStock(stock);
		this.setCantidadNecesaria(cantidadNec);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getCantidadNecesaria() {
		return cantidadNecesaria;
	}
	public void setCantidadNecesaria(int cantidadNecesaria) {
		this.cantidadNecesaria = cantidadNecesaria;
	}
}
