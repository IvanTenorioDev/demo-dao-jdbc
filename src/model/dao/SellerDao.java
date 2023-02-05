package model.dao;

import java.util.List;

import model.entities.Seller;

/**
 * Interface que representa as operações de persistência para objetos do tipo
 * Seller.
 */
public interface SellerDao {

	/**
	 * Insere um objeto do tipo Seller no banco de dados.
	 * 
	 * @param obj Objeto do tipo Seller a ser inserido.
	 */
	void insert(Seller obj);

	/**
	 * Atualiza as informações de um objeto do tipo Seller no banco de dados.
	 * 
	 * @param obj Objeto do tipo Seller com as informações atualizadas.
	 */
	void update(Seller obj);

	/**
	 * Remove um objeto do tipo Seller do banco de dados, a partir do seu id.
	 * 
	 * @param id Id do objeto a ser removido.
	 */
	void deleteById(Integer id);

	/**
	 * Busca um objeto do tipo Seller no banco de dados, a partir do seu id.
	 * 
	 * @param id Id do objeto a ser buscado.
	 * @return O objeto do tipo Seller encontrado.
	 */
	Seller findById(Integer id);

	/**
	 * Busca todos os objetos do tipo Seller no banco de dados.
	 * 
	 * @return Lista de objetos do tipo Seller encontrados.
	 */
	List<Seller> findAll();
}