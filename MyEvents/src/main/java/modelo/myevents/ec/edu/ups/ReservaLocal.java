package modelo.myevents.ec.edu.ups;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	/** The estado. */
	@Column(name="estado_reserva")
	private String estado;
	
	@Column(name="fecha_reserva")
	@Temporal(value=TemporalType.DATE)
	private Date fechaReserva;

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

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	@Override
	public String toString() {
		return "ReservaLocal [id=" + id + ", estado=" + estado + ", fechaReserva=" + fechaReserva + "]";
	}


}