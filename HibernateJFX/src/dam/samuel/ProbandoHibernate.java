package dam.samuel;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import dam.samuel.dao.EmpresaDAO;
import dam.samuel.modelo.Empresa;
import dam.samuel.modelo.HibernateUtil;

public class ProbandoHibernate {

	private static Scanner teclado = new Scanner(System.in);

	private static EmpresaDAO empresaDAO = new EmpresaDAO();
	private static Session session;

	public static void main(String[] args) throws Exception {
		configurarSesion();

		String nombre = "";

		do {
			System.out.println("Nombre de empresa: ");
			nombre = teclado.nextLine();

			Empresa e = new Empresa(nombre);

			empresaDAO.guardar(e);
		} while (!nombre.contains("*"));

		List<Empresa> listado = empresaDAO.consultarTodas();

		for (Empresa empresa : listado) {
			System.out.println(empresa);
		}

		cerrarSesion();
	}

	private static void cerrarSesion() {
		HibernateUtil.closeSessionFactory();
	}

	private static void configurarSesion() {
		HibernateUtil.buildSessionFactory();
		HibernateUtil.openSessionAndBindToThread();
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
}
