package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Vendedor;

public class VendedorDAO {
	
	public List<Vendedor> listAll() throws Exception {
		ArrayList<Vendedor> list = new ArrayList<Vendedor>();

		try {
			Connection con = AppConnection.getConnection();
			Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(
			          "SELECT numRegistro, nome, datanasc, cpf, rg, salario FROM Vendedor");
			  while (rs.next()) {
				  Vendedor v = new Vendedor();
				  v.setNumRegistro(rs.getInt("numRegistro"));
				  v.setNome(rs.getString("nome"));
				  v.setDataNasc(rs.getString("datanasc"));
				  v.setCpf(rs.getString("cpf"));
				  v.setRg(rs.getString("rg"));
				  v.setSalario(rs.getInt("salario"));
				  list.add(v);
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return list;
	}
	
	public Vendedor getVendedor(int numRegistro) throws Exception {
		Vendedor v = null;
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					"SELECT numRegistro, nome, datanasc, cpf, rg, salario FROM Vendedor where numRegistro = ?");
			stmt.setInt(1, numRegistro);
			  ResultSet rs = stmt.executeQuery();
			  while (rs.next()) {
			      v = new Vendedor();
			      v.setNumRegistro(rs.getInt("numRegistro"));
				  v.setNome(rs.getString("nome"));
				  v.setDataNasc(rs.getString("datanasc"));
				  v.setCpf(rs.getString("cpf"));
				  v.setRg(rs.getString("rg"));
				  v.setSalario(rs.getInt("salario"));
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return v;
	}

	
	public Vendedor inserir(Vendedor vendedor) throws Exception {
		String sqlInsert =
			"INSERT INTO Vendedor (numRegistro, nome, datanasc, cpf, rg, salario) VALUES ( ?, ?, ?, ?, ?, ? )";
		valida(vendedor, true);
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setInt(1, vendedor.getNumRegistro());
			ps.setString(2, vendedor.getNome());
			ps.setString(3, vendedor.getDataNasc());
			ps.setString(4, vendedor.getCpf());
			ps.setString(5, vendedor.getRg());
			ps.setInt(6, vendedor.getSalario());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi inserido por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return vendedor;
	}

	
	public Vendedor atualizar(Vendedor vendedor) throws Exception {
		String sqlInsert =
			"UPDATE Vendedor SET nome = ?, datanasc = ?, cpf = ?, rg = ?, salario = ? where numRegistro = ?";
		valida(vendedor, false);
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setString(1, vendedor.getNome());
			ps.setString(2, vendedor.getDataNasc());
			ps.setString(3, vendedor.getCpf());
			ps.setString(4, vendedor.getRg());
			ps.setInt(5, vendedor.getSalario());
			ps.setInt(6, vendedor.getNumRegistro());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi alterado por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return vendedor;
	}
	
	
	public Vendedor excluir(Vendedor vendedor) throws Exception {
		String sqlExcluir =
			"DELETE FROM Vendedor where numRegistro = ?";
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlExcluir);
			ps.setInt(1, vendedor.getNumRegistro());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi excluído por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return vendedor;
	}

	private void valida(Vendedor vendedor, boolean inserir) throws Exception {

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
					.executeQuery("SELECT numRegistro FROM Vendedor");
			while (rs.next()) {
				int id = rs.getInt("numRegistro");
				/*
				String nomeBD = rs.getString("nome");
				String cpfBD = rs.getString("cpf");
				*/
				// Quando está atualizando, se o ID for igual, não precisa comparar
				if (!inserir) {
					if (id == vendedor.getNumRegistro()) {
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
				vendedor.setNumRegistro(maxId + 1);
			}
			rs.close();
			stmt.close();

		} catch (Exception ex) {
			System.err.println("Erro de validação: " + ex);
			throw ex;
		}
	}
}
