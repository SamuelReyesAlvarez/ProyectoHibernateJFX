package dam.samuel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dam.samuel.modelo.Desarrolla;
import dam.samuel.modelo.Empresa;
import dam.samuel.modelo.HibernateUtil;
import dam.samuel.modelo.Juego;

/**
 * 
 * @author Samuel Reyes Álvarez
 * 
 *         Esta clase permite gestionar las consultas HQL propias de la clase
 *         Desarrolla y hereda de GenericDAO el cual le ofrece consultas
 *         generales
 *
 */
public class DesarrolloDAO extends GenericDAO<Desarrolla> {

	/**
	 * Con este método es posible obtener una lista de juegos que fueron
	 * desarrollados por una determinada empresa
	 * 
	 * @param empresa es la que ha desarrollado los juegos
	 * @return lista son los juegos desarrollados por la empresa especificada por
	 *         parámetro
	 */
	@SuppressWarnings("unchecked")
	public List<Juego> consultarJuegosPorEmpresa(Empresa empresa) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query consulta = session
				.createQuery("SELECT d.juego FROM dam.samuel.modelo.Desarrolla d WHERE d.empresa.idEmpresa = "
						+ empresa.getIdEmpresa());
		return consulta.list();
	}
}
