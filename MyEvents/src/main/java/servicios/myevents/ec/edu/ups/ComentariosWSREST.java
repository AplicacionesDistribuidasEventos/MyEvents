package servicios.myevents.ec.edu.ups;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import controller.myevents.ec.edu.ups.LocalController;
import controller.myevents.ec.edu.ups.PersonaController;
import dao.myevents.ec.edu.ups.ComentarioDAO;
import dao.myevents.ec.edu.ups.LocalDAO;
import dao.myevents.ec.edu.ups.PersonaDAO;
import modelo.myevents.ec.edu.ups.Comentario;
import modelo.myevents.ec.edu.ups.Local;
import modelo.myevents.ec.edu.ups.Persona;
import modelo.myevents.ec.edu.ups.PersonaLocalComentario;

@Path("/Comentarios")
public class ComentariosWSREST {

	@Inject
	private PersonaDAO pdao;

	@Inject
	private LocalDAO ldao;

	@Inject
	private ComentarioDAO codao;

	/**
	 * WS: Deuelve un objero PersonaComentarioLocal una fachada de negocio id_local:
	 * Hace referencia al id del local, recuperamos los comentarios solamente de un
	 * determinado local
	 * http://localhost:8080/MyEvents/rs/Comentarios/list-comentarios-local?id_local=3
	 */
	@GET
	@Path("list-comentarios-local")
	@Produces("application/json")
	public List<PersonaLocalComentario> listPLC(@QueryParam("id_local") int id_loc) {
		List<PersonaLocalComentario> plcl = new ArrayList<PersonaLocalComentario>();
		PersonaLocalComentario plc = new PersonaLocalComentario();
		List<Persona> personas = pdao.listPersonas();
		Local localselect = ldao.leerLocal(id_loc);
		if (!localselect.getComentario().isEmpty()) {
			for (Comentario coml : localselect.getComentarios()) {
				for (Persona p : personas) {
					for (Comentario comp : p.getComentarios()) {
						if (comp.getId() == coml.getId()) {
							/* INSTANCIACION DEL OBJEO PLC NO SE SOBREESCRIBA */
							plc = new PersonaLocalComentario();
							plc.setNombre_p(p.getNombre());
							plc.setApellido_p(p.getApellido());
							plc.setComentario(coml.getDescripcion());
							plc.setFecha_c(coml.getFecha());
							plcl.add(plc);
						}
					}
				}
			}
		} else {
			System.out.println("Este salon no tiene comentarios");
		}
		System.out.println("ESTOS SON LOS COMENTARIOS:   " + plcl);
		return plcl;
	}

	/**
	 * WS para la insercion de comentarios de un determinado local, devuelve como
	 * resultado un objeto respuesta
	 * loc_descripcion: Es el comentario referente al local
	 * id_local: Es el id del local del cual se va a realizar el comentario
	 * id_user: Es el usuario quien hace el comentario
	 * http://localhost:8080/MyEvents/rs/Comentarios/agregar-comentario-local?id_local=3&loc_descripcion=Este comentario intenta no ser ambiguo&id_user=22
	 */
	@GET
	@Path("/agregar-comentario-local")
	@Produces("application/json")
	public Respuesta insertarComentario(@QueryParam("id_local") int id_local,
			@QueryParam("loc_descripcion") String loc_descripcion, @QueryParam("id_user")int id_user) {
		System.out.println("COMENTARIODESCRIPCION: " + id_local+" "+loc_descripcion);
		System.out.println("INGRESAA A INSERTAR COMENTARIOS");
		Local local = ldao.leerLocal(id_local);
		Persona persona = pdao.selectPersona(id_user);
		Comentario comentario;
		Respuesta r = new Respuesta();;
		if (loc_descripcion != null) {
			comentario = new Comentario();
			r = new Respuesta();
			int idMax = codao.MaxID();
			Date fechadDate = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String fecha = formatter.format(fechadDate);
			comentario.setId(idMax);
			comentario.setFecha(fecha);
			comentario.setDescripcion(loc_descripcion);
			try {
				codao.insertComentario(comentario);

				local.getComentarios().add(comentario);
				ldao.updateLocal(local);
				persona.getComentarios().add(comentario);
				pdao.updatePersona(persona);
				comentario.setDescripcion(null);
				r.setCodigo(1);
				r.setMensaje("Posteo exitoso");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				r.setCodigo(-99);
				r.setMensaje("Fallido!");

			}
		}return r;
	}

}
