package System;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

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
	private ArrayList<Reparacion> reparaciones; 
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
		reparaciones = consultasDB.verReparaciones();
		reparaciones.forEach(rep ->{
			Factura factura = buscarFacturaPorId(rep.getId_factura());
			factura.getReparaciones().add(rep);
		});
	}
	
	private Factura buscarFacturaPorId(int id) {
		for(Factura factura : facturas) {
			if (factura.getId()==id) return factura;
		}
		return null;
	}
	
	private Vehiculo buscarPorID(int id) {
		for(Vehiculo coche : coches) {
			if (coche.getId()==id) return coche;
		}
		return null;
	}
	
	public Factura buscarFacturaActualCoche(Vehiculo coche) {
		for(Factura factura : facturas) {
			if (factura.getCoche()==coche && factura.getFecha_fin()==null) {
				return factura;
			}
		}
	 
		return new Factura(coche);
	}

	
	public void escribirFactura(Factura factura) throws SQLException {
		insertsDB.insertarFactura(factura);
	}
	public void anadirReparacion(Reparacion rep) throws SQLException {
		insertsDB.insertarReparacion(rep);
	}
	
	
	
	public void terminarReparacionCoche(Factura factura) throws SQLException {
		factura.setFecha_fin(new Date());
		factura.setPrecio_total(0);
		factura.setPagado(false);
		factura.getReparaciones().forEach(rep ->{
			factura.setPrecio_total(factura.getPrecio_total()+rep.getDuracion()*10);
			for(Entry<Pieza, Integer> entrada : rep.getPiezas().entrySet()) {
				factura.setPrecio_total(factura.getPrecio_total()+entrada.getKey().getPrecio()*entrada.getValue());
			}			
		});
		escribirFactura(factura);
	}
	
	
}
