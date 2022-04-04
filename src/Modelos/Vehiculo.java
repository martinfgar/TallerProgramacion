package Modelos;

import java.util.Date;

public class Vehiculo {
	
	private int id;
	private String modelo;
	private String color;
	private Date a�o;
	private String matricula;

	private Cliente dueno;
	
	
	public Vehiculo(int id, String modelo, String color, Date a�o, String matricula, Cliente dueno) {
		this.id = id;
		this.modelo = modelo;
		this.color = color;
		this.a�o = a�o;
		this.matricula = matricula;
		this.dueno = dueno;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Date getA�o() {
		return a�o;
	}
	public void setA�o(Date a�o) {
		this.a�o = a�o;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Cliente getDueno() {
		return dueno;
	}
	public void setDueno(Cliente dueno) {
		this.dueno = dueno;
	}
	
	
}
