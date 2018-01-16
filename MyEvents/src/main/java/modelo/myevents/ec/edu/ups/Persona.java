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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

// TODO: Auto-generated Javadoc
/**
 * The Class Persona.
 */

@Entity
@Table(name = "PERSONA")
public class Persona {
	
	/** The id. */
	@Id
	@Column(name = "per_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	/** The nombre. */
	@Column(name = "per_nombre")
	@NotBlank(message = "Por favor ingrese el nombre")
	private String nombre;

	/** The apellido. */
	@Column(name = "per_apellido")
	@NotBlank(message = "Por favor ingrese el apellido")
	private String apellido;

	/** The cedula. */
	@Column(name = "per_cedula")
	@Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Solo debe ingresar numeros")
	@NotBlank(message = "Por favor ingrese la cedula")
	private String cedula;

	/** The correo. */
	@Column(name = "per_correo")
	@NotBlank(message = "Por favor ingrese el correo") 
	private String correo;

	/** The perfil. */
	@Column(name = "per_perfil")
	private String perfil;

	/** The contrasenia. */
	@Column(name = "per_contrasenia")
	@Size(min = 4, message = "Debe ingresar un minimo de 4 caracteres")
	private String contrasenia;

	/** The estado. */
	@Column(name = "per_estado")
	private String estado;


	 /** The aeventos. */
 	/*Listado Tipo Asistencia Eventos, unidireccional
	  * */
	@OneToMany(cascade=(javax.persistence.CascadeType.ALL),fetch=FetchType.LAZY)
	@JoinColumn(name="per_aev_fk", referencedColumnName="per_id")
	private List<AsistenciaEvento> aeventos =new ArrayList<>();

	 /*Listado Tipo Locales, unidireccional
	  * */

	
	 /** The locales. */
 	/*Listado Tipo Locales, unidireccional
	  * */
	@OneToMany(cascade=(javax.persistence.CascadeType.ALL),fetch=FetchType.EAGER)
	@JoinColumn(name="per_loc_fk", referencedColumnName="per_id")
	private List<Local> locales=new ArrayList<>();
	
	 /** The srecepciones. */
 	/*Listado Tipo Salon Recepciones, unidireccional
	  * */
	@OneToMany(cascade=(javax.persistence.CascadeType.ALL),fetch=FetchType.LAZY)
	@JoinColumn(name="per_reserv_fk", referencedColumnName="per_id")
	private List<ReservaLocal> reservaLocal=new ArrayList<>();



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
	 * Gets the apellido.
	 *
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Sets the apellido.
	 *
	 * @param apellido the new apellido
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Gets the cedula.
	 *
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * Sets the cedula.
	 *
	 * @param cedula the new cedula
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * Gets the correo.
	 *
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Sets the correo.
	 *
	 * @param correo the new correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Gets the perfil.
	 *
	 * @return the perfil
	 */
	public String getPerfil() {
		return perfil;
	}

	/**
	 * Sets the perfil.
	 *
	 * @param perfil the new perfil
	 */
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	/**
	 * Gets the contrasenia.
	 *
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * Sets the contrasenia.
	 *
	 * @param contrasenia the new contrasenia
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

	/**
	 * Gets the aeventos.
	 *
	 * @return the aeventos
	 */
	public List<AsistenciaEvento> getAeventos() {
		return aeventos;
	}

	/**
	 * Sets the aeventos.
	 *
	 * @param aeventos the new aeventos
	 */
	public void setAeventos(List<AsistenciaEvento> aeventos) {
		this.aeventos = aeventos;
	}

	/**
	 * Gets the locales.
	 *
	 * @return the locales
	 */
	public List<Local> getLocales() {
		return locales;
	}

	/**
	 * Sets the locales.
	 *
	 * @param locales the new locales
	 */
	public void setLocales(List<Local> locales) {
		this.locales = locales;
	}

	

	public List<ReservaLocal> getReservaLocal() {
		return reservaLocal;
	}

	public void setReservaLocal(List<ReservaLocal> reservaLocal) {
		this.reservaLocal = reservaLocal;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula
				+ ", correo=" + correo + ", perfil=" + perfil + ", contrasenia=" + contrasenia + ", estado=" + estado
				+ ", aeventos=" + aeventos + ", locales=" + locales + ", reservaLocal=" + reservaLocal + "]";
	}


}