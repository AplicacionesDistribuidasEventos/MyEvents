package controller.myevents.ec.edu.ups;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.LocalDAO;
import modelo.myevents.ec.edu.ups.Evento;
import modelo.myevents.ec.edu.ups.Local;

@ManagedBean
public class LocalController {

	
	private Local local;
	
	@Inject
	private LocalDAO locdao;
	
	private List<Local> listlocal;
	private Local auxLocal;
	

	/* Datos para la ubicacion
	 */
	private String latitud="-1";
	private String longitud="-1"; 
	private String descripcion;
	private String elegimos;
	private String latituddes;
	private String longituddes;
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public LocalDAO getLocdao() {
		return locdao;
	}
	public void setLocdao(LocalDAO locdao) {
		this.locdao = locdao;
	}
	public List<Local> getListlocal() {
		return listlocal;
	}
	public void setListlocal(List<Local> listlocal) {
		this.listlocal = listlocal;
	}
	public Local getAuxLocal() {
		return auxLocal;
	}
	public void setAuxLocal(Local auxLocal) {
		this.auxLocal = auxLocal;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getElegimos() {
		return elegimos;
	}
	public void setElegimos(String elegimos) {
		this.elegimos = elegimos;
	}
	public String getLatituddes() {
		return latituddes;
	}
	public void setLatituddes(String latituddes) {
		this.latituddes = latituddes;
	}
	public String getLongituddes() {
		return longituddes;
	}
	public void setLongituddes(String longituddes) {
		this.longituddes = longituddes;
	}

	//----MANTENIMIENTO CONTROLLER
	
	public String insertar() {
		
		locdao.insertarLocal(local);
		return "listarlocales";
		
	}
	
	public String actualizar() {
		locdao.updateLocal(local);
		return null;
	}
	
	public String leer(int id) {
		
		local = locdao.leerLocal(id);
		return null;
	}
	
	public String eliminar(int id) {
		locdao.deleteLocal(id);
		return "eliminarLocal";
	}
	public List<Local> listaEventos(){
		
		listlocal = locdao.listlocal();
		return listlocal;
	}
	
}
