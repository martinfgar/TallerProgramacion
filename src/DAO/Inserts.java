package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map.Entry;

import Modelos.Pieza;
import Modelos.Reparacion;

public class Inserts {
	
	private String username;
	private String password;
	
	public Inserts(String username,String password) {
		this.username=username;
		this.password=password;
	}
	
	public void insertarReparacion(Reparacion rep) throws SQLException {
		
		ConexionDB conexion = new ConexionDB();
		Connection conn = conexion.conectarOracle(username, password);
		try {
			String ssql = "insert into reparacion(tiempo_hora,horas_duracion,comentarios, id_factura) values(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(ssql);
			stmt.setDate(1, (Date) rep.getHora());
			stmt.setFloat(2, rep.getDuracion());
			stmt.setString(3, rep.getComentarios());
			stmt.setInt(4,rep.getFactura().getId());
			stmt.executeUpdate();
			int id_rep = new Consultas(username,password).last_rep_id()+1;
			for(Entry<Pieza, Integer> entrada : rep.getPiezas().entrySet()) {
				String ssql2 = "insert into usa values(?,"+id_rep+",?)";
				PreparedStatement stmt2 = conn.prepareStatement(ssql2);
				stmt2.setInt(1, entrada.getKey().getId());
				stmt2.setInt(2, entrada.getValue());
				stmt2.executeUpdate();
			}
			conn.close();
		} catch(Exception e) {
			conn.close();
		}
		
		
	}
}