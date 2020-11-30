package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.AlunoWanderson;
import br.edu.faculdadedelta.util.Conexao;

public class AlunoDaoWanderson {


	public void incluir (AlunoWanderson aluno) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "INSERT INTO alunos("
				+ "	nome_aluno, idade_aluno, grau_instrucao_aluno, data_cadastro_aluno)"
				+ "	VALUES (?, ?, ?, ?);";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, aluno.getNomeAluno().trim());
				ps.setInt(2, aluno.getIdade());
				ps.setString(3, aluno.getGrauInstrucao().trim());
				ps.setDate(4, new java.sql.Date(aluno.getDataCadastro().getTime()));
				
				ps.executeUpdate();
				Conexao.fecharConexao(ps, conn, null);
	}
	
	public void alterar (AlunoWanderson aluno) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "UPDATE alunos"
				+ "	SET nome_aluno=?, idade_aluno=?, "
				+ "grau_instrucao_aluno=?, data_cadastro_aluno=?"
				+ "	WHERE id_aluno=?;";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, aluno.getNomeAluno().trim());
				ps.setInt(2, aluno.getIdade());
				ps.setString(3, aluno.getGrauInstrucao().trim());
				ps.setDate(4, new java.sql.Date(aluno.getDataCadastro().getTime()));
				
				ps.executeUpdate();
				Conexao.fecharConexao(ps, conn, null);
	}
	
	public void excluir (AlunoWanderson aluno) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql ="DELETE FROM alunos"
				+ "	WHERE id_aluno=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, aluno.getId());
		
		ps.executeUpdate();
		
		Conexao.fecharConexao(ps, conn, null);
	}
	
	public static List <AlunoWanderson> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBanco();
		String sql = "SELECT id_aluno, nome_aluno, idade_aluno, grau_instrucao_aluno, data_cadastro_aluno\n"
				+ "	FROM alunos;";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<AlunoWanderson> listaRetorno = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			AlunoWanderson aluno = new AlunoWanderson();
			aluno.setId(rs.getLong("id_aluno"));
			aluno.setNomeAluno(rs.getString("nome_aluno").trim());
			aluno.setIdade(rs.getInt("idade_aluno"));
			aluno.setGrauInstrucao(rs.getString("grau_instrucao_aluno").trim());
			aluno.setDataCadastro(rs.getDate("data_cadastro_aluno"));

			listaRetorno.add(aluno);
			
		}
		Conexao.fecharConexao(ps, conn, rs);
		
		return listaRetorno;
	}
	
	
}
