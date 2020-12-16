package Dominio;

public class Alimento {
	private int codigo;
	private String nombre;
	private String tipo;
	public Alimento(int codigo, String nombre, String tipo) {
		this.setCodigo(codigo);
		this.setNombre(nombre);
		this.setTipo(tipo);
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo= tipo;
	}
}
