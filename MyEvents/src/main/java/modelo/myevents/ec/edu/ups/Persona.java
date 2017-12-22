package modelo.myevents.ec.edu.ups;
  
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/*ENTIDAD PERSONA
 * */
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
	
	/*
	 * Variable para la validacion de la cedula
	 */
	@Transient
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";   
	
	 /*Listado Tipo Asistencia Eventos, unidireccional
	  * */
	@OneToMany(cascade=(javax.persistence.CascadeType.ALL),fetch=FetchType.LAZY)
	@JoinColumn(name="per_aev_fk", referencedColumnName="per_id")
	private List<AsistenciaEvento> aeventos =new ArrayList<>();

	 /*Listado Tipo Locales, unidireccional
	  * */


	 /*Listado Tipo Locales, unidireccional
	  * */
	@OneToMany(cascade=(javax.persistence.CascadeType.ALL),fetch=FetchType.EAGER)
	@JoinColumn(name="per_loc_fk", referencedColumnName="per_id")
	private List<Local> locales=new ArrayList<>();
	
	 /*Listado Tipo Salon Recepciones, unidireccional
	  * */
	@OneToMany(cascade=(javax.persistence.CascadeType.ALL),fetch=FetchType.LAZY)
	@JoinColumn(name="per_sal_fk", referencedColumnName="per_id")
	private List<SalonRecepcion> srecepciones=new ArrayList<>();



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
		if (cedula.length()==10) {
			if (validarCedula(cedula) == true) {
				System.out.println("VALIDA");
				this.cedula = cedula;
			} else {
				System.out.println("NO VALIDA");
				this.cedula = null;
			}
		} else {
			this.cedula = null;
		}
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		if(validarCorreo(correo)==true) {
			this.correo = correo;
		}else {
			this.correo = null;	
		}
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

	/*Metodo util en la validacion de la cedula
	 * */
	public boolean validarCedula(String cedul) {
		String ced = cedul;
		int sum_t = 0;
		int res = 0;
		for (int i = 0; i < 9; i++) {
			char b = ced.charAt(i);
			int a = b - 48;
			if (i == 0) {
				a = a * 2;
			} else {
				if (i % 2 == 0) {
					a = a * 2;
				} else {
					a = a * 1;
				}
			}
			if (a > 9) {
				a = a - 9;
			}
			sum_t = sum_t + a;
		}
		res = sum_t % 10;
		if (res != 0) {
			res = 10 - res;
		}
		boolean resultado = false;
		if (res == Integer.parseInt(ced.substring(9, 10))) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
	}

	
	/*Metodo para la validacion de un correo electronico
	 * */
	public boolean validarCorreo(String corr) {
		String email = corr;
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
	}

	
}
