package controller.myevents.ec.edu.ups;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import dao.myevents.ec.edu.ups.LocalDAO;
import dao.myevents.ec.edu.ups.PersonaDAO;
import dao.myevents.ec.edu.ups.ReservaLocalDAO;
import modelo.myevents.ec.edu.ups.Local;
import modelo.myevents.ec.edu.ups.Persona;
import modelo.myevents.ec.edu.ups.PersonaLocalReserva;
import modelo.myevents.ec.edu.ups.ReservaLocal;

// TODO: Auto-generated Javadoc
/**
 * The Class SalonRecepcionController.
 */
@ManagedBean
@SessionScoped
public class ReservaLocalController {
	
	/** Inyeccion de dependencias */
	@Inject
	private ReservaLocalDAO rldao;
	
	/**
	 * */
	@Inject
	private LocalDAO ldao;
	
	@Inject
	private PersonaDAO pdao;
	
	private ReservaLocal reservaLaux;
	
	private List<Local> localSinR = new ArrayList<Local>();
	
	/** Variables definidas para el manejo del SalonRecepcion*/
	private ReservaLocal reservalocal = new ReservaLocal();
	private List<ReservaLocal> lsalonrecepcion;
	private int id;
	
	List<Local> localeslibres = new ArrayList<Local>();
	
	/**
	 * init(). Incializamos todos los métodos y variables.
	 */
	@PostConstruct
	public void init() {
		reservalocal = new ReservaLocal();
		reservaLaux = new ReservaLocal();

		//listaSalonRecepcion();
	}
	

	/**
	 * Geters y Seters de las variables definidas y ha ser utilizadas.
	 */
  
	
	
	public int getId() {
		return id;
	}
	
	public ReservaLocal getReservaLaux() {
		return reservaLaux;
	}


	public void setReservaLaux(ReservaLocal reservaLaux) {
		this.reservaLaux = reservaLaux;
	}


	public void setId(int id) {
		this.id = id;
		cargarEstado(id);
	}
/*
	public ReservaLocal getSalonrecepcion() {
		return salonrecepcion;
	}
	
	public void setSalonrecepcion(ReservaLocal salonrecepcion) {
		this.salonrecepcion = salonrecepcion;
	}
*/	
	
	
	public List<ReservaLocal> getLsalonrecepcion() {
		return lsalonrecepcion;
	}
	
	public List<Local> getLocaleslibres() {
		return localeslibres;
	}


	public void setLocaleslibres(List<Local> localeslibres) {
		this.localeslibres = localeslibres;
	}


	public ReservaLocal getReservalocal() {
		return reservalocal;
	}


	public void setReservalocal(ReservaLocal reservalocal) {
		this.reservalocal = reservalocal;
	}


	public void setLsalonrecepcion(List<ReservaLocal> lsalonrecepcion) {
		this.lsalonrecepcion = lsalonrecepcion;
	}
/*	
	public String crear() {
		srdao.guardar(salonrecepcion);
		return "listadoSalonRecepcion";
	}
	
	public String leer(int id) {
		salonrecepcion = srdao.selectSRecepcion(id);
		return null;
	}
*/	
	/**
	 * Eliminar. Méoto para eliminar el salon de recepciones.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String eliminar(int id) {
		rldao.removeSRecepcion(id);
		return null;
	}
	
	/**
	 * Lista salon recepcion. Método lista todos los las recepciones.
	 *
	 * @return una lista de tipo lsalonrecepcion
	 */
	/*
	public List<ReservaLocal> listaSalonRecepcion() {
		lsalonrecepcion =srdao.listSRecepcion();
		return lsalonrecepcion;
	}
	*/
	/**
	 * Cargar estado. Es el estado del salon de recepciones.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String cargarEstado(int id) {
		reservalocal = rldao.selectSRecepcion(id);
		return "recuperarAsistenciaEvento";
	}
	
	public static Date fechaR;
	/**
	 * Metodo que lista todos los locales RESERVADOS A corde a una fecha
	 * @throws ParseException 
	 * */
	public List<Local> localesRFecha() {
		Date fecha = reservalocal.getFechaReserva();
		fechaR = fecha;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String nFormato = formatter.format(fecha);
		System.out.println("INGRESADO CONVERTIDO: " + nFormato);

		List<Local> locales = ldao.listlocal();
		List<Local> lreservados = new ArrayList<Local>();
System.out.println("LOCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: RRRRRRRRRRRRCCCCCCCCC");
		for(Local l : locales) {
			if(!l.getReseervaLocales().isEmpty()) {
				for(ReservaLocal rl : l.getReseervaLocales()) {
					System.out.println("COMPARACION:  "+rl.getFechaReserva()+".equals("+nFormato+")");
					if(rl.getEstado().equals("true") && rl.getFechaReserva().toString().equals(nFormato)) {
//						System.out.println("RESERVADO: "+l);
						lreservados.add(l);
					}
				}	
			}
		}	
		return lreservados;
	}
	
	public List<Local> cargarLocalesSReserva() {
		//localesRFecha();
		List<Local> locales = ldao.listlocal();
		localeslibres = new ArrayList<Local>();
		
		for(Local l : locales) {
			String ReservaSiNo = "No";
			System.out.println("ANTESSSSSSSSSSSSSSSSS");
			for(Local l1 : localesRFecha()) {
				System.out.println("LUEGOOOOOOOOOOOOO");
				if (l1.getCodigo() == l.getCodigo()) {
					ReservaSiNo = "Si";
					break;
				}else {
					ReservaSiNo = "No";
				}
			}
			if (ReservaSiNo == "No") {
				localeslibres.add(l);
			}
		}
		return localeslibres;
	}
	
	public void Reservar(int id) {
	//System.out.println("ID: "+id);
		int idu = PersonaController.idUsuario;
		int idl = id;
		ReservaLocal rl = new ReservaLocal();
		int Mid = rldao.maxId();
		/**ESTABLEZCO LA TABLA INTERMEDIA
		 * */
		rl.setId(Mid);
		rl.setEstado("true");
		rl.setFechaReserva(fechaR);
		rldao.insertarSRecepcion(rl);
		
		/**OBTENGO EL OBJETO PERSONA
		 * */
		Persona p = pdao.selectPersona(idu);
		
		/**OBTENGO EL OBJETO LOCAL
		 * */
		Local l = ldao.leerLocal(idl);
		
		p.getReservaLocal().add(rl);
		l.getReseervaLocales().add(rl);
		pdao.updatePersona(p);
		ldao.updateLocal(l);
		System.out.println("VA A RESERVAR--   us:"+idu+",   idl:"+idl+",   fechaR:"+fechaR);
		/**REFRESCANDO LOCALES
		 * */
		localeslibres=cargarLocalesSReserva();
	}
	
	/**PERMITE QUITAR LAS RESERVAS POR PARTE DEL ADMINISTRADOR Y USUARIO
	 * */

	public String QuitarResera(int id) {
		
		reservaLaux = rldao.selectSRecepcion(id);
		
		System.out.println("ID RESERVAS" + "" + reservaLaux);
		reservaLaux.setEstado("false");	
		rldao.updateSRecepcion(reservaLaux);
		System.out.println(reservaLaux + "miooooooooo");
	
		return null;

	}
	

}