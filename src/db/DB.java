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

	// Variável para armazenar a conexão com o banco de dados
	private static Connection conn = null;
	
	// Método para obter uma conexão com o banco de dados
	public static Connection getConnection() {
		// Verifica se já existe uma conexão
		if (conn == null) {
			try {
				// Carrega as propriedades do arquivo db.properties
				Properties props = loadProperties();
				
				// Obtém a URL de conexão com o banco de dados
				String url = props.getProperty("dburl");
				
				// Cria uma nova conexão com o banco de dados
				conn = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {
				// Lança uma exceção personalizada com a mensagem de erro
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	// Método para fechar a conexão com o banco de dados
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// Lança uma exceção personalizada com a mensagem de erro
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// Método para carregar as propriedades do arquivo db.properties
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			// Lança uma exceção personalizada com a mensagem de erro
			throw new DbException(e.getMessage());
		}
	}
	
	// Método para fechar um objeto Statement
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// Lança uma exceção personalizada com a mensagem de erro

				throw new DbException(e.getMessage());
			}
		}
	}
}
