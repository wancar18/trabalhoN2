package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.BibliotecaWanderson;
import br.edu.faculdadedelta.util.Conexao;

public class BibliotecaDaoWanderson {

	public void incluir (BibliotecaWanderson bibl) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "INSERT INTO livros("
				+ "	nome_livro, desc_editora, valor_livro, data_cadastro_livro)"
				+ "	VALUES (?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, bibl.getNomeLivro().trim());
				ps.setString(2, bibl.getEditora().trim());
				ps.setDouble(3, bibl.getValorLivro());
				ps.setDate(4, new java.sql.Date(bibl.getDataCadastro().getTime()));
				
				ps.executeUpdate();
				Conexao.fecharConexao(ps, conn, null);
	}
	
	public void alterar (BibliotecaWanderson bibl) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "UPDATE livros"
				+ "	nome_livro=?, desc_editora=?, valor_livro=?, data_cadastro_livro=?"
				+ "	WHERE id_livro=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, bibl.getNomeLivro().trim());
				ps.setString(2, bibl.getEditora().trim());
				ps.setDouble(3, bibl.getValorLivro());
				ps.setDate(4, new java.sql.Date(bibl.getDataCadastro().getTime()));
				
				ps.executeUpdate();
				Conexao.fecharConexao(ps, conn, null);
	}
	
	public void excluir (BibliotecaWanderson bibl) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql ="DELETE FROM livros"
				+ "	WHERE id_livro=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, bibl.getId());
		
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
	}
	
	public static List <BibliotecaWanderson> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "SELECT id_livro, nome_livro, desc_editora, valor_livro, data_cadastro_livro"
				+ "	FROM livros";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<BibliotecaWanderson> listaRetorno = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			BibliotecaWanderson bibl = new BibliotecaWanderson();
			bibl.setId(rs.getLong("id_livro"));
			bibl.setNomeLivro(rs.getString("nome_livro").trim());
			bibl.setEditora(rs.getString("desc_editora").trim());
			bibl.setValorLivro(rs.getDouble("valor_livro"));
			bibl.setDataCadastro(rs.getDate("data_cadastro_livro"));
			listaRetorno.add(bibl);
			
		}
		Conexao.fecharConexao(ps, conn, rs);
		
		return listaRetorno;
	}
	
}
