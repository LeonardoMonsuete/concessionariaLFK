package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Venda;
import dao.VendedorDAO;
import dao.ClienteDAO;
import dao.AutomovelDAO;

public class VendaDAO {
	public List<Venda> listAll() throws Exception {
		ArrayList<Venda> list = new ArrayList<Venda>();

		try {
			Connection con = AppConnection.getConnection();
			Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(
			          "SELECT numVenda, numRegistro_Vendedor, codigo_Cliente, codigo_Automovel, dia, valor FROM Venda");
			  while (rs.next()) {
				  Venda v = new Venda();
				  v.setNumVenda(rs.getInt("numVenda"));
				  
				  VendedorDAO vd = new VendedorDAO();
				  v.setVendedor(vd.getVendedor(rs.getInt("numRegistro_Vendedor")));
				  
				  ClienteDAO c = new ClienteDAO();
				  v.setCliente(c.getCliente(rs.getInt("codigo_Cliente")));
				  
				  AutomovelDAO a = new AutomovelDAO();
				  v.setAutomovel(a.getAutomovel(rs.getInt("codigo_Automovel")));
				  
				  v.setDia(rs.getString("dia"));
				  v.setValor(rs.getInt("valor"));
				  
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
	
	public Venda getVenda(int numVenda) throws Exception {
		Venda v = null;
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					"SELECT numVenda, numRegistro_Vendedor, codigo_Cliente, codigo_Automovel, dia, valor FROM Venda where numVenda = ?");
			stmt.setInt(1, numVenda);
			  ResultSet rs = stmt.executeQuery();
			  while (rs.next()) {
				  v = new Venda();
				  v.setNumVenda(rs.getInt("numVenda"));
				  
				  VendedorDAO vd = new VendedorDAO();
				  v.setVendedor(vd.getVendedor(rs.getInt("numRegistro_Vendedor")));
				  
				  ClienteDAO c = new ClienteDAO();
				  v.setCliente(c.getCliente(rs.getInt("codigo_Cliente")));
				  
				  AutomovelDAO a = new AutomovelDAO();
				  v.setAutomovel(a.getAutomovel(rs.getInt("codigo_Automovel")));
				  
				  v.setDia(rs.getString("dia"));
				  v.setValor(rs.getInt("valor"));
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return v;
	}

	
	public Venda inserir(Venda venda) throws Exception {
		String sqlInsert =
			"INSERT INTO Venda (numVenda, numRegistro_Vendedor, codigo_Cliente, codigo_Automovel, dia, valor) VALUES ( ?, ?, ?, ?, ?, ? )";
		valida(venda, true);
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setInt(1, venda.getNumVenda());
			ps.setInt(2, venda.getVendedor().getNumRegistro());
			ps.setInt(3, venda.getCliente().getCodigo());
			ps.setInt(4, venda.getAutomovel().getCodigo());
			ps.setString(5, venda.getDia());
			ps.setInt(6, venda.getValor());
			
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi inserido por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return venda;
	}

	
	public Venda atualizar(Venda venda) throws Exception {
		String sqlInsert =
			"UPDATE Venda SET numRegistro_Vendedor, codigo_Cliente, codigo_Automovel, dia, valor where numVenda = ?";
		valida(venda, false);
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			
			
			ps.setInt(1, venda.getVendedor().getNumRegistro());
			ps.setInt(2, venda.getCliente().getCodigo());
			ps.setInt(3, venda.getAutomovel().getCodigo());
			ps.setString(4, venda.getDia());
			ps.setInt(5, venda.getValor());
			ps.setInt(6, venda.getNumVenda());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi alterado por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return venda;
	}
	
	
	public Venda excluir(Venda venda) throws Exception {
		String sqlExcluir =
			"DELETE FROM Venda where numVenda = ?";
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlExcluir);
			ps.setInt(1, venda.getNumVenda());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi excluído por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return venda;
	}

	private void valida(Venda venda, boolean inserir) throws Exception {

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
					.executeQuery("SELECT numVenda FROM Venda");
			while (rs.next()) {
				int id = rs.getInt("numVenda");
				/*
				String nomeBD = rs.getString("nome");
				String cpfBD = rs.getString("cpf");
				*/
				// Quando está atualizando, se o ID for igual, não precisa comparar
				if (!inserir) {
					if (id == venda.getNumVenda()) {
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
				venda.setNumVenda(maxId + 1);
			}
			rs.close();
			stmt.close();

		} catch (Exception ex) {
			System.err.println("Erro de validação: " + ex);
			throw ex;
		}
	}
}
