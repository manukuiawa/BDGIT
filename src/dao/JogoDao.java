package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class JogoDao {

	public Connection getConexao() {

		// Driver

		String driver = "com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// URL: onde está o meu banco de dados

		String url = "jdbc:mysql://localhost:3306/ies";

		// Senha
		String senha = "0166";

		// Usuário
		String user = "root";

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, senha);
			System.out.println("Banco de Dados conectado!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//Cadastrar Jogo 
	

	}
