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
 *         Clase que genera objetos que relacionan las empresas con los juegos
 *         que desarrollan. Surge de la relación N:M entre Empresa y Juego y es
 *         necesaria para persistir la unión en la base de datos
 */
@Entity
@Table(name = "desarrolla")
public class Desarrolla implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Dato autogenerado que identifica de forma única a este objeto de los de su
	 * clase
	 */
	@Id
	@Column(name = "idDesarrolla")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idDesarrolla;

	/**
	 * Identificador de la Empresa que desarrolló el juego
	 */
	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	@NotNull
	private Empresa empresa;

	/**
	 * Juego que fue desarrollado por la empresa indicada
	 */
	@ManyToOne
	@JoinColumn(name = "idJuego")
	@NotNull
	private Juego juego;

	/**
	 * Contructor principal para crear la relación
	 * 
	 * @param empresa que desarrolla
	 * @param juego   que fue desarrollado
	 */
	public Desarrolla(Empresa empresa, Juego juego) {
		this.empresa = empresa;
		this.juego = juego;
	}

	/**
	 * Constructor estándar
	 */
	public Desarrolla() {
		super();
	}

	/**
	 * Devuelve el identificador
	 * 
	 * @return idDesarrolla
	 */
	public int getIdDesarrolla() {
		return idDesarrolla;
	}

	/**
	 * Establece el identificador
	 * 
	 * @param idDesarrolla es el identificador de la relación entre el juego y la
	 *                     empresa
	 */
	public void setIdDesarrolla(int idDesarrolla) {
		this.idDesarrolla = idDesarrolla;
	}

	/**
	 * Devuelve la empresa que desarrolló el juego
	 * 
	 * @return empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * Establece la empresa que desarrolló el juego
	 * 
	 * @param empresa es la desarrolladora del juego
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * Devuelve el juego desarrollado por la empresa
	 * 
	 * @return juego
	 */
	public Juego getJuego() {
		return juego;
	}

	/**
	 * Establece el juego desarrollado por la empresa
	 * 
	 * @param juego es el desarrollado por la empresa
	 */
	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	/**
	 * Muestra la información principal del objeto
	 */
	@Override
	public String toString() {
		return "Desarrolla [idDesarrolla=" + idDesarrolla + ", empresa=" + empresa + ", juego=" + juego + "]";
	}
}
