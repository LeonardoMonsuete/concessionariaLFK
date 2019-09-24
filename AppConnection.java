package conc.proj.pooII.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class AppConnection {

	private static Connection con;
	
	
	/**
	 * Recupera a conexão.  Se não estiver aberta, tenta abrir.
	 * @return conexão.
	 */
	public static Connection getConnection() throws Exception {
		if (con == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				  con = DriverManager.getConnection
				    ("jdbc:mysql://localhost:3306/concessionaria", "lkproj", "lkproj");
			} catch (Exception ex) {
				System.err.print("Erro na conexão:" + ex.getLocalizedMessage());
				throw ex;
			}
		}
		return con;
	}
	
	public static void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (Exception ex) {
				System.err.print("Erro ao fechar a conexão:" + ex.getLocalizedMessage());
			}
			con = null;
		}
	}
}
