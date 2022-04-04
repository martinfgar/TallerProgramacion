package Modelos;

import java.util.Date;

public class Vehiculo {
	
	private int id;
	private String modelo;
	private String color;
	private Date año;
	private String matricula;

	private Cliente dueno;
	
	
	public Vehiculo(int id, String modelo, String color, Date año, String matricula, Cliente dueno) {
		this.id = id;
		this.modelo = modelo;
		this.color = color;
		this.año = año;
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
	public Date getAño() {
		return año;
	}
	public void setAño(Date año) {
		this.año = año;
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
