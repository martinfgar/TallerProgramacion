package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Modelos.Cliente;
import Modelos.Pieza;
import Modelos.Vehiculo;

public class Consultas {
	
	public List<Vehiculo> verVehiculos(String username,String password){
		List<Vehiculo> vehiculos = new ArrayList<>();
		try {
			ConexionDB SQL = new ConexionDB();
			Connection conn = SQL.conectarOracle(username, password);
			String query = "select id_vehiculo,modelo,color,año,matricula,cliente.id_cliente,nombre,apellido,telefono,direccion,dni,correo "
					+ "from vehiculo inner join cliente on vehiculo.id_cliente = cliente.id_cliente";
			Statement pstm = conn.createStatement();
			ResultSet resultados =pstm.executeQuery(query);
			while(resultados.next()) {
				Cliente dueno = new Cliente(resultados.getInt(6),resultados.getString(7),resultados.getString(8),
							resultados.getString(9),resultados.getString(10),
							resultados.getString(11),resultados.getString(12));
				Vehiculo buga = new Vehiculo(resultados.getInt(1),resultados.getString(2),resultados.getString(3),
								resultados.getDate(4),resultados.getString(5),dueno);
				vehiculos.add(buga);
			}
			conn.close();
			return vehiculos;
		} catch(Exception e) {
			return vehiculos;
		}
	}
	
	public List<Pieza> verPiezas(String username,String password){
		List<Pieza> piezas = new ArrayList<Pieza>();
			try {
				ConexionDB SQL = new ConexionDB();
				Connection conn = SQL.conectarOracle(username, password);
				String query = "select id_pieza,marca,modelo,precio,stock,descripcion,categorias.nombre "
						+ "from pieza inner join categorias on pieza.id_categoria=categorias.id_categoria";
			}catch (Exception e) {
				
			}
	}
	
}
