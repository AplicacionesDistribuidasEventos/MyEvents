package modelo.myevents.ec.edu.ups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class SalonRecepcion.
 */
@Entity
@Table(name="RESERVAlOCAL")
public class ReservaLocal {
	
	/** The id. */
	@Id
	@Column(name="id_reserva")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	/** The estado. */
	@Column(name="estado_reserva")
	private String estado;
	
	@Column(name="fecha_reserva")
	private String fechaReserva;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	

	public String getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	@Override
	public String toString() {
		return "ReservaLocal [id=" + id + ", estado=" + estado + ", fechaReserva=" + fechaReserva + "]";
	}


}