package br.com.exataoticas.tablens.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@SuppressWarnings("serial")
@Entity
public class ProdutoServico implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	private String especificacaoTecnica;
	
	private BigDecimal precoVenda;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "produtoServico")
	private Set<ProdutoSugestaoServico> sugestoesServico = new HashSet<>(0);
	
		
	public ProdutoServico() {
		
	}
	
	


	public ProdutoServico(Integer id, String descricao, String especificacaoTecnica, BigDecimal precoVenda,
			Set<ProdutoSugestaoServico> sugestoesServico) {
		this.id = id;
		this.descricao = descricao;
		this.especificacaoTecnica = especificacaoTecnica;
		this.precoVenda = precoVenda;
		this.sugestoesServico = sugestoesServico;
	}




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getEspecificacaoTecnica() {
		return especificacaoTecnica;
	}


	public void setEspecificacaoTecnica(String especificacaoTecnica) {
		this.especificacaoTecnica = especificacaoTecnica;
	}


	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}


	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}


	public Set<ProdutoSugestaoServico> getSugestoesServico() {
		return sugestoesServico;
	}


	public void setSugestoesServico(Set<ProdutoSugestaoServico> sugestoesServico) {
		this.sugestoesServico = sugestoesServico;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		ProdutoServico other = (ProdutoServico) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

	
	
	
}
