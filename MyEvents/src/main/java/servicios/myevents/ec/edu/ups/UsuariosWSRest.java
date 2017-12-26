package servicios.myevents.ec.edu.ups;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import dao.myevents.ec.edu.ups.PersonaDAO;
import modelo.myevents.ec.edu.ups.Persona;

@Path("usuarios")
public class UsuariosWSRest {
	@Inject
	private PersonaDAO pdao;
	
	/*Mostrar el listado de usuarios
	 * */
	@GET
	@Path("listado-users")
	@Produces("application/json")
	public List<Persona> listaPersona(){
		return pdao.listPersonas();
	}
	
	
	/*Guardar Usuario
	 * */
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
	
	
	/*Utilizado para el login
	 * */
	@POST
	@Path("login")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Persona> login(Persona p) {
		return pdao.login(p.getCorreo(), p.getContrasenia());
	}
	

}
