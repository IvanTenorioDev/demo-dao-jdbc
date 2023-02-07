package model.dao.impl;

//Importações necessárias
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao { //Classe que implementa o DAO do  Department

	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) { //Construtor que recebe uma conexão
		this.conn = conn;
	}
	
	@Override
	public Department findById(Integer id) { //Método para buscar um departmento por Id
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement( //Prepara a query para buscar o departmento
				"SELECT * FROM department WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) { //Se encontrar o departmento
				Department obj = new Department(); //Cria um novo departmento
				obj.setId(rs.getInt("Id")); //Seta os atributos
				obj.setName(rs.getString("Name"));
				return obj; //Retorna o objeto
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() { //Método para buscar todos os departments
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement( //Prepara a query para buscar todos os departments
				"SELECT * FROM department ORDER BY Name");
			rs = st.executeQuery();

			List<Department> list = new ArrayList<>(); //Cria uma lista de departments

			while (rs.next()) {
				Department obj = new Department(); //Cria um novo departmento
				obj.setId(rs.getInt("Id")); //Seta os atributos
				obj.setName(rs.getString("Name"));
				list.add(obj); //Adiciona o objeto a lista
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(Department obj) { //Método para inserir um novo departmento
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO department " +
				"(Name) " +
				"VALUES " +
				"(?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName()); //Seta o nome do departmento

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1); //Pega o Id do departmento
					obj.setId(id); //Seta o Id no objeto
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Department obj) { //Método para atualizar um departmento
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE department " +
				"SET Name = ? " +
				"WHERE Id = ?");

			st.setString(1, obj.getName()); //Seta o novo nome do departmento
			st.setInt(2, obj.getId()); //Seta o Id do departmento

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) { //Método para deletar um departmento
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM department WHERE Id = ?");

			st.setInt(1, id); //Seta o Id do departmento

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}
}
