package controller.myevents.ec.edu.ups;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.CategoriaDAO;
import dao.myevents.ec.edu.ups.EventoDAO;
import dao.myevents.ec.edu.ups.LocalDAO;
import modelo.myevents.ec.edu.ups.Categoria;
import modelo.myevents.ec.edu.ups.Evento;
import modelo.myevents.ec.edu.ups.Local;



@ManagedBean
public class EventoController {
	
	@Inject
	private EventoDAO evendao;
	
	@Inject
	private LocalDAO locdao;
	
	@Inject
	private CategoriaDAO catedao;
	
	private Evento evento;
	private Local recupelocal;
	private Categoria c;
	
	private List<Evento> levento;
	private List<Evento> leventocercano;
	private List<Evento> leventofecha;
	
	
	/* Variables para controlar ID de la naveacion
	 */
	private int id;
	private int id2;
	private int id3;

	
	/* Getters and Setters
	 */
	
	public int getId3() {
		return id3;
	}


	public void setId3(int id3) {
		this.id3 = id3;
		
	}
	
	public Local getRecupelocal() {
		return recupelocal;
	}

	public void setRecupelocal(Local recupelocal) {
		this.recupelocal = recupelocal;
	}

	public LocalDAO getLocdao() {
		return locdao;
	}

	public void setLocdao(LocalDAO locdao) {
		this.locdao = locdao;
	}

	public int getId() {
		return id;
	}

	
	/* RECUPERAR ID PARA LA NAVEGACION DE LOCALES Y EVENTOS
	 * Sett ID
	 */

	public void setId(int id) {
		this.id = id;
		loadEventoEditar(id);
		loadID(id);

		insertarEventoLocalGloba();
		
		loadCId(id);//agregado
		/////agregar///
		insertaCategoriaAdmin();

	}


	public EventoDAO getEvendao() {
		return evendao;
	}


	public void setEvendao(EventoDAO evendao) {
		this.evendao = evendao;
	}
	

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
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
	
	
	public void loadID(int id) {
		id2 = id;
	}
	
	public void loadCId(int id) {
		id3 = id;
	} 
	
	
	/* Mantenimiento Controlladores del EventoController
	 */
	
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
	
	
	/* Metodo para Agregar un evento a un local
	 */
	
		public String insertarEventoLocalGloba(){ 


			recupelocal=locdao.leerLocal(id2); 
			recupelocal.getEvento().add(evento);
			locdao.updateLocal(recupelocal); 
			
			return null;  
		}
		
		public String insertaCategoriaAdmin(){
			c = catedao.leerCategoria(id3);
			c.getEventos().add(evento);
			catedao.actualizarCategoria(c);
			return null;
					
		}
	
	
}//fin clase EventoController
