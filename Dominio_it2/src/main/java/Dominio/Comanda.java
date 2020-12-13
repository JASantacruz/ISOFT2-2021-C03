package Dominio;
import java.util.LinkedList;

import Dominio.Mesa;

public class Comanda {
	private int _idComanda;
	private Mesa _mesa;
	private LinkedList<String> ingredientesComanda=new LinkedList<String>();
//	private String _primerPlato;
//	private String _segundoPlato;
//	private String _postre;
//	private String _bebida;

	public Comanda(int idComanda, Mesa mesa,LinkedList<String> ingredientesComanda) {
		this.setIdComanda(idComanda);
		this.setMesa(mesa);
		this.setIngredientesComanda(ingredientesComanda);
	}

	public LinkedList<String> getIngredientesComanda() {
		return ingredientesComanda;
	}

	public void setIngredientesComanda(LinkedList<String> ingredientesComanda) {
		this.ingredientesComanda = ingredientesComanda;
	}

	public Comanda() {
		throw new UnsupportedOperationException();
	}

	public int getIdComanda() {
		return this._idComanda;
	}

	public void setIdComanda(int aIdComanda) {
		this._idComanda = aIdComanda;
	}

	public Mesa getMesa() {
		return this._mesa;
	}

	public void setMesa(Mesa aMesa) {
		this._mesa = aMesa;
	}

//	public String getPrimerPlato() {
//		return this._primerPlato;
//	}
//
//	public void setPrimerPlato(String aPrimerPlato) {
//		this._primerPlato = aPrimerPlato;
//	}
//
//	public String getSegundoPlato() {
//		return this._segundoPlato;
//	}
//
//	public void setSegundoPlato(String aSegundoPlato) {
//		this._segundoPlato = aSegundoPlato;
//	}
//
//	public String getPostre() {
//		return this._postre;
//	}
//
//	public void setPostre(String aPostre) {
//		this._postre = aPostre;
//	}
//
//	public String getBebida() {
//		return this._bebida;
//	}
//
//	public void setBebida(String aBebida) {
//		this._bebida = aBebida;
//	}
}