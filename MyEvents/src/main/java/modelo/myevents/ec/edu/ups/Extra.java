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

// TODO: Auto-generated Javadoc
/**
 * The Class Extra.
 */
@Entity
@Table(name="EXTRA")
public class Extra {
	
	/** The id. */
	@Id
	@Column(name="ext_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	/** The nombre. */
	@Column(name="ext_nombre")
	@Size(min=3,max=25)
	private String nombre;
	
	/** The descipcion. */
	@Column(name="ext_descripcion")
	@Size(min=3,max=300)
	private String descipcion;
	
	/** The salrecepciones. */
	@OneToMany(cascade=(javax.persistence.CascadeType.ALL),fetch=FetchType.EAGER)
	@JoinColumn(name="sal_rec_ext_fk", referencedColumnName="ext_id")
	private List<SalonRecepcion> salrecepciones;

	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * Gets the descipcion.
	 *
	 * @return the descipcion
	 */
	public String getDescipcion() {
		return descipcion;
	}


	/**
	 * Sets the descipcion.
	 *
	 * @param descipcion the new descipcion
	 */
	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}
	
	


	/**
	 * Gets the salrecepciones.
	 *
	 * @return the salrecepciones
	 */
	public List<SalonRecepcion> getSalrecepciones() {
		return salrecepciones;
	}


	/**
	 * Sets the salrecepciones.
	 *
	 * @param salrecepciones the new salrecepciones
	 */
	public void setSalrecepciones(List<SalonRecepcion> salrecepciones) {
		this.salrecepciones = salrecepciones;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Extra [id=" + id + ", nombre=" + nombre + ", descipcion=" + descipcion + ", salrecepciones="
				+ salrecepciones + "]";
	}
}

