package Dominio;

public class Postre {
	private int codigo;
	private String nombre;
	private int stock;
	public Postre(int codigo, String nombre, int stock) {
		this.setCodigo(codigo);
		this.setNombre(nombre);
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
