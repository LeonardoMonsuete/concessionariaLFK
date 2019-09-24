package conc.proj.pooII.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import conc.proj.pooII.entity.Novos;

public class NovosDAO {

	
	public List<Novos> listAll() throws Exception {
		ArrayList<Novos> list = new ArrayList<Novos>();

		try {
			Connection con = AppConnection.getConnection();
			Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(
			          "SELECT id, modelo, marca, cor, ano FROM Novos");
			  while (rs.next()) {
			      Novos auto = new Novos();
			      auto.setId(rs.getInt("id"));
				  auto.setModelo(rs.getString("modelo"));
				  auto.setMarca(rs.getString("marca"));
				  auto.setCor(rs.getString("cor"));
				  auto.setAno(rs.getString("ano"));
				  list.add(auto);
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return list;
	}
	
	public Novos getNovos(int id) throws Exception {
		Novos novo = null;
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					"SELECT id, modelo, marca, ano, cor FROM Novos where id = ?");
			stmt.setInt(1, id);
			  ResultSet rs = stmt.executeQuery();
			  while (rs.next()) {
			      novo = new Novos();
			      novo.setId(rs.getInt("id"));
				  novo.setModelo(rs.getString("modelo"));
				  novo.setMarca(rs.getString("marca"));
				  novo.setAno(rs.getString("ano"));
				  novo.setCor(rs.getString("cor"));
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return novo;
	}

	
	public Novos inserir(Novos novo) throws Exception {
		String sqlInsert =
			"INSERT INTO Novos (ID, MODELO, MARCA, ANO, COR) VALUES (?, ?, ?, ?, ? )";
		
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setInt(1, novo.getId());
			ps.setString(2, novo.getModelo());
			ps.setString(3, novo.getMarca());
			ps.setString(4, novo.getAno());
			ps.setString(5, novo.getCor());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi inserido por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return novo;
	}

	
	public Novos atualizar(Novos novo) throws Exception {
		String sqlInsert =
			"UPDATE Novos SET modelo = ?, marca = ?, ano = ?, cor = ? where id = ?";
		
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setString(1, novo.getModelo());
			ps.setString(2, novo.getMarca());
			ps.setString(3, novo.getAno());
			ps.setString(3, novo.getCor());
			ps.setInt(4, novo.getId());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi alterado por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return novo;
	}
	
	
	public Novos excluir(Novos novo) throws Exception {
		String sqlExcluir =
			"DELETE FROM Novos where id = ?";
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlExcluir);
			ps.setInt(1, novo.getId());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi excluído por erro de banco."); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return novo;
	}

	
	
	
}
