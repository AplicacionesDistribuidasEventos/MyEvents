package modelo.myevents.ec.edu.ups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="LOCAL")
public class Local {
	
	@Id
	private int codigo;
	
	@Column(name="local_nombre")
	@NotNull 
	@Size(min=5,max=50)
	private String nombre;
	
	@Column(name="local_descripcion")
	@NotNull 
	@Size(min=5,max=50)
	private String descripcion;
	
	@Column(name="local_capacidad")
	@NotNull 
	private String capacidad;
	
	@Column(name="local_costo")
	@NotNull 
	private String costo;
	
	@Column(name="local_puntuacion")
	@NotNull 
	private String puntuacion;
	
	@Column(name="local_comentario")
	@NotNull 
	private String comentario;
	
	@Column(name="even_fotografia")
	private byte[] foto;
	
	private double latitud;
	
	private double longitud;
	
	public int getCodigo() {
		return codigo;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}
	public String getCosto() {
		return costo;
	}
	public void setCosto(String costo) {
		this.costo = costo;
	}
	public String getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	
	@Override
	public String toString() {
		return "Local [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", capacidad="
				+ capacidad + ", costo=" + costo + ", puntuacion=" + puntuacion + ", comentario=" + comentario
				+ ", latitud=" + latitud + ", longitud=" + longitud + "]";
	}

	
	
	
	
}
