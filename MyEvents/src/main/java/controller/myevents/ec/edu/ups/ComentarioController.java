package controller.myevents.ec.edu.ups;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.ComentarioDAO;
import dao.myevents.ec.edu.ups.LocalDAO;
import dao.myevents.ec.edu.ups.PersonaDAO;
import modelo.myevents.ec.edu.ups.Comentario;
import modelo.myevents.ec.edu.ups.Local;
import modelo.myevents.ec.edu.ups.Persona;
import modelo.myevents.ec.edu.ups.PersonaLocalComentario;
import servicios.myevents.ec.edu.ups.Respuesta;

@ManagedBean
//@ViewScoped
public class ComentarioController {

	@Inject
	private ComentarioDAO codao;

	@Inject
	private PersonaDAO pdao;

	@Inject
	private LocalDAO ldao;

	private Comentario comentario;

	@PostConstruct
	public void init() {
		comentario = new Comentario();
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	/*
	 * public Respuesta guardarComentario() { Respuesta r = new Respuesta(); try {
	 * codao.insertComentario(comentario); r.setCodigo(1);
	 * r.setMensaje("Enviado exitoso"); } catch (Exception e) { // TODO: handle
	 * exception r.setCodigo(-99); r.setMensaje("Posteado fallido");
	 * e.printStackTrace(); } return r; }
	 */
	public List<Comentario> listadoComentarios() {
		return codao.listComentario();
	}

	/**
	 * METODO QUE DEVUELVE LOS COMENTARIOS DE UN RESPECTIVO LOCAL, LOS COMENTARIOS
	 * SON DEVUELTOS JUNTO AL USUARIO QUIEN LO GENERO METODO DE TIPO FACHADA DE
	 * NEGOCIO
	 */
	public List<PersonaLocalComentario> listPLC() {
		List<PersonaLocalComentario> plcl = new ArrayList<PersonaLocalComentario>();
		PersonaLocalComentario plc = new PersonaLocalComentario();
		List<Persona> personas = pdao.listPersonas();
		Local localselect = ldao.leerLocal(LocalController.id_loc_com);
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
	 * METODO PARA LA INSERCION DE UN COMENTARIO, DONDE SE RECUPERA EL OBJETO TANTO
	 * DE PERSONA COMO LOCAL MEDIANTE SUS RESPECTIVOS IDs
	 */
	public void insertarComentario() {
		System.out.println("COMENTARIODESCRIPCION:    " + comentario.getDescripcion());
		System.out.println("INGRESAA A INSERTAR COMENTARIOS");
		Local local = ldao.leerLocal(LocalController.id_loc_com);
		Persona persona = pdao.selectPersona(PersonaController.idUsuario);
		if(comentario.getDescripcion()!=null) {
			int idMax = codao.MaxID();
			Date fechadDate = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String fecha = formatter.format(fechadDate);
			comentario.setId(idMax);
			comentario.setFecha(fecha);
			codao.insertComentario(comentario);
			System.out.println("COMENTARIO A INGRESAR:    " + comentario);

			
			 local.getComentarios().add(comentario);
			 ldao.updateLocal(local);
			 persona.getComentarios().add(comentario);
			 pdao.updatePersona(persona);	
			 comentario.setDescripcion(null);
		}
		// return null;
	}

}
