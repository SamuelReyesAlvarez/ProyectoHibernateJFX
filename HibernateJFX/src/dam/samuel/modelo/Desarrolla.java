package dam.samuel.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
	@Column(name = "idDesarrolla")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idDesarrolla;

	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	@NotNull
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "idJuego")
	@NotNull
	private Juego juego;

	public Desarrolla(Empresa empresa, Juego juego) {
		this.empresa = empresa;
		this.juego = juego;
	}

	public Desarrolla() {
		super();
	}

	public int getIdDesarrolla() {
		return idDesarrolla;
	}

	public void setIdDesarrolla(int idDesarrolla) {
		this.idDesarrolla = idDesarrolla;
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
		return "Desarrolla [idDesarrolla=" + idDesarrolla + ", empresa=" + empresa + ", juego=" + juego + "]";
	}
}
