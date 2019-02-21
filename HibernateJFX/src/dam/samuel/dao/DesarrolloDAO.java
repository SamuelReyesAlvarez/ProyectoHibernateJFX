package dam.samuel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dam.samuel.modelo.Empresa;
import dam.samuel.modelo.HibernateUtil;
import dam.samuel.modelo.Juego;

public class DesarrolloDAO {

	@SuppressWarnings("unchecked")
	public List<Juego> consultarJuegosPorEmpresa(Empresa empresa) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query consulta = session
				.createQuery("SELECT d.juego FROM dam.samuel.modelo.Desarrolla d WHERE d.empresa.idEmpresa = "
						+ empresa.getIdEmpresa());
		return consulta.list();
	}
}
