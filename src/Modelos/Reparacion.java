package Modelos;

import java.util.Date;
import java.util.HashMap;

public class Reparacion {
	
	private int id;
	private Date fecha_hora;
	private float duracion;
	private String comentarios;
	private Factura factura;
	private Vehiculo coche;
	private	HashMap<Pieza,Integer> piezas;
	
	public Reparacion() {};
	
	public Reparacion(int id, Date hora, float duracion, String comentarios, Factura factura, Vehiculo coche,
			HashMap<Pieza, Integer> piezas) {
		this.id = id;
		this.fecha_hora = hora;
		this.duracion = duracion;
		this.comentarios = comentarios;
		this.factura = factura;
		this.coche = coche;
		this.piezas = piezas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getHora() {
		return fecha_hora;
	}
	public void setHora(Date hora) {
		this.fecha_hora = hora;
	}
	public float getDuracion() {
		return duracion;
	}
	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public Vehiculo getCoche() {
		return coche;
	}
	public void setCoche(Vehiculo coche) {
		this.coche = coche;
	}
	public HashMap<Pieza, Integer> getPiezas() {
		return piezas;
	}
	public void setPiezas(HashMap<Pieza, Integer> piezas) {
		this.piezas = piezas;
	}
	
	
	
}
