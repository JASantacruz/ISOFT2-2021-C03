package Dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;

import Persistencia.Agente;

public class DTOComanda {
	public static Agente agente = new Agente();

	public static LinkedList<String> comprobarStock(LinkedList<String> elementosComanda) {
		LinkedList<String> cartaSinStock=new LinkedList<String>();
		LinkedList<String> elementosComprobados=new LinkedList<String>();
		LinkedList<Ingrediente> ingredientesNecesarios=new LinkedList<Ingrediente>();
		ResultSet resultStock;
		ResultSet resultReceta;
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
									+ "FROM Receta WHERE codigoCarta=(SELECT codigo FROM Carta WHERE nombre='"+elementoComanda+"')";
							resultReceta=agente.Read(consultaIngredienteNecesario);
							while(resultReceta.next()) {//todos los ingredientes de cada plato
								nombreIngrediente=resultReceta.getString(1);
								cantidadNecesaria=resultReceta.getInt(2);
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
	public static int guardarComanda(String idMesa, LinkedList<String> comanda) {
		int res = 0;
		ResultSet resultIdElemento;
		ResultSet resultComanda;
		int idElemento;
		int idComanda;
		int contadorElemento;
		LocalDateTime turnoActual=DTOReserva.obtenerTurno();
		String consultaCreacionComanda="INSERT INTO Comanda(idComanda,idMesa,turno) VALUES (null,"+idMesa+",'"+turnoActual+"');";
		String consultaObtenerComanda="SELECT idComanda FROM Comanda WHERE idMesa="+idMesa+" AND turno='"+turnoActual+"';";
		try {
			agente.Update(consultaCreacionComanda);
			resultComanda=agente.Read(consultaObtenerComanda);
			if(resultComanda.next()) {
				idComanda=resultComanda.getInt(1);
				for(String elementoComanda: comanda) {
					contadorElemento=0;
					for(String elemento: comanda) {
						if(elemento.equals(elementoComanda)) {
							contadorElemento++;
						}
					}
					String consultaIdElemento="SELECT codigo FROM Carta WHERE nombre='"+elementoComanda+"';";
					resultIdElemento=agente.Read(consultaIdElemento);
					while(resultIdElemento.next()) {
						idElemento=resultIdElemento.getInt(1);
						String consultaInsertarElementoComanda="INSERT INTO Pedido (comanda, codigo, cantidad) VALUES ("+idComanda+","+idElemento+","+contadorElemento+");";
						agente.Update(consultaInsertarElementoComanda);
					}

				}
			}
			res = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static int actualizarStock(LinkedList<String> lista) {
		Agente ag = new Agente();
		ResultSet res;
		int resultado = 0;
		Receta rec;
		LinkedList<Receta> listReceta = new LinkedList<>();
		LinkedList<Integer> listStock = new LinkedList<>();
		int codigo = 0;
		int stock = 0;
		for(int i = 0; i < lista.size(); i++) {
			String consulta = "SELECT codigo FROM Carta WHERE nombre='"+lista.get(i)+"';";
			try {
				res = ag.Read(consulta);
				while(res.next())
					codigo = res.getInt(1); 
				consulta = "SELECT nombreIngrediente, cantidad FROM Receta WHERE codigoCarta="+codigo+";";
				res = ag.Read(consulta);
				while(res.next()) {
					rec = new Receta(res.getString(1), res.getInt(2)); 
					listReceta.add(rec);
				}
				for(int j = 0; j < listReceta.size(); j++) {
					consulta = "SELECT stock FROM Ingrediente WHERE nombre"+listReceta.get(j).getNombreIngrediente()+";"; 
					res = ag.Read(consulta);
					while(res.next())
						stock = res.getInt(1);
					consulta = "UPDATE Ingrediente SET stock="+(stock-1)+" WHERE nombre='"+listReceta.get(j).getNombreIngrediente()+"';";
					ag.Update(consulta);
				}//Fin for j
				resultado = 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public static int leerAlimentosDeComandas(LinkedList<String> pedidos) {
		Agente ag = new Agente();
		ResultSet res;
		LinkedList<Integer> Comandas = new LinkedList<>();
		int resultado = 1;
		String consulta = "SELECT idComanda FROM Comanda WHERE (SELECT DATEDIFF(NOW(), turno)) < 3;"; 
		try {
			res = ag.Read(consulta);
			while(res.next()) 
				Comandas.add(res.getInt(1));

			for(int i = 0; i < Comandas.size(); i++) {
				consulta = "SELECT nombre FROM Carta WHERE codigo IN (SELECT codigo FROM Pedido WHERE comanda="+Comandas.get(i)+");";
				res = ag.Read(consulta);
				while(res.next())
					pedidos.add(res.getString(1));
			}
			resultado = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return resultado;
	}
	public static int subirAvisoCocina(String idMesa, String camarero) {
		LocalDateTime turno=DTOReserva.obtenerTurno();
		String consultaIdCamarero="SELECT idCamarero FROM Camarero WHERE nombre='"+camarero+"'";
		ResultSet idCamarero;
		int res=-1;
		try {
			idCamarero=agente.Read(consultaIdCamarero);
			if(idCamarero.next())
			res=agente.Insert("INSERT INTO Aviso (idAviso, descripcion, idCamarero, turno) VALUES "
					+ "(null, 'Comida de la mesa "+idMesa+" preparada',"+idCamarero.getInt(1)+",'"+turno+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	public static int subirAvisoCamareroBarra(String idMesa, String camarero) {
		LocalDateTime turno=DTOReserva.obtenerTurno();
		String consultaIdCamarero="SELECT idCamarero FROM Camarero WHERE nombre='"+camarero+"'";
		ResultSet idCamarero;
		int res=-1;
		try {
			idCamarero=agente.Read(consultaIdCamarero);
			if(idCamarero.next())
			res=agente.Insert("INSERT INTO Aviso (idAviso, descripcion, idCamarero, turno) VALUES "
					+ "(null, 'Bebidas de la mesa "+idMesa+" preparadas',"+idCamarero.getInt(1)+",'"+turno+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
