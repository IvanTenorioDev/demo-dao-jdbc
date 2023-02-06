package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
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
