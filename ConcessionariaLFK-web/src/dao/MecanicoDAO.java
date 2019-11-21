package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Mecanico;

public class MecanicoDAO {
	public List<Mecanico> listAll() throws Exception {
		ArrayList<Mecanico> list = new ArrayList<Mecanico>();

		try {
			Connection con = AppConnection.getConnection();
			Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(
			          "SELECT numRegistro, nome, datanasc, cpf, rg, salario FROM Mecanico");
			  while (rs.next()) {
				  Mecanico m = new Mecanico();
				  m.setNumRegistro(rs.getInt("numRegistro"));
				  m.setNome(rs.getString("nome"));
				  m.setDataNasc(rs.getString("datanasc"));
				  m.setCpf(rs.getString("cpf"));
				  m.setRg(rs.getString("rg"));
				  m.setSalario(rs.getInt("salario"));
				  list.add(m);
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return list;
	}
	
	public Mecanico getMecanico(int numRegistro) throws Exception {
		Mecanico m = null;
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					"SELECT numRegistro, nome, datanasc, cpf, rg, salario FROM Mecanico where numRegistro = ?");
			stmt.setInt(1, numRegistro);
			  ResultSet rs = stmt.executeQuery();
			  while (rs.next()) {
			      m = new Mecanico();
			      m.setNumRegistro(rs.getInt("numRegistro"));
				  m.setNome(rs.getString("nome"));
				  m.setDataNasc(rs.getString("datanasc"));
				  m.setCpf(rs.getString("cpf"));
				  m.setRg(rs.getString("rg"));
				  m.setSalario(rs.getInt("salario"));
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return m;
	}

	
	public Mecanico inserir(Mecanico mecanico) throws Exception {
		String sqlInsert =
			"INSERT INTO Mecanico (numRegistro, nome, datanasc, cpf, rg, salario) VALUES ( ?, ?, ?, ?, ?, ? )";
		valida(mecanico, true);
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setInt(1, mecanico.getNumRegistro());
			ps.setString(2, mecanico.getNome());
			ps.setString(3, mecanico.getDataNasc());
			ps.setString(4, mecanico.getCpf());
			ps.setString(5, mecanico.getRg());
			ps.setInt(6, mecanico.getSalario());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi inserido por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return mecanico;
	}

	
	public Mecanico atualizar(Mecanico mecanico) throws Exception {
		String sqlInsert =
			"UPDATE Mecanico SET nome = ?, datanasc = ?, cpf = ?, rg = ?, salario = ? where numRegistro = ?";
		valida(mecanico, false);
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setString(1, mecanico.getNome());
			ps.setString(2, mecanico.getDataNasc());
			ps.setString(3, mecanico.getCpf());
			ps.setString(4, mecanico.getRg());
			ps.setInt(5, mecanico.getSalario());
			ps.setInt(6, mecanico.getNumRegistro());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi alterado por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return mecanico;
	}
	
	
	public Mecanico excluir(Mecanico mecanico) throws Exception {
		String sqlExcluir =
			"DELETE FROM Mecanico where numRegistro = ?";
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlExcluir);
			ps.setInt(1, mecanico.getNumRegistro());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi excluído por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return mecanico;
	}

	private void valida(Mecanico mecanico, boolean inserir) throws Exception {

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
					.executeQuery("SELECT numRegistro FROM Mecanico");
			while (rs.next()) {
				int id = rs.getInt("numRegistro");
				/*
				String nomeBD = rs.getString("nome");
				String cpfBD = rs.getString("cpf");
				*/
				// Quando está atualizando, se o ID for igual, não precisa comparar
				if (!inserir) {
					if (id == mecanico.getNumRegistro()) {
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
				mecanico.setNumRegistro(maxId + 1);
			}
			rs.close();
			stmt.close();

		} catch (Exception ex) {
			System.err.println("Erro de validação: " + ex);
			throw ex;
		}
	}
}
