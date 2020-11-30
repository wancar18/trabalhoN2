package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.EmpresaWanderson;
import br.edu.faculdadedelta.util.Conexao;

public class EmpresaDaoWanderson {

	public void incluir (EmpresaWanderson empresa) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "INSERT INTO empresas("
				+ "	nome_fantasia_empresa, cnpj_empresa, endereco_empresa, data_cadastro_empresa)"
				+ "	VALUES (?, ?, ?, ?)";
		
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, empresa.getNomeFantasia().trim());
				ps.setString(2, empresa.getCnpj().trim());
				ps.setString(3, empresa.getEndereco().trim());
				ps.setDate(4, new java.sql.Date(empresa.getDataCadastro().getTime()));
				
				ps.executeUpdate();
				Conexao.fecharConexao(ps, conn, null);
	}
	
	public void alterar (EmpresaWanderson empresa) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "UPDATE empresas"
				+ "	SET nome_fantasia_empresa=?, cnpj_empresa=?, "
				+ "endereco_empresa=?, data_cadastro_empresa=?"
				+ "	WHERE id_empresa";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, empresa.getNomeFantasia().trim());
				ps.setString(2, empresa.getCnpj().trim().trim());
				ps.setString(3, empresa.getEndereco().trim());
				ps.setDate(4, new java.sql.Date(empresa.getDataCadastro().getTime()));
				
				ps.executeUpdate();
				Conexao.fecharConexao(ps, conn, null);
	}
	
	public void excluir (EmpresaWanderson empresa) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql ="DELETE FROM empresas"
				+ "	WHERE id_empresa=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, empresa.getId());
		
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
	}
	
	public static List <EmpresaWanderson> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "SELECT id_empresa, nome_fantasia_empresa, cnpj_empresa, endereco_empresa, data_cadastro_empresa\n"
				+ "	FROM empresas;";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<EmpresaWanderson> listaRetorno = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			EmpresaWanderson empresa = new EmpresaWanderson();
			empresa.setId(rs.getLong("id_empresa"));
			empresa.setNomeFantasia(rs.getString("nome_fantasia_empresa").trim());
			empresa.setCnpj(rs.getString("cnpj_empresa").trim());
			empresa.setEndereco(rs.getString("endereco_empresa").trim());
			empresa.setDataCadastro(rs.getDate("data_cadastro_empresa"));

			listaRetorno.add(empresa);
			
		}
		Conexao.fecharConexao(ps, conn, rs);
		
		return listaRetorno;
	}
	
	
	
	
}
