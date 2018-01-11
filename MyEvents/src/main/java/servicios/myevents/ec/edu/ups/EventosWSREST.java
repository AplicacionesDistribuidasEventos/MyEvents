package servicios.myevents.ec.edu.ups;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import controller.myevents.ec.edu.ups.PersonaController;
import dao.myevents.ec.edu.ups.AsistenciaEventoDAO;
import dao.myevents.ec.edu.ups.CategoriaDAO;
import dao.myevents.ec.edu.ups.EventoDAO;
import dao.myevents.ec.edu.ups.PersonaDAO;
import modelo.myevents.ec.edu.ups.AsistenciaEvento;
import modelo.myevents.ec.edu.ups.Categoria;
import modelo.myevents.ec.edu.ups.CategoriaEventos;
import modelo.myevents.ec.edu.ups.Evento;
import modelo.myevents.ec.edu.ups.Persona;

@Path("/eventos")
public class EventosWSREST {
	@Inject
	private CategoriaDAO cdao;
	
	@Inject 
	private PersonaDAO pdao;
	
	@Inject
	private AsistenciaEventoDAO aedao;

	@Inject
	private EventoDAO edao;
	/**
	 * WS: Devuelve JSON de eventos referente a una categoria en especial
	 * http://localhost:8080/MyEvents/rs/eventos/listado-categoria-eventos?id_categoria=1
	 * */
	@GET
	@Path("/listado-categoria-eventos")
	@Produces("application/json")
	public List<Evento> getWSCategoriaEventos(@QueryParam("id_categoria") int id_categoria){
		List<Evento> cel = new ArrayList<Evento>();
		Categoria categoria = new Categoria();
		
		categoria = cdao.leerCategoria(id_categoria);
		for(Evento ev : categoria.getEventos()) {
			cel.add(ev);
		}
		return cel;
	}
	
	/**
	 * WS: Devuelve un JSON de Mensaje con la respectiva confirmacion, este WS es
	 * utilizado para realizar las reservaciones en la parte del cliente. Se envian
	 * 3 parametros mediante solicitudes GET estado: hace referencia a un String, o
	 * se puede establecer como true o false cod:Es el codigo referente al evento
	 * que a elegido el usuario id_user: El codigo del USUARIO de la sesion de
	 * Android
	 * http://localhost:8080/MyEvents/rs/eventos/establecer-asistencia?estado=true&cod=14&id_user=7
	 */
	@GET
	@Path("/establecer-asistencia")
	@Produces("application/json")
	public Respuesta confirmarAsistencia(@QueryParam("estado") String estado, @QueryParam("cod") int cod, @QueryParam("id_user") int id_user) {
		Respuesta r = new Respuesta();
		Persona per = pdao.selectPersona(id_user);
		List<AsistenciaEvento> asisevenl = new ArrayList<AsistenciaEvento>();
		asisevenl = aedao.listAEvento();
		
		Evento eve = edao.leerEvento(cod);

		int idMax = aedao.maxId();
		
		AsistenciaEvento ae = new AsistenciaEvento();

		/**Condicion 1: esta con registros de ASISTENCIAEVENTOS la determinada PERSONA*/
		/** Condicion 1.1: compara si existe, si existe ACTULIZA*/
		/** CONDICION 1.2: compara si existe, no existe AGREGA */
		/** Condicion 2: esta vacia, simplemente agrega a la lista*/

		if (!per.getAeventos().isEmpty() && !eve.getAsistenciaEventos().isEmpty()) {
			/** CONDICION 1 actualiza en vacia */
		 System.out.println("BUSCANDO COINCIDENCIAS");
		 	System.out.println("P TOTAL: "+per);
			System.out.println("P eventosl: "+per.getAeventos());
			
			System.out.println("EV TOTAL: "+eve);
			System.out.println("E eventosl: "+eve.getAsistenciaEventos());
			
			for (AsistenciaEvento aevper : per.getAeventos()) {
				System.out.println("ESTE 1~=: "+aevper.getCodigo());
				for (AsistenciaEvento aevl : asisevenl) {
					System.out.println("COMP: "+aevper.getCodigo()+"=="+aevl.getCodigo() +" && "+ eve.getAsistenciaEventos().get(0).getCodigo()+ "=="+aevl.getCodigo());
					System.out.println(" ");
					for(AsistenciaEvento aee : eve.getAsistenciaEventos()) {
						if (aevper.getCodigo() == aevl.getCodigo()
								&& aee.getCodigo() == aevl.getCodigo()) {
							System.out.println("HUBIERON CINCIDENCIAS: actualizar estado");
							aevper.setEstado(estado);

							aedao.guardar(aevper);
							r.setCodigo(100);
							r.setMensaje("Establecimiento exitoso");
							return r;
						}	
					}
				}
			}
			System.out.println("REGISTRO LLENO pero SIN COINCIDENCIAS");
			/** Al final si no encontro COINCIDENCIAS que inserte uno nuevo*/
					ae = new AsistenciaEvento();
					ae.setCodigo(idMax);
					ae.setEstado(estado.toString());

					aedao.guardar(ae);

					per.getAeventos().add(ae);
					eve.getAsistenciaEventos().add(ae);

					pdao.updatePersona(per);
					edao.updateEvento(eve);
		} 
		else {
			/** CONDICION 2 inserta en vacia */
			System.out.println("VA A LLENAR UNA EN BLANCO");
			ae = new AsistenciaEvento();
			ae.setCodigo(idMax);
			ae.setEstado(estado);

			aedao.guardar(ae);

			per.getAeventos().add(ae);
			eve.getAsistenciaEventos().add(ae);

			pdao.updatePersona(per);
			edao.updateEvento(eve);
		}	
		r.setCodigo(-1);
		r.setMensaje("No se establecio");
		return r;
	}

	/**
	 * WS: Devuelve un listado de todos los eventos
	 * Produce un Coleccion de tipo JSON
	 * http://localhost:8080/MyEvents/rs/eventos/listado-eventos
	 * */
	@GET
	@Path("/listado-eventos")
	@Produces("application/json")
	public List<Evento> getListadoEventos(){
		List<Evento> eventos = new ArrayList<Evento>();
		eventos = edao.listEvento();
		return eventos;
	}
}
