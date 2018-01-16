package servicios.myevents.ec.edu.ups;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import controller.myevents.ec.edu.ups.PersonaController;
import controller.myevents.ec.edu.ups.ReservaLocalController;
import dao.myevents.ec.edu.ups.LocalDAO;
import dao.myevents.ec.edu.ups.PersonaDAO;
import dao.myevents.ec.edu.ups.ReservaLocalDAO;
import modelo.myevents.ec.edu.ups.Local;
import modelo.myevents.ec.edu.ups.Persona;
import modelo.myevents.ec.edu.ups.ReservaLocal;

@Path("/locales")
public class LocalesWSRest {
	
	/** Inyeccion de dependecias */
	@Inject
	private LocalDAO locdao;
	
	@Inject
	private ReservaLocalDAO rldao;
	
	@Inject
	private PersonaDAO pdao;
	
	/**Mostrar el listado de los locales
	 * http://localhost:8080/MyEvents/rs/locales/listado-locales
	 */
	@GET
	@Path("/listado-locales")
	@Produces("application/json")
	//@Produces({"application/json", "image/jpg"})
	public List<Local> listlocal(){
		
		/*
		public Response getFile() {
			
			final String FILE_PATH= "C:\\Users\\asus\\git\\MyEvents\\MyEvents\\src\\main\\webapp\\imagenes\\local1.jpg";
			
			File file = new File(FILE_PATH);
			
			ResponseBuilder response = Response.ok((Object) file);
			response.header("Content-Disposition",
				"attachment; filename=image_from_server.jpg");
			return response.build();
			*/
			
		
		List<Local> listlocales = locdao.listlocal(); 
		return listlocales;
	}
	
	@POST
	@Path("/imagenesYdescripcion-locales")
	@Produces("application/json")
	public List<Local> listarLocales(){
		List<Local> listaLocales = new ArrayList<Local>();
		listaLocales = locdao.listlocal();
		return listaLocales;
	}
	/**http://localhost:8080/MyEvents/rs/locales/crear-reservas?id_u=2&id_l=7&fecha_e=2018-01-26
	 * Desde la app Android debe pasarse la fecha como String, en el siguiente formato 2018-01-26
	 * id_u: La variable que hace referencia al ID de la persona que desea hacer la reserva.
	 * id_l: Hace referencia al Id del local que se va a reservar
	 * fecha_e: Hace referencia a la fecha en la cual se va a realizar la reserva 
	 * @throws ParseException 
	 * */
	@GET
	@Path("/crear-reservas")
	@Produces("application/json")
	public Respuesta crearReserva(@QueryParam("id_u") int id_u, @QueryParam("id_l") int id_l, @QueryParam("fecha_e") String fechaR) throws ParseException{
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
		Date fecha = fmt.parse(fechaR);
		System.out.println("F TRANSFORMADA: "+fecha.toString());
		
		Respuesta r = new Respuesta();
//		List<Local> localeslibres = new ArrayList<Local>();
		int idu = id_u;
		int idl = id_l;
		ReservaLocal rl = new ReservaLocal();
		try {
			int Mid = rldao.maxId();
			/**ESTABLEZCO LA TABLA INTERMEDIA
			 * */
			rl.setId(Mid);
			rl.setEstado("true");
			rl.setFechaReserva(fecha);
			rldao.insertarSRecepcion(rl);
			
			/**OBTENGO EL OBJETO PERSONA
			 * */
			Persona p = pdao.selectPersona(idu);
			
			/**OBTENGO EL OBJETO LOCAL
			 * */
			Local l = locdao.leerLocal(idl);
			
			p.getReservaLocal().add(rl);
			l.getReseervaLocales().add(rl);
			pdao.updatePersona(p);
			locdao.updateLocal(l);
			System.out.println("VA A RESERVAR--   us:"+idu+",   idl:"+idl+",   fechaR:"+fechaR);
			r.setCodigo(1);
			r.setMensaje("Reserva Exitosa");
		}catch (Exception e) {
			// TODO: handle exception
			r.setCodigo(-90);
			r.setMensaje("No se ha grabado");
		}
		/**REFRESCANDO LOCALES
		 * */
//		localeslibres=rlc.cargarLocalesSReserva();
		return r;
	}
	
	/**EN ESTE METODO SE DEERIA LISTAR LAS IMAGENES CON SUS RESPECTIVOS LOCALES DE ACUERDO A UNA FECHA ENCIADA POR EL USUARIO
	 * @throws ParseException 
	 * http://localhost:8080/MyEvents/rs/locales/listado-locales-fecha?fecha_SR=2018-01-26
	 * fecha_SR: Es una fecha de tipo String que debe enviarse desde android, comprobado con 2018-01-26
	 * */
	@GET
	@Path("/listado-locales-fecha")
	@Produces("application/json")
	public List<Local> cargarLocalesSReserva(@QueryParam("fecha_SR") String fechaSR) throws ParseException {
		System.out.println("fechaSR     "+fechaSR);
		List<Local> locales = locdao.listlocal();
		List<Local>localeslibres = new ArrayList<Local>();
//		rlc = new ReservaLocalController();
		for(Local l : locales) {
			String ReservaSiNo = "No";
			for(Local l1 : localesRFecha(fechaSR)) {
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
	
	
	public List<Local> localesRFecha(String f) throws ParseException {
		System.out.println("RECUPERA STRING FFFF: "+f);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date fechasave = formatter.parse(f);
//		String nFormato = formatter.format(fechasave);

		List<Local> locales = locdao.listlocal();
		List<Local> lreservados = new ArrayList<Local>();

		for(Local l : locales) {
			if(!l.getReseervaLocales().isEmpty()) {
				for(ReservaLocal rl : l.getReseervaLocales()) {
					System.out.println("COMPARACION:  "+rl.getFechaReserva().toString()+".equals("+f+")");
					if(rl.getEstado().equals("true") && rl.getFechaReserva().toString().equals(f)) {
						lreservados.add(l);
					}
				}	
			}
		}	
		
		return lreservados;
	}
	
}