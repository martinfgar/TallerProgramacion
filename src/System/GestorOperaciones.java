package System;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.Consultas;
import DAO.Inserts;
import Modelos.Factura;
import Modelos.Pieza;
import Modelos.Reparacion;
import Modelos.Vehiculo;
public class GestorOperaciones {
	
	private String username;
	private String password;
	private ArrayList<Vehiculo> coches;
	private ArrayList<Factura> facturas;
	private ArrayList<Pieza> piezas;
	private Consultas consultasDB;
	private Inserts insertsDB;
	
	public GestorOperaciones(String username, String password) throws SQLException {
		this.username=username;
		this.password=password;
		consultasDB = new Consultas(username,password);
		insertsDB = new Inserts(username,password);
		coches = consultasDB.verVehiculos();
		piezas = consultasDB.verPiezas();
		facturas = consultasDB.verFacturas();
		facturas.forEach(factura ->{
			factura.setCoche(buscarPorID(factura.getId_vehiculo()));
		});
	}
	
	private Vehiculo buscarPorID(int id) {
		for(Vehiculo coche : coches) {
			if (coche.getId()==id) return coche;
		}
		return null;
	}

	
	public void anadirReparacion(Reparacion rep) throws SQLException {
		insertsDB.insertarReparacion(rep);
	}
	
	
}
