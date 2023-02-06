package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

//Constructor que recebe uma conexão como parâmetro e atribui ao atributo da classe
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

//Método para inserir vendedor
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

//Método para atualizar vendedor
	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

//Método para excluir vendedor por id
	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

//Método para encontrar vendedor por id
	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// Comando SQL para buscar vendedor pelo id e o departamento associado
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ? ");

			// definindo o id para busca
			st.setInt(1, id);
			rs = st.executeQuery();

			// se houver um resultado na busca
			if (rs.next()) {
				// criando o objeto do tipo Department
				Department dep = instantiateDepartment(rs);

				// criando o objeto do tipo Seller
				Seller obj = instantiateSeller(rs, dep);

				// retornando o objeto vendedor
				return obj;
			}

			// retornando null caso não haja um resultado
			return null;
		} catch (SQLException e) {
			// caso ocorra uma exceção, é lançada uma excessão do tipo DbException com a
			// mensagem de erro
			throw new DbException(e.getMessage());
		} finally {
			// fechando os recursos abertos pela consulta ao banco de dados
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		// Criação de um objeto vendedor
		Seller obj = new Seller();

		// Preenchimento do id do vendedor com o valor obtido do resultado da consulta
		// ao banco de dados
		obj.setId(rs.getInt("Id"));

		// Preenchimento do nome do vendedor com o valor obtido do resultado da consulta
		// ao banco de dados
		obj.setName(rs.getString("Name"));

		// Preenchimento do email do vendedor com o valor obtido do resultado da
		// consulta ao banco de dados
		obj.setEmail(rs.getString("Email"));

		// Preenchimento do salário base do vendedor com o valor obtido do resultado da
		// consulta ao banco de dados
		obj.setBaseSalary(rs.getDouble("BaseSalary"));

		// Preenchimento da data de nascimento do vendedor com o valor obtido do
		// resultado da consulta ao banco de dados
		obj.setBirthDate(rs.getDate("BirthDate"));

		// Definindo o departamento do vendedor
		obj.setDepartment(dep);

		// Retornando o objeto vendedor preenchido com os valores obtidos do banco de
		// dados
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		// Criando um novo objeto do tipo Department
		Department dep = new Department();

		// Definindo o id do departamento com o valor obtido na busca
		dep.setId(rs.getInt("DepartmentId"));

		// Definindo o nome do departamento com o valor obtido na busca
		dep.setName(rs.getString("DepName"));

		// retornando o objeto departamento
		return dep;
	}

	// Método para buscar todos os vendedores
	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}