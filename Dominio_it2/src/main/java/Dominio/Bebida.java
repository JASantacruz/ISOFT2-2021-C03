package Dominio;

public class Bebida {
	private int codigo;
	private String nombre;
	private int stock;
	public Bebida( String nombre, int codigo, int stock) {
		this.setNombre(nombre);
		this.setCodigo(codigo);
		this.setStock(stock);
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
}