package controller.myevents.ec.edu.ups;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.LocalDAO;
import dao.myevents.ec.edu.ups.PersonaDAO;
import modelo.myevents.ec.edu.ups.Local;
import modelo.myevents.ec.edu.ups.Persona;

@ManagedBean
public class LocalController {

	private int id2;
	private Local local; 
	
	private Persona p ;
	
	@Inject
	private LocalDAO locdao;
	
	@Inject
	private PersonaDAO pdao;
	
	private List<Local> listlocal;
	private Local auxLocal;
	
	//  Atributo para la navegacion por codigo en JSF
	private int id;

	/* Datos para la ubicacion
	 */
	private String latitud="-1";
	private String longitud="-1"; 
	private String descripcion;
	private String elegimos;
	private String latituddes;
	private String longituddes;
	
	
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
		loadLocalEditar(id);
		loadId(id);
	}
	
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
	
	
	
	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	@PostConstruct
	public void init() {
		local = new Local(); 
		loadLocal();
	}

	public void loadLocal() {
		
		listlocal=locdao.listlocal();
	}
	
	public String loadLocalEditar(int id) {
		
		local = locdao.leerLocal(id);
		
		return "CrearLocal";
	}
	
	public void loadId(int id) {
		id2 = id;
	}
	
	//----MANTENIMIENTO CONTROLLER
	
	public String insertar() {
		locdao.guardarLocal(local);
		loadLocal();

		return null;
		
	}
	//toomo 1 ID
	public String insertarLocalAdmin() {
		p = new Persona();
		System.out.println("ESTE ID: ");
/*		local.setCapacidad("200");
		local.setComentario("Buenazo");
		local.setCosto("200.90");
		local.setDescripcion("Esta description");
		local.setNombre("Guayuzo");
		local.setPuntuacion("1");
		*/
		p = pdao.selectPersona(id2);
		listlocal.add(local);
		p.setLocales(listlocal);
		pdao.updatePersona(p); 
		//locdao.updateLocal(p.setLocales(listlocal));
		return null;
		
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
	public List<Local> listaLocales(){
		
		listlocal = locdao.listlocal();
		return listlocal;
	}
	
	
	
}//fin localController
