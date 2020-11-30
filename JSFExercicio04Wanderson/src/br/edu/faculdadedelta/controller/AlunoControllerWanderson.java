package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.AlunoDaoWanderson;
import br.edu.faculdadedelta.modelo.AlunoWanderson;

@ManagedBean
@SessionScoped

public class AlunoControllerWanderson {

	private AlunoWanderson aluno = new AlunoWanderson();
	private AlunoDaoWanderson dao = new AlunoDaoWanderson();

	private static final String PAGINA_CADASTRO = "cadastroAluno.xhtml";
	private static final String RELACAO_VENDAS = "listaAluno.xhtml";



	public AlunoWanderson getAluno() {
		return aluno;
	}

	public void setAluno(AlunoWanderson aluno) {
		this.aluno = aluno;
	}

	public AlunoDaoWanderson getDao() {
		return dao;
	}

	public void setDao(AlunoDaoWanderson dao) {
		this.dao = dao;
	}

	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void limparCampos() {
		aluno = new AlunoWanderson();
	}

	public String salvar() {
		try {
			if(aluno.getId()==null) {
					dao.incluir(aluno);
						exibirMensagem("Inclusão realizada com Sucesso!");
						limparCampos();
				}else {
					dao.alterar(aluno);
					exibirMensagem("Alteração realizada com Sucesso!");
				}
			
		
		
		}catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde." + e.getMessage());
			e.printStackTrace();
		}
		return PAGINA_CADASTRO;
	}
	public List<AlunoWanderson> getLista() {
		List<AlunoWanderson> listaRetorno = new ArrayList<AlunoWanderson>();
		try {
			listaRetorno = AlunoDaoWanderson.listar();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. " + "Tente novamente mais tarde." + e.getMessage());
			e.printStackTrace();
		}
		return listaRetorno;
	}

	public String editar() {
		return PAGINA_CADASTRO;
	}

	public String excluir() {
		try {
			dao.excluir(aluno);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde. " + e.getMessage());
			e.printStackTrace();
		}
		return RELACAO_VENDAS;
	}

	

}
