package modelo.myevents.ec.edu.ups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



// TODO: Auto-generated Javadoc
/**
 * The Class Evento.
 */
@Entity
@Table(name="EVENTO")
public class Evento {

	/** The codigo. */
	@Id
	@Column(name="even_codigo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codigo;
	
	/** The nombre. */
	@Column(name="even_nombre")
	@NotNull 
	@Size(min=5,max=50)
	private String nombre;
	
	/** The descripcion. */
	@Column(name="eve_Descripcion")
	@NotNull 
	@Size(min=5,max=200)
	private String descripcion;
	
	/** The foto. */
	@Column(name="even_fotografia")
	private byte[] foto;
	
	/** The costo. */
	
	@Column(name="even_costo")
	private String costo;
	
	/** The fecha evento. */
	@Column(name="even_fechaEvento")
	@Temporal(value= TemporalType.DATE)
	@NotNull 
	private Date fechaEvento;
	

	/** The categoria. */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="eve_cat_id")
	private Categoria categoria;
	
	
	
	//relaciones entre evento a salon de recepciones y de evento a asistencia evento

	/** The salones. */
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name="eve_rec_fk", referencedColumnName="even_codigo")
	private List<SalonRecepcion> salones;
	
	/** The Asistencia eventos. */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	//cedula como se va a llamar en la otra tabla, el id de la tabla donde se crea
	@JoinColumn(name="aev_even_id", referencedColumnName="even_codigo")
	private List<AsistenciaEvento> AsistenciaEventos = new ArrayList<>();
	
	
	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	/**
	 * Gets the categoria.
	 *
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Sets the categoria.
	 *
	 * @param categoria the new categoria
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
	 * Gets the foto.
	 *
	 * @return the foto
	 */
	public byte[] getFoto() {
		return foto;
	}

	/**
	 * Sets the foto.
	 *
	 * @param foto the new foto
	 */
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	
	

	/**
	 * Gets the costo.
	 *
	 * @return the costo
	 */
	public String getCosto() {
		return costo;
	}

	/**
	 * Sets the costo.
	 *
	 * @param costo the new costo
	 */
	public void setCosto(String costo) {
		this.costo = costo;
	}

	/**
	 * Gets the fecha evento.
	 *
	 * @return the fecha evento
	 */
	public Date getFechaEvento() {
		return fechaEvento;
	}

	/**
	 * Sets the fecha evento.
	 *
	 * @param fechaEvento the new fecha evento
	 */
	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}
	
	

	/**
	 * Gets the asistencia eventos.
	 *
	 * @return the asistencia eventos
	 */
	public List<AsistenciaEvento> getAsistenciaEventos() {
		return AsistenciaEventos;
	}

	/**
	 * Sets the asistencia eventos.
	 *
	 * @param asistenciaEventos the new asistencia eventos
	 */
	public void setAsistenciaEventos(List<AsistenciaEvento> asistenciaEventos) {
		AsistenciaEventos = asistenciaEventos;
	}
  
  	/**
	   * Gets the salones.
	   *
	   * @return the salones
	   */
	  public List<SalonRecepcion> getSalones() {
		return salones;
	}

	/**
	 * Sets the salones.
	 *
	 * @param salones the new salones
	 */
	public void setSalones(List<SalonRecepcion> salones) {
		this.salones = salones;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Evento [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", foto="
				+ Arrays.toString(foto) + ", costo=" + costo + ", fechaEvento=" + fechaEvento + ", AsistenciaEventos="
				+ AsistenciaEventos + "]";
	}

	/*@Override
	public String toString() {
		return "Evento [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", foto="
				+ Arrays.toString(foto) + ", costo=" + costo + ", fechaEvento=" + fechaEvento + "]";
	}*/
			
}