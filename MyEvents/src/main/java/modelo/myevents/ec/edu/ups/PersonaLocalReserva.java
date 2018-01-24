package modelo.myevents.ec.edu.ups;

import java.util.Date;
 
public class PersonaLocalReserva {
	
	/**Atributos para vista de LOCALES*/
	private int l_codigo;
	private String l_nombre;
	private String l_descripcion;
	private String l_fotoPerfil;
	
	
	/**Atributos para vista de ReservaLocales*/
	private int res_id;
	private String res_estado;
	private Date res_fechaReserva;
	
	

	
	/**Getters y Setters*/
	
	

	public String getL_descripcion() {
		return l_descripcion;
	}
	public int getL_codigo() {
		return l_codigo;
	}
	public void setL_codigo(int l_codigo) {
		this.l_codigo = l_codigo;
	}
	public String getL_nombre() {
		return l_nombre;
	}
	public void setL_nombre(String l_nombre) {
		this.l_nombre = l_nombre;
	}
	public int getRes_id() {
		return res_id;
	}
	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}
	public void setL_descripcion(String l_descripcion) {
		this.l_descripcion = l_descripcion;
	}
	public String getL_fotoPerfil() {
		return l_fotoPerfil;
	}
	public void setL_fotoPerfil(String l_fotoPerfil) {
		this.l_fotoPerfil = l_fotoPerfil;
	}
	public String getRes_estado() {
		return res_estado;
	}
	public void setRes_estado(String res_estado) {
		this.res_estado = res_estado;
	}
	public Date getRes_fechaReserva() {
		return res_fechaReserva;
	}
	public void setRes_fechaReserva(Date res_fechaReserva) {
		this.res_fechaReserva = res_fechaReserva;
	}
	@Override
	public String toString() {
		return "PersonaLocalReserva [l_nombre=" + l_nombre + ", l_descripcion=" + l_descripcion + ", l_fotoPerfil="
				+ l_fotoPerfil + ", res_estado=" + res_estado + ", res_fechaReserva=" + res_fechaReserva + "]";
	}
	
	
	
}
