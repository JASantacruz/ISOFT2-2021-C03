package Dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;

import Persistencia.Agente;

public class DTOComanda {
	public static Agente agente = new Agente();

	public static LinkedList<String> comprobarStock(LinkedList<String> elementosComanda) {
		LinkedList<String> cartaSinStock=new LinkedList<String>();
		LinkedList<String> elementosComprobados=new LinkedList<String>();
		LinkedList<Ingrediente> ingredientesNecesarios=new LinkedList<Ingrediente>();
		ResultSet resultStock;
		ResultSet resultNecesita;
		ResultSet resultTipo;
		String consultaStock="";
		String consultaIngredienteNecesario="";
		String consultaTipoCarta="";
		String tipo="";
		int stock=-1;
		int cantidadNecesaria=-1;
		int contadorElementoCarta=0;
		int indiceIngrediente;
		String nombreIngrediente="";
		Ingrediente ingrediente;
		try {
			for(String elementoComanda: elementosComanda) {
				if(!cartaSinStock.contains(elementoComanda)) {
					consultaTipoCarta="SELECT tipo FROM Carta WHERE nombre='"+elementoComanda+"'";
					resultTipo=agente.Read(consultaTipoCarta);
					if(resultTipo.next()) {
						tipo=resultTipo.getString(1);
						if(tipo.equals("Bebida")) {
							int contadorBebida=0;
							for(String bebidaComprobacion: elementosComanda) {
								if(bebidaComprobacion.equals(elementoComanda)) {
									contadorBebida++;
								}
							}
							consultaStock="SELECT stock FROM Bebida WHERE nombre='"+elementoComanda+"'";
							resultStock=agente.Read(consultaStock);
							if(resultStock.next()) stock=resultStock.getInt(1);
							if(contadorBebida>stock) {
								cartaSinStock.add(elementoComanda);
							}
						}else {
							consultaIngredienteNecesario="SELECT nombreIngrediente, cantidad "
									+ "FROM Necesita WHERE codigoCarta=(SELECT codigo FROM Carta WHERE nombre='"+elementoComanda+"')";
							resultNecesita=agente.Read(consultaIngredienteNecesario);
							while(resultNecesita.next()) {//todos los ingredientes de cada plato
								nombreIngrediente=resultNecesita.getString(1);
								cantidadNecesaria=resultNecesita.getInt(2);
								if(!ingredienteComprobado(nombreIngrediente, ingredientesNecesarios)) {
									consultaStock="SELECT stock FROM Ingrediente WHERE nombre='"+nombreIngrediente+"'";
									resultStock=agente.Read(consultaStock);
									if(resultStock.next()) {
										stock=resultStock.getInt(1);
										ingrediente=new Ingrediente(nombreIngrediente, stock,cantidadNecesaria);
										ingredientesNecesarios.add(ingrediente);
										if(ingrediente.getStock()<ingrediente.getCantidadNecesaria()) {
											cartaSinStock.add(elementoComanda);
										}
									}
								}else {
									indiceIngrediente=indiceListaIngrediente(nombreIngrediente,ingredientesNecesarios);
									int cantidadNecesariaNueva=ingredientesNecesarios.get(indiceIngrediente).getCantidadNecesaria()+cantidadNecesaria;
									ingredientesNecesarios.get(indiceIngrediente).setCantidadNecesaria(cantidadNecesariaNueva);
									if(ingredientesNecesarios.get(indiceIngrediente).getStock()<ingredientesNecesarios.get(indiceIngrediente).getCantidadNecesaria()) {
										cartaSinStock.add(elementoComanda);
									}
								}
							}

						}
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cartaSinStock;
	}
	public static boolean ingredienteComprobado(String ingrediente,LinkedList<Ingrediente> ingredientesComprobados) {
		for(Ingrediente ingredienteComprobado: ingredientesComprobados) {
			if(ingredienteComprobado.getNombre().equals(ingrediente)) {
				return true;
			}
		}
		return false;
	}
	public static int indiceListaIngrediente(String ingrediente,LinkedList<Ingrediente> ingredientesComprobados) {
		int indice=-1;
		for(int i=0;i<ingredientesComprobados.size();i++) {
			if(ingredientesComprobados.get(i).getNombre().equals(ingrediente)) {
				indice=i;
			}
		}
		return indice;
	}
	public static void guardarComanda(String idMesa, LinkedList<String> comanda) {
		ResultSet resultIdElemento;
		ResultSet resultComanda;
		int idElemento;
		int idComanda;
		int contadorElemento;
		LocalDateTime turnoActual=DTOReserva.obtenerTurno();
		String consultaCreacionComanda="INSERT INTO Comanda(idComanda,idMesa,turno) VALUES (null,"+idMesa+",'"+turnoActual+"')";
		agente.Update(consultaCreacionComanda);
		String consultaObtenerComanda="SELECT idComanda FROM Comanda WHERE idMesa="+idMesa+" AND turno='"+turnoActual+"'";
		resultComanda=agente.Read(consultaObtenerComanda);
		try {
			if(resultComanda.next()) {
				idComanda=resultComanda.getInt(1);
				for(String elementoComanda: comanda) {
					contadorElemento=0;
					for(String elemento: comanda) {
						if(elemento.equals(elementoComanda)) {
							contadorElemento++;
						}
					}
					String consultaIdElemento="SELECT codigo FROM Carta WHERE nombre='"+elementoComanda+"'";
					resultIdElemento=agente.Read(consultaIdElemento);
					if(resultIdElemento.next()) {
						idElemento=resultIdElemento.getInt(1);
						String consultaInsertarElementoComanda="INSERT INTO Pedido (comanda, codigo, cantidad) VALUES ("+idComanda+","+idElemento+","+contadorElemento+")";
						agente.Update(consultaInsertarElementoComanda);
					}

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
