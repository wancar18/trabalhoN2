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

import br.edu.faculdadedelta.dao.BibliotecaDaoWanderson;
import br.edu.faculdadedelta.modelo.BibliotecaWanderson;


@ManagedBean
@SessionScoped

public class BibliotecaControllerWanderson {
	
	private BibliotecaWanderson bibl = new BibliotecaWanderson();
	private BibliotecaDaoWanderson dao = new BibliotecaDaoWanderson();
	
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	  String data = "01/01/2022";

	  LocalDate ld = LocalDate.parse(data, formatter);
	  Date dataLimite = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());

	private static final String PAGINA_CADASTRO = "cadastroBiblioteca.xhtml";
	private static final String RELACAO_VENDAS = "listaBiblioteca.xhtml";

	

	public BibliotecaWanderson getBibl() {
		return bibl;
	}

	public void setBibl(BibliotecaWanderson bibl) {
		this.bibl = bibl;
	}

	public BibliotecaDaoWanderson getDao() {
		return dao;
	}

	public void setDao(BibliotecaDaoWanderson dao) {
		this.dao = dao;
	}

	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void limparCampos() {
		bibl = new BibliotecaWanderson();
	}

	public String salvar() {
		try {
			if(bibl.getDataCadastro().after(new Date()) && bibl.getDataCadastro().before(dataLimite)) {
			if(bibl.getId()==null) {
					dao.incluir(bibl);;
						exibirMensagem("Inclusão realizada com Sucesso!");
						limparCampos();
				}else {
					dao.alterar(bibl);
					exibirMensagem("Alteração realizada com Sucesso!");
				}
			
			
			}else {
				exibirMensagem("Data deve ser maior que a data Atual e Menor que 01/01/2022!");
			}
		}catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde." + e.getMessage());
			e.printStackTrace();
		}
		return PAGINA_CADASTRO;
	}
	public List<BibliotecaWanderson> getLista() {
		List<BibliotecaWanderson> listaRetorno = new ArrayList<BibliotecaWanderson>();
		try {
			listaRetorno = BibliotecaDaoWanderson.listar();
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
			dao.excluir(bibl);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde. " + e.getMessage());
			e.printStackTrace();
		}
		return RELACAO_VENDAS;
	}

}
