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
public class ProdutoSugestaoServico implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "produtoServico_id")
	private ProdutoServico produtoServico;
	
	public ProdutoSugestaoServico(Integer id, Produto produto, ProdutoServico produtoServico) {
		super();
		this.id = id;
		this.produto = produto;
		this.produtoServico = produtoServico;
	}

	public ProdutoSugestaoServico() {

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

	public ProdutoServico getProdutoServico() {
		return produtoServico;
	}

	public void setProdutoTratamento(ProdutoServico produtoServico) {
		this.produtoServico = produtoServico;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((produtoServico == null) ? 0 : produtoServico.hashCode());
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
		ProdutoSugestaoServico other = (ProdutoSugestaoServico) obj;
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
		if (produtoServico == null) {
			if (other.produtoServico != null)
				return false;
		} else if (!produtoServico.equals(other.produtoServico))
			return false;
		return true;
	}
	

	

}
