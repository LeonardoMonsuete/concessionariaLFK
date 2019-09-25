package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Cliente;

public class ClienteDAO {
	
	public List<Cliente> listAll() throws Exception {
		ArrayList<Cliente> list = new ArrayList<Cliente>();

		try {
			Connection con = AppConnection.getConnection();
			Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(
			          "SELECT codigo, nome, datanasc, cpf, rg FROM Cliente");
			  while (rs.next()) {
				  Cliente c = new Cliente();
				  c.setCodigo(rs.getInt("codigo"));
				  c.setNome(rs.getString("nome"));
				  c.setDataNasc(rs.getString("datanasc"));
				  c.setCpf(rs.getString("cpf"));
				  c.setRg(rs.getString("rg"));
				  list.add(c);
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return list;
	}
	
	public Cliente getCliente(int codigo) throws Exception {
		Cliente c = null;
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					"SELECT codigo, nome, datanasc, cpf, rg FROM Cliente where codigo = ?");
			stmt.setInt(1, codigo);
			  ResultSet rs = stmt.executeQuery();
			  while (rs.next()) {
			      c = new Cliente();
			      c.setCodigo(rs.getInt("codigo"));
				  c.setNome(rs.getString("nome"));
				  c.setDataNasc(rs.getString("datanasc"));
				  c.setCpf(rs.getString("cpf"));
				  c.setRg(rs.getString("rg"));
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return c;
	}

	
	public Cliente inserir(Cliente cliente) throws Exception {
		String sqlInsert =
			"INSERT INTO Cliente (codigo, nome, datanasc, cpf, rg) VALUES ( ?, ?, ?, ?, ? )";
		valida(cliente, true);
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setInt(1, cliente.getCodigo());
			ps.setString(2, cliente.getNome());
			ps.setString(3, cliente.getDataNasc());
			ps.setString(4, cliente.getCpf());
			ps.setString(5, cliente.getRg());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi inserido por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return cliente;
	}

	
	public Cliente atualizar(Cliente cliente) throws Exception {
		String sqlInsert =
			"UPDATE Cliente SET nome = ?, datanasc = ?, cpf = ?, rg = ? where codigo = ?";
		valida(cliente, false);
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getDataNasc());
			ps.setString(3, cliente.getCpf());
			ps.setString(4, cliente.getRg());
			ps.setInt(5, cliente.getCodigo());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi alterado por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return cliente;
	}
	
	
	public Cliente excluir(Cliente cliente) throws Exception {
		String sqlExcluir =
			"DELETE FROM Cliente where codigo = ?";
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlExcluir);
			ps.setInt(1, cliente.getCodigo());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi excluído por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return cliente;
	}

	private void valida(Cliente cliente, boolean inserir) throws Exception {

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
					.executeQuery("SELECT codigo FROM Cliente");
			while (rs.next()) {
				int id = rs.getInt("codigo");
				/*
				String nomeBD = rs.getString("nome");
				String cpfBD = rs.getString("cpf");
				*/
				// Quando está atualizando, se o ID for igual, não precisa comparar
				if (!inserir) {
					if (id == cliente.getCodigo()) {
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
				cliente.setCodigo(maxId + 1);
			}
			rs.close();
			stmt.close();

		} catch (Exception ex) {
			System.err.println("Erro de validação: " + ex);
			throw ex;
		}
	}
}
