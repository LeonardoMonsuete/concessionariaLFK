package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Servico;
import dao.MecanicoDAO;
import dao.ClienteDAO;
import dao.AutomovelDAO;

public class ServicoDAO {
	public List<Servico> listAll() throws Exception {
		ArrayList<Servico> list = new ArrayList<Servico>();

		try {
			Connection con = AppConnection.getConnection();
			Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(
			          "SELECT numServico, numRegistro_Mecanico, codigo_Cliente, codigo_Automovel, dia, custo FROM Servico");
			  while (rs.next()) {
				  Servico s = new Servico();
				  s.setNumServico(rs.getInt("numServico"));
				  
				  MecanicoDAO m = new MecanicoDAO();
				  s.setMecanico(m.getMecanico(rs.getInt("numRegistro_Mecanico")));
				  
				  ClienteDAO c = new ClienteDAO();
				  s.setCliente(c.getCliente(rs.getInt("codigo_Cliente")));
				  
				  AutomovelDAO a = new AutomovelDAO();
				  s.setAutomovel(a.getAutomovel(rs.getInt("codigo_Automovel")));
				  
				  s.setDia(rs.getString("dia"));
				  s.setCusto(rs.getInt("custo"));
				  
				  list.add(s);
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return list;
	}
	
	public Servico getServico(int numServico) throws Exception {
		Servico s = null;
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					"SELECT numServico, numRegistro_Mecanico, codigo_Cliente, codigo_Automovel, dia, custo FROM Mecanico where numServico = ?");
			stmt.setInt(1, numServico);
			  ResultSet rs = stmt.executeQuery();
			  while (rs.next()) {
			      s = new Servico();
			      
				  s.setNumServico(rs.getInt("numServico"));
				  
				  MecanicoDAO m = new MecanicoDAO();
				  s.setMecanico(m.getMecanico(rs.getInt("numRegistro_Mecanico")));
				  
				  ClienteDAO c = new ClienteDAO();
				  s.setCliente(c.getCliente(rs.getInt("codigo_Cliente")));
				  
				  AutomovelDAO a = new AutomovelDAO();
				  s.setAutomovel(a.getAutomovel(rs.getInt("codigo_Automovel")));
				  
				  s.setDia(rs.getString("dia"));
				  s.setCusto(rs.getInt("custo"));
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return s;
	}

	
	public Servico inserir(Servico servico) throws Exception {
		String sqlInsert =
			"INSERT INTO Servico (numServico, numRegistro_Mecanico, codigo_Cliente, codigo_Automovel, dia, custo) VALUES ( ?, ?, ?, ?, ?, ? )";
		valida(servico, true);
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setInt(1, servico.getNumServico());
			ps.setInt(2, servico.getMecanico().getNumRegistro());
			ps.setInt(3, servico.getCliente().getCodigo());
			ps.setInt(4, servico.getAutomovel().getCodigo());
			ps.setString(5, servico.getDia());
			ps.setInt(6, servico.getCusto());
			
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi inserido por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return servico;
	}

	
	public Servico atualizar(Servico servico) throws Exception {
		String sqlInsert =
			"UPDATE Servico SET numRegistro_Mecanico, codigo_Cliente, codigo_Automovel, dia, custo where numServico = ?";
		valida(servico, false);
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			
			ps.setInt(1, servico.getMecanico().getNumRegistro());
			ps.setInt(2, servico.getCliente().getCodigo());
			ps.setInt(3, servico.getAutomovel().getCodigo());
			ps.setString(4, servico.getDia());
			ps.setInt(5, servico.getCusto());
			ps.setInt(6, servico.getNumServico());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi alterado por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return servico;
	}
	
	
	public Servico excluir(Servico servico) throws Exception {
		String sqlExcluir =
			"DELETE FROM Servico where numServico = ?";
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlExcluir);
			ps.setInt(1, servico.getNumServico());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi excluído por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return servico;
	}

	private void valida(Servico servico, boolean inserir) throws Exception {

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
					.executeQuery("SELECT numServico FROM Servico");
			while (rs.next()) {
				int id = rs.getInt("numServico");
				/*
				String nomeBD = rs.getString("nome");
				String cpfBD = rs.getString("cpf");
				*/
				// Quando está atualizando, se o ID for igual, não precisa comparar
				if (!inserir) {
					if (id == servico.getNumServico()) {
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
				servico.setNumServico(maxId + 1);
			}
			rs.close();
			stmt.close();

		} catch (Exception ex) {
			System.err.println("Erro de validação: " + ex);
			throw ex;
		}
	}
}
