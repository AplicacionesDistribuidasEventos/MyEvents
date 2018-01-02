package modelo.myevents.ec.edu.ups;

public class CategoriaEventos {
	private String descripcion_cat;
	private String descripcion_eve;
	private String fecha;
	private String costo;
	/*Nombre de la categoria*/
	private String categoria;
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
/*
	public String getDescripcion_cat() {
		return descripcion_cat;
	}

	public void setDescripcion_cat(String descripcion_cat) {
		this.descripcion_cat = descripcion_cat;
	}
*/
	public String getDescripcion_eve() {
		return descripcion_eve;
	}

	public void setDescripcion_eve(String descripcion_eve) {
		this.descripcion_eve = descripcion_eve;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCosto() {
		return costo;
	}

	public void setCosto(String costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "CategoriaEventos [descripcion_cat=" + descripcion_cat + ", descripcion_eve=" + descripcion_eve
				+ ", fecha=" + fecha + ", costo=" + costo + ", categoria=" + categoria + "]";
	}
	
	

}
