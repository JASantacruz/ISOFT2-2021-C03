package Dominio;

import java.util.LinkedList;

public class Cuenta {
	//private double pago;
	private LinkedList<Alimento> entrante;
	private LinkedList<Alimento> primerPlato;
	private LinkedList<Alimento> segundoPlato;
	private LinkedList<Alimento> postre;
	private LinkedList<Bebida> bebida;
	public Cuenta(LinkedList<Alimento> entrante, LinkedList<Alimento> primerPlato,
			LinkedList<Alimento> segundoPlato, LinkedList<Alimento> postre, LinkedList<Bebida> bebida) {
		this.setEntrante(entrante);
		this.setPrimerPlato(primerPlato);
		this.setSegundoPlato(segundoPlato);
		this.setPostre(postre);
		this.setBebida(bebida);
	}
	public LinkedList<Alimento> getEntrante() {
		return entrante;
	}
	public void setEntrante(LinkedList<Alimento> entrante) {
		this.entrante = entrante;
	}
	public LinkedList<Alimento> getPrimerPlato() {
		return primerPlato;
	}
	public void setPrimerPlato(LinkedList<Alimento> primerPlato) {
		this.primerPlato = primerPlato;
	}
	public LinkedList<Alimento> getSegundoPlato() {
		return segundoPlato;
	}
	public void setSegundoPlato(LinkedList<Alimento> segundoPlato) {
		this.segundoPlato = segundoPlato;
	}
	public LinkedList<Alimento> getPostre() {
		return postre;
	}
	public void setPostre(LinkedList<Alimento> postre) {
		this.postre = postre;
	}
	public LinkedList<Bebida> getBebida() {
		return bebida;
	}
	public void setBebida(LinkedList<Bebida> bebida) {
		this.bebida = bebida;
	}
}
