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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "PERSONA")
public class Persona {
	@Id
	@Column(name = "per_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "per_nombre")
	@NotBlank(message = "Por favor ingrese el nombre")
	private String nombre;

	@Column(name = "per_apellido")
	@NotBlank(message = "Por favor ingrese el apellido")
	private String apellido;

	@Column(name = "per_cedula")
	@Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Solo debe ingresar numeros")
	@NotBlank(message = "Por favor ingrese la cedula")
	private String cedula;

	@Column(name = "per_correo")
	@NotBlank(message = "Por favor ingrese el correo")
	private String correo;

	@Column(name = "per_perfil")
	private String perfil;

	@Column(name = "per_contrasenia")
	@Size(min = 4, message = "Debe ingresar un minimo de 4 caracteres")
	private String contrasenia;

	@Column(name = "per_estado")
	private String estado;
	
	@OneToMany(cascade=(javax.persistence.CascadeType.ALL),fetch=FetchType.EAGER)
	@JoinColumn(name="per_aev_fk", referencedColumnName="per_id")
	private List<AsistenciaEvento> aeventos;
	
	@OneToMany(cascade=(javax.persistence.CascadeType.ALL),fetch=FetchType.EAGER)
	@JoinColumn(name="per_loc_fk", referencedColumnName="per_id")
	private List<Local> locales;
	
	@OneToMany(cascade=(javax.persistence.CascadeType.ALL),fetch=FetchType.EAGER)
	@JoinColumn(name="per_sal_fk", referencedColumnName="per_id")
	private List<SalonRecepcion> srecepciones;

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

	public List<AsistenciaEvento> getAeventos() {
		return aeventos;
	}

	public void setAeventos(List<AsistenciaEvento> aeventos) {
		this.aeventos = aeventos;
	}

	public List<Local> getLocales() {
		return locales;
	}

	public void setLocales(List<Local> locales) {
		this.locales = locales;
	}

	public List<SalonRecepcion> getSrecepciones() {
		return srecepciones;
	}

	public void setSrecepciones(List<SalonRecepcion> srecepciones) {
		this.srecepciones = srecepciones;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula
				+ ", correo=" + correo + ", perfil=" + perfil + ", contrasenia=" + contrasenia + ", estado=" + estado
				+ ", aeventos=" + aeventos + ", locales=" + locales + ", srecepciones=" + srecepciones + "]";
	}

	

	/*@Override
	public String toString() {
		return "Personas [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula
				+ ", correo=" + correo + ", perfil=" + perfil + ", contrasenia=" + contrasenia + ", fecha_nac="
				+ ", estado=" + estado + "]";
	}*/
	
	
}
