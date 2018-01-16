package servicios.myevents.ec.edu.ups;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

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
			pdao.insertPersona(p);
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
	
	
	/**
	 * WS: Utilizado para el login del usuario, manda como parametro una coleccion
	 * JSON, donde envian solamente el usuario y la contrasenia
	 */
	@POST
	@Path("login")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Persona> login(Persona p) {
		return pdao.login(p.getCorreo(), p.getContrasenia());
	}

	/**
	 * WS: Elimina el usuario tomado como parametro del respectivo ID del Usuario a
	 * eliminar Retorna una coleccion JSON donde retorna un mensaje exitoso en el
	 * caso de eliminarlo.
	 * http://localhost:8080/MyEvents/rs/usuarios/eliminar-usuario?id_usuario=17
	 */
	@GET
	@Path("/eliminar-usuario")
	@Produces("application/json")
	public Respuesta elimnarUsuario(@QueryParam("id_usuario") int id) {
		Respuesta r = new Respuesta();
		if(pdao.deletePersona(id)==true) {
			r.setCodigo(1);
			r.setMensaje("eliminado!");
		}else {
			r.setCodigo(-99);
			r.setMensaje("fallo al eliminar");
		}
		return r;
	}
	
	/**
	 * WS: PARA CARGAR LA INFORMACION DE LA CUENTA DE UN DETERMINADO USUARIO id:
	 * Hace referencia al ID de la Persona
	 * http://localhost:8080/MyEvents/rs/usuarios/informacion-usuario?id=3
	 */
	@GET
	@Path("/informacion-usuario")
	@Produces("application/json")
	public Persona informacionPersona(@QueryParam("id") int id) {
		Persona p = new Persona();
		p = pdao.elejirPersona(id);
		return p;
	}
	
	/**
	 * WS: Para la actualizacion del objeto Usuario
	 * http://localhost:8080/MyEvents/rs/usuarios/actualizar-usuario?nombre=carla&apellido=diazz&correo=carlaa@gmail.com&id=3
	 * nombre: el nombre a actualizar
	 * apellido: el apellido a actualizar
	 * correo: 
	 */
	@GET
	@Path("/actualizar-usuario")
	@Produces("application/json")
	public Respuesta editarPerfil(@QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido,
			@QueryParam("correo") String correo, @QueryParam("id") int id) {
		Respuesta r = new Respuesta();
		Persona p = new Persona();
		try {
			p = pdao.elejirPersona(id);
			p.setNombre(nombre);
			p.setApellido(apellido);
			p.setCorreo(correo);
			pdao.updatePersona(p);
			r.setCodigo(1);
			r.setMensaje("Actualizacion exitosa");
		} catch (Exception e) {
			r.setCodigo(-90);
			r.setMensaje("Error al actualizar");
			;
			e.printStackTrace();
		}
		return r;
	}
}