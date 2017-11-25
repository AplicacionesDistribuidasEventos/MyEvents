package controller.myevents.ec.edu.ups;



import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.EventoDAO;
import modelo.myevents.ec.edu.ups.Evento;



@ManagedBean
public class EventoController {

	
	private Evento evento;
	
	@Inject
	private EventoDAO evendao;
	
	private List<Evento> levento;
	private List<Evento> leventocercano;
	private List<Evento> leventofecha;
	private Evento auxEvento;
	
	
	
	/* Datos para la ubicacion
	 */
	private String latitud="-1";
	private String longitud="-1"; 
	private String descripcion;
	private String elegimos;
	private String latituddes;
	private String longituddes;
	
	
	
	
	
	
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


	public EventoDAO getEvendao() {
		return evendao;
	}


	public void setEvendao(EventoDAO evendao) {
		this.evendao = evendao;
	}


	public Evento getAuxEvento() {
		return auxEvento;
	}


	public void setAuxEvento(Evento auxEvento) {
		this.auxEvento = auxEvento;
	}


	@PostConstruct
	public void init() {
		evento = new Evento(); 
	}


	public Evento getEvento() {
		return evento;
	}


	public void setEvento(Evento evento) {
		this.evento = evento;
	}



	public List<Evento> getLevento() {
		return levento;
	}


	public void setLevento(List<Evento> levento) {
		this.levento = levento;
	}


	public List<Evento> getLeventocercano() {
		return leventocercano;
	}


	public void setLeventocercano(List<Evento> leventocercano) {
		this.leventocercano = leventocercano;
	}


	public List<Evento> getLeventofecha() {
		return leventofecha;
	}


	public void setLeventofecha(List<Evento> leventofecha) {
		this.leventofecha = leventofecha;
	}
	
	//----MANTENIMIENTO CONTROLLER
	
	
	
	public String insertar() {
		evendao.insertarEvento(evento);
		return "listarEventos";
	}
	
	public String actualizar() {
		evendao.updateEvento(evento);
		return null;
	}
	
	public String leer(int id) {
		evento = evendao.leerEvento(id);
		return null;
	}
	public String eliminar(int id) {
		evendao.deleteEvento(id);
		return "eliminarEvento";
	}
	  
	
	public List<Evento> listaEventos(){
		
		levento = evendao.listEvento();
		return levento;
	}
	/*
	public String cargarMapa(Evento e ){
		auxEvento = e;
		return("mapas_veterinarias");
	}
	*/
}








