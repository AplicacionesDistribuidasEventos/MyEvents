package modelo.myevents.ec.edu.ups;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="EXTRA")
public class Extra {
	
	@Id
	@Column(name="ext_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="ext_nombre")
	@Size(min=3,max=25)
	private String nombre;
	
	@Column(name="ext_descripcion")
	@Size(min=3,max=300)
	private String descipcion;
	
	@OneToMany(cascade=(javax.persistence.CascadeType.ALL),fetch=FetchType.EAGER)
	@JoinColumn(name="sal_rec_ext_fk", referencedColumnName="ext_id")
	private List<SalonRecepcion> salrecepciones;

	
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
	
	


	public List<SalonRecepcion> getSalrecepciones() {
		return salrecepciones;
	}


	public void setSalrecepciones(List<SalonRecepcion> salrecepciones) {
		this.salrecepciones = salrecepciones;
	}


	@Override
	public String toString() {
		return "Extra [id=" + id + ", nombre=" + nombre + ", descipcion=" + descipcion + ", salrecepciones="
				+ salrecepciones + "]";
	}
}

