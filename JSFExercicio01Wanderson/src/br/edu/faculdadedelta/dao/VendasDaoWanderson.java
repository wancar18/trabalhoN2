package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.VendasWanderson;
import br.edu.faculdadedelta.util.Conexao;

public class VendasDaoWanderson {

	public void incluir (VendasWanderson vendas) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "INSERT INTO vendas("
				+ "desc_cliente, desc_produto, valor_produto, data_venda)"
				+ "VALUES (?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, vendas.getCliente().trim());
				ps.setString(2, vendas.getProduto().trim());
				ps.setDouble(3, vendas.getValorVenda());
				ps.setDate(4, new java.sql.Date(vendas.getDataVenda().getTime()));
				
				ps.executeUpdate();
				Conexao.fecharConexao(ps, conn, null);
	}
	
	public void alterar (VendasWanderson vendas) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "UPDATE vendas "
				+ "SET desc_cliente=?, desc_produto=?, valor_produto=?, data_venda=?"
				+ "WHERE id_venda=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, vendas.getCliente().trim());
				ps.setString(2, vendas.getProduto().trim());
				ps.setDouble(3, vendas.getValorVenda());
				ps.setDate(4, new java.sql.Date(vendas.getDataVenda().getTime()));
				ps.setLong(5, vendas.getId());
				
				ps.executeUpdate();
				Conexao.fecharConexao(ps, conn, null);
	}
	
	public void excluir (VendasWanderson vendas) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql ="DELETE FROM vendas"
				+ "	WHERE id_venda=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, vendas.getId());
		
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
	}
	
	public static List <VendasWanderson> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "SELECT id_venda, desc_cliente, desc_produto, valor_produto, data_venda"
				+ "	FROM vendas";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<VendasWanderson> listaRetorno = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			VendasWanderson vendas = new VendasWanderson();
			vendas.setId(rs.getLong("id_venda"));
			vendas.setCliente(rs.getString("desc_cliente").trim());
			vendas.setProduto(rs.getString("desc_produto").trim());
			vendas.setValorVenda(rs.getDouble("valor_produto"));
			vendas.setDataVenda(rs.getDate("data_venda"));

			listaRetorno.add(vendas);
			
		}
		Conexao.fecharConexao(ps, conn, rs);
		
		return listaRetorno;
	}
	
	
}
