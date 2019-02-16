package dam.samuel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dam.samuel.modelo.Empresa;
import dam.samuel.modelo.HibernateUtil;

public class EmpresaDAO extends GenericDAO<Empresa> {

	public Empresa consultarPorId(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Empresa empresa = (Empresa) session.get(Empresa.class, id);
		return empresa;
	}

	@SuppressWarnings("unchecked")
	public List<Empresa> consultarTodas() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query consulta = session.createQuery("SELECT e FROM empresa e");
		return consulta.list();
	}
}
