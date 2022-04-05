package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map.Entry;

import Modelos.Factura;
import Modelos.Pieza;

public class Updates {
	
	private String username;
	private String password;
	
	public Updates(String username,String password) {
		this.username=username;
		this.password=password;
	}
	
	public void actualizarFactura(Factura factura) throws SQLException {
		ConexionDB conexion = new ConexionDB();
		Connection conn = conexion.conectarOracle(username, password);
		try {
			String ssql = "update factura set  precio_total=? , fecha_fin = ?,pagado=? where id_factura=?";
			PreparedStatement stmt = conn.prepareStatement(ssql);
			stmt.setDate(2, (Date) factura.getFecha_fin());
			stmt.setDouble(1, factura.getPrecio_total());
			stmt.setBoolean(3, factura.isPagado());
			stmt.setInt(4, factura.getId());
			stmt.executeUpdate();
			conn.close();
		} catch(Exception e) {
			conn.close();
		}
	}
}
