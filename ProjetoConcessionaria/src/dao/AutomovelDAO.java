package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Automovel;


public class AutomovelDAO {
	
	public List<Automovel> listAll() throws Exception {
		ArrayList<Automovel> list = new ArrayList<Automovel>();

		try {
			Connection con = AppConnection.getConnection();
			Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(
			          "SELECT codigo, modelo, ano, cilindrada, tipo FROM Automovel");
			  while (rs.next()) {
				  Automovel a = new Automovel();
				  a.setCodigo(rs.getInt("codigo"));
				  a.setModelo(rs.getString("modelo"));
				  a.setAno(rs.getInt("ano"));
				  a.setCilindrada(rs.getInt("cilindrada"));
				  a.setTipo(rs.getInt("tipo"));
				  list.add(a);
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return list;
	}
	
	public Automovel getAutomovel(int codigo) throws Exception {
		Automovel a = null;
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					"SELECT codigo, modelo, ano, cilindrada, tipo FROM Automovel where codigo = ?");
			stmt.setInt(1, codigo);
			  ResultSet rs = stmt.executeQuery();
			  while (rs.next()) {
			      a = new Automovel();
			      a.setCodigo(rs.getInt("codigo"));
				  a.setModelo(rs.getString("modelo"));
				  a.setAno(rs.getInt("ano"));
				  a.setCilindrada(rs.getInt("cilindrada"));
				  a.setTipo(rs.getInt("tipo"));
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return a;
	}

	
	public Automovel inserir(Automovel automovel) throws Exception {
		String sqlInsert =
			"INSERT INTO Automovel (codigo, modelo, ano, cilindrada, tipo) VALUES ( ?, ?, ?, ?, ? )";
		valida(automovel, true);
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setInt(1, automovel.getCodigo());
			ps.setString(2, automovel.getModelo());
			ps.setInt(3, automovel.getAno());
			ps.setInt(4, automovel.getCilindrada());
			ps.setInt(5, automovel.getTipo());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi inserido por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return automovel;
	}

	
	public Automovel atualizar(Automovel automovel) throws Exception {
		String sqlInsert =
			"UPDATE Pessoa SET modelo = ?, ano = ?, cilindrada = ? where codigo = ?";
		valida(automovel, false);
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setString(1, automovel.getModelo());
			ps.setInt(2, automovel.getAno());
			ps.setInt(3, automovel.getCilindrada());
			ps.setInt(4, automovel.getCodigo());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi alterado por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return automovel;
	}
	
	
	public Automovel excluir(Automovel automovel) throws Exception {
		String sqlExcluir =
			"DELETE FROM Automovel where codigo = ?";
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlExcluir);
			ps.setInt(1, automovel.getCodigo());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi excluído por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return automovel;
	}

	private void valida(Automovel automovel, boolean inserir) throws Exception {

		// Verifica se nome não é nulo ou vazio.
		/*
		if (automovel.getNome() == null || pessoa.getNome().trim().length() == 0) {
			throw new Exception("Nome não pode ser nulo");
		}

		// Verifica se nome não é nulo ou vazio.
		if (pessoa.getCpf() == null || pessoa.getCpf().trim().length() == 0) {
			throw new Exception("Cpf não pode ser nulo");
		}
		*/

		try {
			int maxId = 0;
			Connection con = AppConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT codigo FROM Automovel");
			while (rs.next()) {
				int id = rs.getInt("codigo");
				/*
				String nomeBD = rs.getString("nome");
				String cpfBD = rs.getString("cpf");
				*/
				// Quando está atualizando, se o ID for igual, não precisa comparar
				if (!inserir) {
					if (id == automovel.getCodigo()) {
						continue;
					}
				}
				
				// Verifica o nome e cpf.
				/*
				if (pessoa.getNome().equals(nomeBD)) {
					throw new Exception("Nome já existe em " + id);
				}
				if (pessoa.getCpf().equals(cpfBD)) {
					throw new Exception("Cpf já existe em " + id);
				}
				*/
				// Aproveita para calcular o max id.
				if (maxId < id) {
					maxId = id;
				}
			}
			
			// Só altera o ID se for inserir, atualizar não.
			if (inserir) {
				automovel.setCodigo(maxId + 1);
			}
			rs.close();
			stmt.close();

		} catch (Exception ex) {
			System.err.println("Erro de validação: " + ex);
			throw ex;
		}
	}
}
