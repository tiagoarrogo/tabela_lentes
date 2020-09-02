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
public class ProdutoTratamento implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	private String especificacaoTecnica;
	
	private BigDecimal precoVenda;
		
	@JsonIgnore
	@OneToMany(mappedBy = "produtoTratamento")
	private Set<ProdutoSugestaoTratamento> sugestoesTratamento = new HashSet<>(0);
	
	
	public ProdutoTratamento() {
		
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

	
	public Set<ProdutoSugestaoTratamento> getSugestoesTratamento() {
		return sugestoesTratamento;
	}

	public void setSugestoesTratamento(Set<ProdutoSugestaoTratamento> sugestoesTratamento) {
		this.sugestoesTratamento = sugestoesTratamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((especificacaoTecnica == null) ? 0 : especificacaoTecnica.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((precoVenda == null) ? 0 : precoVenda.hashCode());
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
		ProdutoTratamento other = (ProdutoTratamento) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (especificacaoTecnica == null) {
			if (other.especificacaoTecnica != null)
				return false;
		} else if (!especificacaoTecnica.equals(other.especificacaoTecnica))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (precoVenda == null) {
			if (other.precoVenda != null)
				return false;
		} else if (!precoVenda.equals(other.precoVenda))
			return false;
		return true;
	}	

	
	
}
