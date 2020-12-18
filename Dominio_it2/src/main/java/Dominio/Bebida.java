package Dominio;

public class Bebida {
	private int codigo;
	private String nombre;
	private int stock;
	private int precio;
	public Bebida(int codigo, String nombre, int stock, int precio) {
		this.setCodigo(codigo);
		this.setNombre(nombre);
		this.setStock(stock);
		this.setPrecio(precio);
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
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