// Arquivo DepartmentDao.java

// Declaração do pacote model.dao
package model.dao;

// Importação da classe List do pacote java.util
import java.util.List;

// Importação da classe Department do pacote model.entities
import model.entities.Department;

/**
 * Interface DepartmentDao que contém métodos para manipular objetos Department
 * no banco de dados.
 */
public interface DepartmentDao {

	/**
	 * Insere um objeto Department no banco de dados.
	 * 
	 * @param obj Objeto Department a ser inserido.
	 */
	void insert(Department obj);

	/**
	 * Atualiza um objeto Department no banco de dados.
	 * 
	 * @param obj Objeto Department a ser atualizado.
	 */
	void update(Department obj);

	/**
	 * Remove um objeto Department do banco de dados a partir do seu ID.
	 * 
	 * @param id ID do objeto Department a ser removido.
	 */
	void deleteById(Integer id);

	/**
	 * Busca um objeto Department no banco de dados a partir do seu ID.
	 * 
	 * @param id ID do objeto Department a ser buscado.
	 * @return O objeto Department encontrado.
	 */
	Department findById(Integer id);

	/**
	 * Retorna uma lista com todos os objetos Department do banco de dados.
	 * 
	 * @return Uma lista com todos os objetos Department.
	 */
	List<Department> findAll();
}
