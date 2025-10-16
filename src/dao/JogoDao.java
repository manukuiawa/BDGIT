package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			//System.out.println("Banco de Dados conectado!");
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
			return jogoNovo;

		} catch (Exception e) {
			System.out.println("Erro ao inserir jogo:");
			e.printStackTrace();
			return null;
		}
	}
	
	//Listar Jogos
	public List<Jogo> listarJogos() {
		List<Jogo> jogos = new ArrayList<>();
		String consulta = "select * from jogos";

		try {
			Connection conn = getConexao();
			PreparedStatement pst = conn.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String genero = rs.getString(3);
				Date dataLancamento = rs.getDate(4);
				Double nota = rs.getDouble(5);
				Jogo jogo = new Jogo(id, nome, genero, dataLancamento, nota);
				jogos.add(jogo);
			}

			rs.close();
			pst.close();
			conn.close();
			return jogos;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jogos;
	}
}
