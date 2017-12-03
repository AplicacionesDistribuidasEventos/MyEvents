package modelo.myevents.ec.edu.ups;

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


@Entity
@Table(name="CATEGORIA")
public class Categoria {
	
	@Id
	@Column(name="cat_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="cat_nombre")
	@NotBlank(message = "Por favor ingrese el nombre de la categoria")
	@Size(min=3,max=20)
	private String nombre;
	
	@Column(name="cat_descripcion")
	private String descipcion;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	//cedula como se va a llamar en la otra tabla, el id de la tabla donde se crea
	@JoinColumn(name="eve_cat_id", referencedColumnName="cat_id")
	private List<Evento> eventos;
	
	
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
	
	

	public List<Evento> getEventos() {
		return eventos;
	}


	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}


	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", descipcion=" + descipcion + ", eventos=" + eventos
				+ "]";
	}


	/*@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", descipcion=" + descipcion + "]";
	}*/
	
	
	

}

