package servicios.myevents.ec.edu.ups;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import dao.myevents.ec.edu.ups.CategoriaDAO;
import dao.myevents.ec.edu.ups.LocalDAO;
import dao.myevents.ec.edu.ups.PersonaDAO;
import modelo.myevents.ec.edu.ups.Categoria;
import modelo.myevents.ec.edu.ups.Local;
import modelo.myevents.ec.edu.ups.Persona;

@Path("usuarios")
public class UsuariosWSRest {
	
	/** Inyeccion de dependecias */
	@Inject
	private PersonaDAO pdao;
	@Inject
	private CategoriaDAO cdao;
	@Inject
	private LocalDAO locdao;
	
	
	/**Mostrar el listado de usuarios
	 */
	@GET
	@Path("/listado-users")
	@Produces("application/json")
	public List<Persona> listaPersona(){
		List<Persona> lpersona = pdao.listPersonas();
		return lpersona;
	}
	
	/**Mostrar el listado de las categorias
	 */
	@GET
	@Path("/listado-categorias")
	@Produces("application/json")
	public List<Categoria> listaCategorias(){
		List<Categoria> lcategorias = cdao.listSoloCategorias(); 
		return lcategorias;
	}
	
	
	/**Guardar Usuario
	 */
	@POST
	@Path("crear-usuarios")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta GrabarPersona(Persona p) {
		Respuesta r = new Respuesta();
		try {
			p.setPerfil("USUARIO");
			p.setEstado("A");
			pdao.guardar(p);
			r.setCodigo(1);
			r.setMensaje("Grabado Exitosamente");
			return r;
		}catch(Exception e) {
			e.printStackTrace();
			r.setCodigo(99);
			r.setMensaje("Error al grabar");
			return r;
		}
	}
	
	
	/**Utilizado para el login
	 */
	@POST
	@Path("login")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Persona> login(Persona p) {
		return pdao.login(p.getCorreo(), p.getContrasenia());
	}
	
	/**Mostrar el listado de los locales
	 */
	@GET
	@Path("/listado-locales")
	@Produces("application/json")
	public List<Local> listlocal(){
		List<Local> listlocales = locdao.listlocal(); 
		return listlocales;
	}

}
