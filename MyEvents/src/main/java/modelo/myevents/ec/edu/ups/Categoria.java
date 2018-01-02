package modelo.myevents.ec.edu.ups;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


// TODO: Auto-generated Javadoc
/**
 * The Class Categoria.
 */
@Entity
@Table(name="CATEGORIA")
public class Categoria {
	
	/** The id. */
	@Id
	@Column(name="cat_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	/** The nombre. */
	@Column(name="cat_nombre")
	@NotBlank(message = "Por favor ingrese el nombre de la categoria")
	@Size(min=3,max=20)
	private String nombre;
	
	/** The descipcion. */
	@Column(name="cat_descripcion")
	private String descipcion;
	
	/** The eventos. */
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY)
	//cedula como se va a llamar en la otra tabla, el id de la tabla donde se crea
	@JoinColumn(name="eve_cat_id", referencedColumnName="cat_id")
	private List<Evento> eventos;
	

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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", descipcion=" + descipcion + "]";
	}
	
	

	/**
	 * Gets the eventos.
	 *
	 * @return the eventos
	 */
	public List<Evento> getEventos() {
		return eventos;
	}


	/**
	 * Sets the eventos.
	 *
	 * @param eventos the new eventos
	 */
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	/**
	 * Adds the evento.
	 *
	 * @param evento the evento
	 */
	public void addEvento(Evento evento){
		if (eventos == null){
			eventos = new ArrayList<>();
			eventos.add(evento);
			
		}
	}
}



