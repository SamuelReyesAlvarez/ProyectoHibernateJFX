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
 */
public class JuegoDAO extends GenericDAO<Juego> {

	public Juego consultarPorId(Juego juego) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (Juego) session.get(Juego.class, juego.getIdJuego());
	}

	@SuppressWarnings("unchecked")
	public List<Juego> consultarTodas() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query consulta = session.createQuery("SELECT j FROM dam.samuel.modelo.Juego j ORDER BY nombre");
		return consulta.list();
	}

	@SuppressWarnings("unchecked")
	public List<Juego> consultarPorEstilo(EstiloJuego estilo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query consulta = session.createQuery(
				"SELECT j FROM dam.samuel.modelo.Juego j WHERE estilo = '" + estilo.toString() + "' ORDER BY nombre");
		return consulta.list();
	}
}
