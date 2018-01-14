package servicios.myevents.ec.edu.ups;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import dao.myevents.ec.edu.ups.LocalDAO;
import modelo.myevents.ec.edu.ups.Local;

@Path("/locales")
public class LocalesWSRest {
	
	/** Inyeccion de dependecias */
	@Inject
	private LocalDAO locdao;
	
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
}
