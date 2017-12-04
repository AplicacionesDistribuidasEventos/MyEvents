package controller.myevents.ec.edu.ups;



import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.EventoDAO;
import dao.myevents.ec.edu.ups.LocalDAO;
import modelo.myevents.ec.edu.ups.Evento;
import modelo.myevents.ec.edu.ups.Local;



@ManagedBean
public class EventoController {

	
	private Evento evento;
	private Local local;
	
	@Inject
	private EventoDAO evendao;
	
	@Inject
	private LocalDAO locdao;
	
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
	
	
	//------------------Agregar un evento A local Maestro-Detalle
	
		public String InsertarEventoLocal(){ 
			
			Local loc = new Local();   			//Instnacias Local 
			loc = locdao.leerLocal(id); 		//Leer id del local
			levento.add(evento); 				//agregar la instancia evento a la lista eventos
			loc.setEvento(levento); 			// llamar las lista de evento en el local
			locdao.updateLocal(loc); 			//Actualizar el local con eventos
			return null;  
		}
	
	
}








