package controller.myevents.ec.edu.ups;

import java.util.ArrayList;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.AsistenciaEventoDAO;
import dao.myevents.ec.edu.ups.EventoDAO;
import dao.myevents.ec.edu.ups.PersonaDAO;
import modelo.myevents.ec.edu.ups.AsistenciaEvento;
import modelo.myevents.ec.edu.ups.Evento;
import modelo.myevents.ec.edu.ups.Persona;


// TODO: Auto-generated Javadoc
/**
 * The Class AsistenciaEventoController.
 */

@ManagedBean
public class AsistenciaEventoController {
	
	/** Inyeccion de dependecias a los DAO que se van a utilizar */
	@Inject
	private AsistenciaEventoDAO aedao;
	
	@Inject
	private EventoDAO edao;
	
	@Inject
	private PersonaDAO pdao;
	
	
	/** Variables definidas para el manejo de los Locales */
	private int id;
	
	/** Variables de lista y de objetos utilizados */
	private AsistenciaEvento asistenciaevento;
	private List<AsistenciaEvento> lasistenciaevento;
	
	
	/**
	 * init().
	 * Metodo para incializar los metodos de clase
	 */
	@PostConstruct
	public void init() {
		asistenciaevento = new AsistenciaEvento();
		listaAsistenciaEvento();
	}
	
	/**
	 * Geters y Seters de las variables definidas.
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		cargarEstado(id);
	}


	public AsistenciaEvento getAsistenciaevento() {
		return asistenciaevento;
	}
	
	public void setAsistenciaevento(AsistenciaEvento asistenciaevento) {
		this.asistenciaevento = asistenciaevento;
	}
	
	public List<AsistenciaEvento> getLasistenciaevento() {
		return lasistenciaevento;
	}
	
	public void setLasistenciaevento(List<AsistenciaEvento> lasistenciaevento) {
		this.lasistenciaevento = lasistenciaevento;
	}

	/**
	 * Crear. Inserta las AsistenciaEventos del local
	 *
	 * @return URL de navegacion
	 */
	public String crear() {
		//aedao.insertAEvento(asistenciaevento);
		aedao.guardar(asistenciaevento);
		return "listadoAsistenciaEvento";
	}
	
	/**
	 * Leer. Método para leer el codigo de la entidad AsistenciaEvento
	 *
	 * @param id
	 * @return null
	 */
	public String leer(int id) {
		asistenciaevento = aedao.selectAEvento(id);
		return null;
	}
	
	/**
	 * Eliminar. Aistencia evento
	 *
	 * @param id the id
	 * @return null
	 */
	public String eliminar(int id) {
		aedao.removeAEvento(id);
		return null;
	}
	
	/**
	 * Lista asistencia evento.
	 *
	 * @return the list de todas las asitencias
	 */
	public List<AsistenciaEvento> listaAsistenciaEvento() {
		lasistenciaevento = aedao.listAEvento();
		return lasistenciaevento;
	}
	
	/**
	 * Cargar estado.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String cargarEstado(int id) {
		asistenciaevento = aedao.selectAEvento(id);
		return "recuperarAsistenciaEvento";
	}
	
	/** Confirmar la asistencia al Evento*/
	public boolean confirmarEstadoAsistencia(int cod, Boolean estado) {
		Persona per = pdao.selectPersona(PersonaController.idUsuario);
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
							aevper.setEstado(estado.toString());

							aedao.guardar(aevper);
							return true;
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
			ae.setEstado(estado.toString());

			aedao.guardar(ae);

			per.getAeventos().add(ae);
			eve.getAsistenciaEventos().add(ae);

			pdao.updatePersona(per);
			edao.updateEvento(eve);
		}	
		return false;
	}


}