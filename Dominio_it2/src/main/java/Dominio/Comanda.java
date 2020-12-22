package Dominio;
import java.util.LinkedList;

import Dominio.Mesa;

public class Comanda {
	private int _idComanda;
	private Mesa _mesa;
	private LinkedList<String> ingredientesComanda=new LinkedList<String>();

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
}