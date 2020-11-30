package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.CarroDaoWanderson;
import br.edu.faculdadedelta.modelo.CarroWanderson;

@ManagedBean
@SessionScoped

public class CarroControllerWanderson {

	private CarroWanderson carro = new CarroWanderson();
	private CarroDaoWanderson dao = new CarroDaoWanderson();

	private static final String PAGINA_CADASTRO = "cadastroCarro.xhtml";
	private static final String RELACAO_VENDAS = "listaCarro.xhtml";

	public CarroWanderson getCarro() {
		return carro;
	}

	public void setCarro(CarroWanderson carro) {
		this.carro = carro;
	}

	public CarroDaoWanderson getDao() {
		return dao;
	}

	public void setDao(CarroDaoWanderson dao) {
		this.dao = dao;
	}

	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void limparCampos() {
		carro = new CarroWanderson();
	}

	public String salvar() {
		try {
			if(carro.getDataCadastro().after(new Date())) {
			if(carro.getId()==null) {
					dao.incluir(carro);
						exibirMensagem("Inclusão realizada com Sucesso!");
						limparCampos();
				}else {
					dao.alterar(carro);
					exibirMensagem("Alteração realizada com Sucesso!");
				}
			
		
			}else {
				exibirMensagem("Data deve ser maior que a data Atual!");
			}
		}catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde." + e.getMessage());
			e.printStackTrace();
		}
		return PAGINA_CADASTRO;
	}
	public List<CarroWanderson> getLista() {
		List<CarroWanderson> listaRetorno = new ArrayList<CarroWanderson>();
		try {
			listaRetorno = CarroDaoWanderson.listar();
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
			dao.excluir(carro);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde. " + e.getMessage());
			e.printStackTrace();
		}
		return RELACAO_VENDAS;
	}

}

