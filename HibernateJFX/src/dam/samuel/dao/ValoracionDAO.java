package dam.samuel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dam.samuel.modelo.HibernateUtil;
import dam.samuel.modelo.Juego;
import dam.samuel.modelo.Valoracion;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class ValoracionDAO extends GenericDAO<Valoracion> {

	@SuppressWarnings("unchecked")
	public List<Valoracion> consultarPorJuego(Juego juego) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query consulta = session
				.createQuery("SELECT v FROM Valoracion v INNER JOIN v.juego j WHERE j.idJuego = " + juego.getIdJuego());
		return consulta.list();
	}
}
