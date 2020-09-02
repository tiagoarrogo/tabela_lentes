package br.com.exataoticas.tablens.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class ProdutoSugestaoTratamento implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "produtoTratamento_id")
	private ProdutoTratamento produtoTratamento;
	
	public ProdutoSugestaoTratamento(Integer id, Produto produto, ProdutoTratamento produtoTratamento) {
		super();
		this.id = id;
		this.produto = produto;
		this.produtoTratamento = produtoTratamento;
	}

	public ProdutoSugestaoTratamento() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoTratamento getProdutoTratamento() {
		return produtoTratamento;
	}

	public void setProdutoTratamento(ProdutoTratamento produtoTratamento) {
		this.produtoTratamento = produtoTratamento;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((produtoTratamento == null) ? 0 : produtoTratamento.hashCode());
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
		ProdutoSugestaoTratamento other = (ProdutoSugestaoTratamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (produtoTratamento == null) {
			if (other.produtoTratamento != null)
				return false;
		} else if (!produtoTratamento.equals(other.produtoTratamento))
			return false;
		return true;
	}
	

	

}
