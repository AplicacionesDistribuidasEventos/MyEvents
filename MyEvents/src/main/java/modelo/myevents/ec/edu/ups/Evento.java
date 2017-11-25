package modelo.myevents.ec.edu.ups;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//TABLA EVENTO EN LA BASE DE DATOS

@Entity
@Table(name="EVENTO")
public class Evento {

	@Id
	@Column(name="even_codigo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codigo;
	
	@Column(name="even_nombre")
	@NotNull 
	@Size(min=5,max=50)
	private String nombre;
	
	@Column(name="eve_Descripcion")
	@NotNull 
	@Size(min=5,max=200)
	private String descripcion;
	
	@Column(name="even_fotografia")
	private byte[] foto;
	/*
	@Column(name="even_capacidad")
	@NotNull 
	private int capacidad;
	*/
	@Column(name="even_costo")
	private double costo;
	
	@Column(name="even_fechaEvento")
	@Temporal(value= TemporalType.DATE)
	@NotNull 
	private Date fechaEvento;
	/*
	@Column(name="even_latiud")
	private double latitud;
	
	@Column(name="even_longitud")
	private double longitud;
	*/
	
	//METHODS GETTERS AND SETTER
	
	

	public int getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	
	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Date getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	@Override
	public String toString() {
		return "Evento [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", foto="
				+ Arrays.toString(foto) + ", costo=" + costo + ", fechaEvento=" + fechaEvento + "]";
	}

	
			
}
