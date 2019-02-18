package dam.samuel.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
@Entity
@Table(name = "desarrolla")
public class Desarrolla implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	@NotNull
	@Valid
	private Empresa empresa;

	@Id
	@ManyToOne
	@JoinColumn(name = "idJuego")
	@NotNull
	@Valid
	private Juego juego;

	public Desarrolla(Empresa empresa, Juego juego) {
		this.empresa = empresa;
		this.juego = juego;
	}

	public Desarrolla() {
		super();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	@Override
	public String toString() {
		return "Desarrolla [idEmpresa=" + empresa + ", idJuego=" + juego + "]";
	}
}
