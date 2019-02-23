package dam.samuel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dam.samuel.modelo.HibernateUtil;
import dam.samuel.modelo.Juego;
import dam.samuel.modelo.Juego.EstiloJuego;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 *         Clase que ofrece consultas HQL específicas de los objetos Juego y que
 *         hereda de GenericDAO para poder realizar consultas comunes
 */
public class JuegoDAO extends GenericDAO<Juego> {

	/**
	 * Permite obtener a través de un Id un objeto que fue persistido anteriormente
	 * y se le asignó el identificador requerido
	 * 
	 * @param id por el que se desea obtener el objeto
	 * @return unico juego que coincida con el id deseado
	 */
	public Juego consultarPorId(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (Juego) session.get(Juego.class, id);
	}

	/**
	 * Permite obtener una lista con todos los juegos registrados en la aplicación
	 * 
	 * @return lista de juegos registrados y almacenados en la base de datos
	 */
	@SuppressWarnings("unchecked")
	public List<Juego> consultarTodas() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query consulta = session.createQuery("SELECT j FROM dam.samuel.modelo.Juego j ORDER BY nombre");
		return consulta.list();
	}

	/**
	 * Permite obtener una lista de juegos que pertenezcan al estilo indicado por
	 * parámetro
	 * 
	 * @param estilo de juego deseado
	 * @return lista de juegos que cumplan con el estilo deseado
	 */
	@SuppressWarnings("unchecked")
	public List<Juego> consultarPorEstilo(EstiloJuego estilo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query consulta = session.createQuery(
				"SELECT j FROM dam.samuel.modelo.Juego j WHERE estilo = '" + estilo.toString() + "' ORDER BY nombre");
		return consulta.list();
	}
}
