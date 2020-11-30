package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.VendasDaoWanderson;
import br.edu.faculdadedelta.modelo.VendasWanderson;

@ManagedBean
@SessionScoped

public class VendasControllerWanderson {
	
	private VendasWanderson vendas = new VendasWanderson();
	private VendasDaoWanderson dao = new VendasDaoWanderson();
	
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	  String data = "01/01/2021"; // NO EXERCICIO NAO FAZIA SENTIDO SER 01/01/2020, E MAIOR QUE A DATA ATUAL, POR ISSO USEI 2021

	  LocalDate ld = LocalDate.parse(data, formatter);
	  Date dataLimite = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
	  
	private static final String PAGINA_CADASTRO = "cadastroVendas.xhtml";
	private static final String RELACAO_VENDAS = "listaVendas.xhtml";
	
	public VendasWanderson getVendas() {
		return vendas;
	}
	public void setVendas(VendasWanderson vendas) {
		this.vendas = vendas;
	}
	public VendasDaoWanderson getDao() {
		return dao;
	}
	public void setDao(VendasDaoWanderson dao) {
		this.dao = dao;
	}
	
	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	
	public void limparCampos () {
		vendas = new VendasWanderson();
	}
	
	public String salvar() {
		
		try {
			if(vendas.getDataVenda().after(new Date()) && vendas.getDataVenda().before(dataLimite)) {
				if(vendas.getId()==null) {
					dao.incluir(vendas);
						exibirMensagem("Inclusão realizada com Sucesso!");
						limparCampos();
				}else {
					dao.alterar(vendas);
					exibirMensagem("Alteração realizada com Sucesso!");
				}
				
			}else {
				exibirMensagem("Data deve ser superior a data atual, menor que 01/01/2022 e menor que a data final");
			}
		}catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde." + e.getMessage());
			e.printStackTrace();
		}
		return PAGINA_CADASTRO;
	}
	
	public List<VendasWanderson> getLista() {
		List<VendasWanderson> listaRetorno = new ArrayList<VendasWanderson>();
		try {
			listaRetorno = VendasDaoWanderson.listar();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. "
					+ "Tente novamente mais tarde." + e.getMessage());
			e.printStackTrace();
		}
		return listaRetorno;
	}
	
	
	public String editar() {
		return PAGINA_CADASTRO;
	}
	
	public String excluir() {
		try {
			dao.excluir(vendas);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde. " + e.getMessage());
			e.printStackTrace();
		}
		return RELACAO_VENDAS;
	}
	

	
}
