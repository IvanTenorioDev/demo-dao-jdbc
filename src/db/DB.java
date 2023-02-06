// Este é um código Java para uma classe "DB" que representa uma conexão com um banco de dados
package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
// Propriedade estática para armazenar a conexão com o banco de dados
	private static Connection conn = null;

// Método para obter uma conexão com o banco de dados
	public static Connection getConnection() {
		// Verifica se a conexão já foi estabelecida
		if (conn == null) {
			try {
				// Carrega as propriedades de conexão a partir do arquivo db.properties
				Properties props = loadProperties();
				String url = props.getProperty("dburl");

				// Obtém a conexão com o banco de dados
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				// Lança uma exceção personalizada caso ocorra uma exceção de banco de dados
				throw new DbException(e.getMessage());
			}
		}
		// Retorna a conexão com o banco de dados
		return conn;
	}

// Método para fechar a conexão com o banco de dados
	public static void closeConnection() {
		// Verifica se a conexão está aberta
		if (conn != null) {
			try {
				// Fecha a conexão com o banco de dados
				conn.close();
			} catch (SQLException e) {
				// Lança uma exceção personalizada caso ocorra uma exceção de banco de dados
				throw new DbException(e.getMessage());
			}
		}
	}

// Método para carregar as propriedades de conexão a partir do arquivo db.properties
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			// Lança uma exceção personalizada caso ocorra uma exceção de entrada/saída
			throw new DbException(e.getMessage());
		}
	}

// Método para fechar um objeto "Statement"
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
// Lança uma exceção personalizada "DbException" caso haja erro ao fechar o statement
				throw new DbException(e.getMessage());
			}
		}
	}

// Método para fechar um objeto "ResultSet"
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
// Lança uma exceção personalizada "DbException" caso haja erro ao fechar o result set
				throw new DbException(e.getMessage());
			}
		}
	}
}