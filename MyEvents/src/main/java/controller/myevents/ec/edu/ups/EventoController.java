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
	
	private int id;
	
	
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
		loadEventoEditar(id);
	}


	public EventoDAO getEvendao() {
		return evendao;
	}


	public void setEvendao(EventoDAO evendao) {
		this.evendao = evendao;
	}


	@PostConstruct
	public void init() {
		evento = new Evento(); 
		loadEvento();
		
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
	
	
	public void loadEvento() {
		
		levento = evendao.listEvento();
	}
	
	//----MANTENIMIENTO CONTROLLER
	
	public String loadEventoEditar(int id) {
		
		evento = evendao.leerEvento(id);
		
		return "CrearEvento";
	}
	
	public String insertar() {
		evendao.guardarEvento(evento);
		loadEvento();
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








