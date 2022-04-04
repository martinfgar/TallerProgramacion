package Modelos;

import java.util.Date;

public class Factura {
	
	private int id;
	private Date fecha_entrada;
	private double precio_total;
	private Date fecha_fin;
	private boolean pagado;
	private int id_vehiculo;
	private Vehiculo coche;
	
	
	
	public Factura(int id, Date fecha_entrada, double precio_total, Date fecha_fin, boolean pagado, int id_vehiculo) {
		this.id = id;
		this.fecha_entrada = fecha_entrada;
		this.precio_total = precio_total;
		this.fecha_fin = fecha_fin;
		this.pagado = pagado;
		this.id_vehiculo=id_vehiculo;
	}
	public Factura(int id, Date fecha_entrada, double precio_total, Date fecha_fin, boolean pagado, Vehiculo coche) {
		this.id = id;
		this.fecha_entrada = fecha_entrada;
		this.precio_total = precio_total;
		this.fecha_fin = fecha_fin;
		this.pagado = pagado;
		this.coche = coche;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha_entrada() {
		return fecha_entrada;
	}
	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}
	public double getPrecio_total() {
		return precio_total;
	}
	public void setPrecio_total(double precio_total) {
		this.precio_total = precio_total;
	}
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public boolean isPagado() {
		return pagado;
	}
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	public Vehiculo getCoche() {
		return coche;
	}
	public void setCoche(Vehiculo coche) {
		this.coche = coche;
	}
	public int getId_vehiculo() {
		return id_vehiculo;
	}
	public void setId_vehiculo(int id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}
	
	
}
