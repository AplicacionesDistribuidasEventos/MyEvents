package modelo.myevents.ec.edu.ups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COMENTARIOS")
public class Comentario {
	@Id
	@Column(name="com_id")
	private int id;
	
	@Column(name="com_descripcion")
	private String descripcion;
		
	@Column(name="com_fecha")
	private String fecha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		System.out.println("GETTT  " +descripcion);
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		System.out.println("SETEADAAAA:"+descripcion);
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", descripcion=" + descripcion + ", fecha=" + fecha + "]";
	}

}
