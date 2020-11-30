package br.edu.faculdadedelta.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection conectarNoBanco() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		
		Connection conn = null;
		
		String url = "jdbc:postgresql://localhost/Delta";
		String usuario = "postgres";
		String senha = "123";
		
		conn = DriverManager.getConnection(url, usuario, senha);
		
		return conn;
	}
	
	public static void fecharConexao(PreparedStatement ps, Connection conn, ResultSet rs) throws SQLException {
		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}
		if (rs != null) {
			rs.close();
		}
	}
	
	public static void main(String[] args) {
		try {
			Conexao.conectarNoBanco();
			System.out.println("Conectou no banco de dados!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}