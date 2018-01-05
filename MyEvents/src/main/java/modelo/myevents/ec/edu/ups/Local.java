package modelo.myevents.ec.edu.ups;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


// TODO: Auto-generated Javadoc
/**
 * The Class Local.
 */
/*ENTIDAD LOCAL SALON DE EVENTOS
 * */
@Entity
@Table(name="LOCAL")
public class Local implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** The codigo. */
	@Id
	@Column(name="local_codigo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codigo;
	
	/** The nombre. */
	@Column(name="local_nombre")
	@NotNull 
	@Size(min=5,max=50)
	private String nombre;
	
	/** The descripcion. */
	@Column(name="local_descripcion")
	@NotNull 
	@Size(min=5,max=50)
	private String descripcion;
	
	/** The capacidad. */
	@Column(name="local_capacidad")
	@NotNull 
	private String capacidad;
	
	/** The costo. */
	@Column(name="local_costo")
	@NotNull 
	private String costo;
	
	/** The puntuacion. */
	@Column(name="local_puntuacion")
	@NotNull 
	private String puntuacion;
	
	/** The comentario. */
	@Column(name="local_comentario")
	@NotNull 
	private String comentario;
	
	/** The foto. */
	@Column(name="even_fotografia")
	private String fotoPerfil;
	
	/** The latitud. */
	private String latitud;
	
	/** The longitud. */
	private String longitud;
	
	private byte[] imagen;
	
	
	/** The evento. */
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name="loc_eve_fk", referencedColumnName="local_codigo")
	private List<Evento> evento ;
	
	
	
	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	/**
	 * Gets the foto perfil.
	 *
	 * @return the foto perfil
	 */
	public String getFotoPerfil() {
		return fotoPerfil;
	}

	/**
	 * Sets the foto perfil.
	 *
	 * @param fotoPerfil the new foto perfil
	 */
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	/**
	 * Gets the evento.
	 *
	 * @return the evento
	 */
	public List<Evento> getEvento() {
		return evento;
	}
	
	/**
	 * Sets the evento.
	 *
	 * @param evento the new evento
	 */
	public void setEvento(List<Evento> evento) {
		this.evento = evento;
	}
	
	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
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
	 * Gets the capacidad.
	 *
	 * @return the capacidad
	 */
	public String getCapacidad() {
		return capacidad;
	}
	
	/**
	 * Sets the capacidad.
	 *
	 * @param capacidad the new capacidad
	 */
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
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
	 * Gets the puntuacion.
	 *
	 * @return the puntuacion
	 */
	public String getPuntuacion() {
		return puntuacion;
	}
	
	/**
	 * Sets the puntuacion.
	 *
	 * @param puntuacion the new puntuacion
	 */
	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	/**
	 * Gets the comentario.
	 *
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}
	
	/**
	 * Sets the comentario.
	 *
	 * @param comentario the new comentario
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
	/**
	 * Gets the latitud.
	 *
	 * @return the latitud
	 */
	public String getLatitud() {
		return latitud;
	}

	/**
	 * Sets the latitud.
	 *
	 * @param latitud the new latitud
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	/**
	 * Gets the longitud.
	 *
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}

	/**
	 * Sets the longitud.
	 *
	 * @param longitud the new longitud
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Local [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", capacidad="
				+ capacidad + ", costo=" + costo + ", puntuacion=" + puntuacion + ", comentario=" + comentario
				+ ", latitud=" + latitud + ", longitud=" + longitud + "]";
	}

	
	
	
	
}
