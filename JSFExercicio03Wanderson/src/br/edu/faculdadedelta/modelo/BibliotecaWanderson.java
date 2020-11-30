package br.edu.faculdadedelta.modelo;

import java.util.Date;

public class BibliotecaWanderson {

	private Long id;
	private String nomeLivro;
	private String editora;
	private double valorLivro;
	private Date dataCadastro;
	
	public BibliotecaWanderson() {
	}

	public BibliotecaWanderson(Long id, String nomeLivro, String editora, double valorLivro, Date dataCadastro) {
		this.id = id;
		this.nomeLivro = nomeLivro;
		this.editora = editora;
		this.valorLivro = valorLivro;
		this.dataCadastro = dataCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public double getValorLivro() {
		return valorLivro;
	}

	public void setValorLivro(double valorLivro) {
		this.valorLivro = valorLivro;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BibliotecaWanderson other = (BibliotecaWanderson) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
