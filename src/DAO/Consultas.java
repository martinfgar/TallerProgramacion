package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Modelos.Cliente;
import Modelos.Factura;
import Modelos.Pieza;
import Modelos.Reparacion;
import Modelos.Vehiculo;

public class Consultas {
	
	private String username;
	private String password;
	
	public Consultas(String username,String password) {
		this.username=username;
		this.password=password;
	}
	public int last_rep_id() throws SQLException {
		ConexionDB SQL = new ConexionDB();
		Connection conn = SQL.conectarOracle(username, password);
		try {
			String sql_id_rep = "select max(id_reparacion) from reparacion";
			Statement pstm = conn.createStatement();
			ResultSet resultado = pstm.executeQuery(sql_id_rep);
			if (resultado.next()) return resultado.getInt(1);
			return 0;
		} catch(Exception e) {
			conn.close();
			return 0;
		}
	}
	
	public ArrayList<Vehiculo> verVehiculos() throws SQLException{
		ArrayList<Vehiculo> vehiculos = new ArrayList<>();
		ConexionDB SQL = new ConexionDB();
		Connection conn = SQL.conectarOracle(username, password);
		try {
			
			String query = "select id_vehiculo,modelo,color,a�o,matricula,cliente.id_cliente,nombre,apellido,telefono,direccion,dni,correo "
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
			conn.close();
			return vehiculos;
		}
	}
	
	public ArrayList<Pieza> verPiezas() throws SQLException{
		ArrayList<Pieza> piezas = new ArrayList<Pieza>();
		ConexionDB SQL = new ConexionDB();
		Connection conn = SQL.conectarOracle(username, password);
			try {
				String query = "select id_pieza,marca,modelo,precio,stock,descripcion,categorias.nombre "
						+ "from pieza inner join categorias on pieza.id_categoria=categorias.id_categoria";
				Statement pstm =  conn.createStatement();
				ResultSet resultados =pstm.executeQuery(query);
				while(resultados.next()) {
					Pieza pieza = new Pieza(resultados.getInt(1),resultados.getString(2),resultados.getString(3),
							resultados.getDouble(4),resultados.getInt(5),resultados.getString(6),resultados.getString(7));
					piezas.add(pieza);
				}
				conn.close();
				return piezas;
			}catch (Exception e) {
				conn.close();
				return piezas;
			}
	}
	
	public ArrayList<Factura> verFacturas() throws SQLException{
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		ConexionDB SQL = new ConexionDB();
		Connection conn = SQL.conectarOracle(username, password);
		try {
			String query = "select id_factura,fecha_entrada,precio_total,fecha_fin,pagado,id_vehiculo from factura";
			Statement pstm =  conn.createStatement();
			ResultSet resultados =pstm.executeQuery(query);
			while(resultados.next()) {
				Factura fact = new Factura(resultados.getInt(1),resultados.getDate(2),resultados.getDouble(3),resultados.getDate(4),resultados.getBoolean(5),resultados.getInt(6));
				facturas.add(fact);
			}
			conn.close();
			return facturas;
		}catch (Exception e) {
			conn.close();
			return facturas;
		}
	}
	public ArrayList<Reparacion> verReparaciones() throws SQLException{
		ArrayList<Reparacion> reparaciones = new ArrayList<Reparacion>();
		ConexionDB SQL = new ConexionDB();
		Connection conn = SQL.conectarOracle(username, password);
		try {
			String query = "select * from reparacion";
			Statement pstm =  conn.createStatement();
			ResultSet resultados =pstm.executeQuery(query);
			while(resultados.next()) {
				Reparacion rep = new Reparacion(resultados.getInt(1),resultados.getDate(2),resultados.getFloat(3),resultados.getString(4),resultados.getInt(5));
				reparaciones.add(rep);
			}
			conn.close();
			return reparaciones;
		}catch (Exception e) {
			conn.close();
			return reparaciones;
		}
	}
}
