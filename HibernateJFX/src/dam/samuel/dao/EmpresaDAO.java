package dam.samuel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dam.samuel.modelo.Empresa;
import dam.samuel.modelo.HibernateUtil;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class EmpresaDAO extends GenericDAO<Empresa> {

	public Empresa consultarPorId(Empresa empresa) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (Empresa) session.get(Empresa.class, empresa.getIdEmpresa());
	}

	@SuppressWarnings("unchecked")
	public List<Empresa> consultarTodas() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query consulta = session.createQuery("SELECT e FROM dam.samuel.modelo.Empresa e ORDER BY nombre");
		return consulta.list();
	}

	public Empresa consultarPorNombre(Empresa empresa) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (Empresa) session
				.createQuery("SELECT e FROM dam.samuel.modelo.Empresa e WHERE nombre = '" + empresa.getNombre() + "'")
				.uniqueResult();
	}
}
