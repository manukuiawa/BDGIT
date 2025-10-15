package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Jogo;

public class JogoDao {

	public Connection getConexao() {

		// Driver
		String driver = "com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		// URL: onde está o meu banco de dados
		String url = "jdbc:mysql://localhost:3306/sistemaJogos";

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


	// Inserir jogo
	public Jogo inserirJogo(Jogo jogoNovo) {
		String consulta = "INSERT INTO jogos (nome, genero, dataLancamento, nota) VALUES (?, ?, ?, ?)";

		try (Connection conn = getConexao();
				PreparedStatement pst = conn.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS)) {

			pst.setString(1, jogoNovo.getNome());
			pst.setString(2, jogoNovo.getGenero());
			pst.setDate(3, jogoNovo.getDataLancamento());
			pst.setDouble(4, jogoNovo.getNota());
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				jogoNovo.setId(id);
			}

			rs.close();
			System.out.println("✅ Jogo inserido com sucesso!");
			return jogoNovo;

		} catch (Exception e) {
			System.out.println("Erro ao inserir jogo:");
			e.printStackTrace();
			return null;
		}
	}
}
