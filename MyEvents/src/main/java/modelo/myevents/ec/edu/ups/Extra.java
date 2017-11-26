package modelo.myevents.ec.edu.ups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EXTRA")
public class Extra {
	
	@Id
	@Column(name="ext_id")
	private int id;
	
	@Column(name="ext_nombre")
	private String nombre;
	
	@Column(name="ext_descripcion")
	private String descipcion;

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescipcion() {
		return descipcion;
	}


	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}


	@Override
	public String toString() {
		return "Extra [id=" + id + ", nombre=" + nombre + ", descipcion=" + descipcion + "]";
	}
	

}

