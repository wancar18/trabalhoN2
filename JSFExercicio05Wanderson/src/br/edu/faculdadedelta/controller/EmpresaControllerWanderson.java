package br.edu.faculdadedelta.controller;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.EmpresaDaoWanderson;
import br.edu.faculdadedelta.modelo.EmpresaWanderson;

@ManagedBean
@SessionScoped

public class EmpresaControllerWanderson {
	
	private EmpresaWanderson empresa = new EmpresaWanderson();
	private EmpresaDaoWanderson dao = new EmpresaDaoWanderson();

	private static final String PAGINA_CADASTRO = "cadastroEmpresa.xhtml";
	private static final String RELACAO_VENDAS = "listaEmpresa.xhtml";

	public EmpresaWanderson getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaWanderson carro) {
		this.empresa = carro;
	}

	public EmpresaDaoWanderson getDao() {
		return dao;
	}

	public void setDao(EmpresaDaoWanderson dao) {
		this.dao = dao;
	}

	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void limparCampos() {
		empresa = new EmpresaWanderson();
	}

	public String salvar() {
		try {
			if(empresa.getId()==null) {
				
					dao.incluir(empresa);
						exibirMensagem("Inclusão realizada com Sucesso!");
						limparCampos();
				}else {
					dao.alterar(empresa);
					exibirMensagem("Alteração realizada com Sucesso!");
				}
			

		}catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde." + e.getMessage());
			e.printStackTrace();
		}
		return PAGINA_CADASTRO;
	}
	
	public List<EmpresaWanderson> getLista() {
		List<EmpresaWanderson> listaRetorno = new ArrayList<EmpresaWanderson>();
		try {
			listaRetorno = EmpresaDaoWanderson.listar();
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
			dao.excluir(empresa);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde. " + e.getMessage());
			e.printStackTrace();
		}
		return RELACAO_VENDAS;
	}

}
