package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	// Cria a conexão com o banco de dados
	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	// Método que retorna um vendedor de acordo com o id
	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null; // declara uma variavél st que armazenará o objeto PreparedStatement
		ResultSet rs = null; // declara uma variável rs que armazena o objeto ResultSet
		try {
			// cria um objeto PreparedStatement com uma query sql para obter os dados do
			// vendedor de acordo com o id
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");

			// define o parâmetro da query sql
			st.setInt(1, id);
			// executa a query sql e armazena o resultado no objeto ResultSet
			rs = st.executeQuery();
			if (rs.next()) { // se conseguiu obter os dados
				// cria um objeto Department a partir dos dados no ResultSet
				Department dep = instantiateDepartment(rs);
				// cria um objeto Seller a partir dos dados no ResutSet
				Seller obj = instantiateSeller(rs, dep);
				return obj; // retorna o objeto Seller criado
			}
			return null; // caso contrário retorna null
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st); // fecha o objeto PreparedStatement
			DB.closeResultSet(rs); // fecha o objeto ResultSet
		}
	}

	// cria um objeto Department a partir dos dados no ResultSet
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	// cria um objeto Seller a partir dos dados no ResutSet
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// cria um objeto PreparedStatement com uma query sql para obter os dados dos
			// vendedores de acordo com o departamento
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "ORDER BY Name");

			// executa a query sql e armazena o resultado no objeto ResultSet
			rs = st.executeQuery();

			List<Seller> list = new ArrayList<>(); // cria uma lista de Seller
			Map<Integer, Department> map = new HashMap<>(); // cria um mapa para armazenar os objetos Department

			// enquanto houver dados no ResultSet
			while (rs.next()) {

				Department dep = map.get(rs.getInt("DepartmentId")); // obtém o objeto Department no mapa

				// se o mapa não tiver um objeto Department para o id obtido
				if (dep == null) {
					// cria um objeto Department a partir dos dados no ResultSet
					dep = instantiateDepartment(rs);
					// armazena o objeto Department no mapa
					map.put(rs.getInt("DepartmentId"), dep);
				}

				// cria um objeto Seller a partir dos dados no ResutSet
				Seller obj = instantiateSeller(rs, dep);
				list.add(obj); // adiciona o objeto Seller à lista
			}
			return list; // retorna a lista de Seller
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st); // fecha o objeto PreparedStatement
			DB.closeResultSet(rs); // fecha o objeto ResultSet
		}
	}

	// Método que retorna uma lista de vendedores de acordo com o DEPARTAMENTO
	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// cria um objeto PreparedStatement com uma query sql para obter os dados dos
			// vendedores de acordo com o departamento
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name");

			// define o parâmetro da query sql
			st.setInt(1, department.getId());
			// executa a query sql e armazena o resultado no objeto ResultSet
			rs = st.executeQuery();

			List<Seller> list = new ArrayList<>(); // cria uma lista de Seller
			Map<Integer, Department> map = new HashMap<>(); // cria um mapa para armazenar os objetos Department

			// enquanto houver dados no ResultSet
			while (rs.next()) {

				Department dep = map.get(rs.getInt("DepartmentId")); // obtém o objeto Department no mapa

				// se o mapa não tiver um objeto Department para o id obtido
				if (dep == null) {
					// cria um objeto Department a partir dos dados no ResultSet
					dep = instantiateDepartment(rs);
					// armazena o objeto Department no mapa
					map.put(rs.getInt("DepartmentId"), dep);
				}

				// cria um objeto Seller a partir dos dados no ResutSet
				Seller obj = instantiateSeller(rs, dep);
				list.add(obj); // adiciona o objeto Seller à lista
			}
			return list; // retorna a lista de Seller
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st); // fecha o objeto PreparedStatement
			DB.closeResultSet(rs); // fecha o objeto ResultSet
		}
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

}