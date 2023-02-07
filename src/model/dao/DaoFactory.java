package model.dao;

//package model.dao; - Declaração de pacote
import db.DB; // Importação da classe DB
import model.dao.impl.DepartmentDaoJDBC; // Importação da classe DepartmentDaoJDBC
import model.dao.impl.SellerDaoJDBC; // Importação da classe SellerDaoJDBC

public class DaoFactory { // Declaração da classe DaoFactory

	// Método para criar um SellerDao
	public static SellerDao createSellerDao() {
		// Retorna uma instância de SellerDaoJDBC com a conexão do BD
		return new SellerDaoJDBC(DB.getConnection());
	}

	// Método para criar um DepartmentDao
	public static DepartmentDao createDepartmentDao() {
		// Retorna uma instância de Department
		return new DepartmentDaoJDBC(DB.getConnection());
	}
	/*
	 * A classe DaoFactory é uma classe de fábrica responsável por fornecer uma
	 * instância de um objeto que implementa a interface SellerDao. A importância
	 * desta classe é centralizar a criação de objetos, permitindo a mudança da
	 * implementação de uma interface sem afetar o resto da aplicação. Isso também
	 * ajuda a manter o código mais organizado e facilitar futuras manutenções, já
	 * que a criação de objetos fica concentrada em uma única classe.
	 */
}
