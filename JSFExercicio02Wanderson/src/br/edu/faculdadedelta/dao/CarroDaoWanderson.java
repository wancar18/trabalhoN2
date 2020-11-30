package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.CarroWanderson;
import br.edu.faculdadedelta.util.Conexao;

public class CarroDaoWanderson {

	public void incluir (CarroWanderson carro) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "INSERT INTO carros("
				+ "	desc_nome_carro, desc_marca_carro, ano_fabricacao_carro, desc_placa_carro, data_cadastro_carro)"
				+ "	VALUES (?, ?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, carro.getNome().trim());
				ps.setString(2, carro.getMarca().trim());
				ps.setInt(3, carro.getAnoFab());
				ps.setString(4, carro.getPlaca().trim());
				ps.setDate(5, new java.sql.Date(carro.getDataCadastro().getTime()));
				
				ps.executeUpdate();
				Conexao.fecharConexao(ps, conn, null);
	}
	
	public void alterar (CarroWanderson carro) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "UPDATE carros"
				+ "	desc_nome_carro=?, desc_marca_carro=?, ano_fabricacao_carro=?, "
				+ "desc_placa_carro=?, data_cadastro_carro=?"
				+ "	WHERE id_carro=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, carro.getNome().trim());
				ps.setString(2, carro.getMarca().trim());
				ps.setInt(3, carro.getAnoFab());
				ps.setString(4, carro.getPlaca().trim());
				ps.setDate(5, new java.sql.Date(carro.getDataCadastro().getTime()));
				
				ps.executeUpdate();
				Conexao.fecharConexao(ps, conn, null);
	}
	
	public void excluir (CarroWanderson carro) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql ="DELETE FROM carros"
				+ "	WHERE id_carro=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, carro.getId());
		
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
	}
	
	public static List <CarroWanderson> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "SELECT id_carro, desc_nome_carro, desc_marca_carro, ano_fabricacao_carro, "
				+ "desc_placa_carro, data_cadastro_carro"
				+ "	FROM carros";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<CarroWanderson> listaRetorno = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			CarroWanderson carro = new CarroWanderson();
			carro.setId(rs.getLong("id_carro"));
			carro.setNome(rs.getString("desc_nome_carro").trim());
			carro.setMarca(rs.getString("desc_marca_carro").trim());
			carro.setAnoFab(rs.getInt("ano_fabricacao_carro"));
			carro.setPlaca(rs.getString("desc_placa_carro").trim());
			carro.setDataCadastro(rs.getDate("data_cadastro_carro"));

			listaRetorno.add(carro);
			
		}
		Conexao.fecharConexao(ps, conn, rs);
		
		return listaRetorno;
	}
	
	
}
