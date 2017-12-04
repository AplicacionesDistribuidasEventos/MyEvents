package controller.myevents.ec.edu.ups;



import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.CategoriaDAO;
import dao.myevents.ec.edu.ups.EventoDAO;
import modelo.myevents.ec.edu.ups.Categoria;
import modelo.myevents.ec.edu.ups.Evento;



@ManagedBean
public class EventoController {

	
	private Evento evento;
	private Categoria c;
	
	@Inject
	private EventoDAO evendao;
	
	@Inject
	private CategoriaDAO catedao;
	
	private List<Evento> levento;
	private List<Evento> leventocercano;
	private List<Evento> leventofecha;
	
	private int id;
	private int id3;
	
	/////////////////Inicio de Get and set ///////////////////////////////

	public int getId3() {
		return id3;
	}


	public void setId3(int id3) {
		this.id3 = id3;
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
		loadEventoEditar(id);
		loadId(id);//agregado
		/////agregar///
		insertaCategoriaAdmin();
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
	
	public void loadId(int id) {
		id3 = id;
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
	
	public String insertaCategoriaAdmin(){
		c = catedao.leerCategoria(id3);
		c.getEventos().add(evento);
		catedao.actualizarCategoria(c);
		return null;
				
	}
	
}








