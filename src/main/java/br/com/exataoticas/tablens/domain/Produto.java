package br.com.exataoticas.tablens.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
public class Produto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(max = 30)
	private String codigoSistemaErp;

	@NotBlank(message = " A descrição do produto não pode ser nula!")
	@Size(max = 90)
	private String descricao;

	@NotBlank(message = " A unidade do produto não pode ser nula!")	
	private String un;

	@NotNull	
	@ManyToOne
	@JoinColumn(name = "produtoTipo_id")
	private ProdutoTipo produtoTipo;

	@NotNull
	
	@ManyToOne
	@JoinColumn(name = "produtoMarca_id")
	private ProdutoMarca produtoMarca;

	@NotNull
	
	@ManyToOne
	@JoinColumn(name = "produtoMaterial_id")
	private ProdutoMaterial produtoMaterial;

	
	private BigDecimal precoCusto;
	
	private BigDecimal precoVenda;

	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;
	
	@OneToMany(mappedBy = "produto")	
	private List<ProdutoSugestaoTratamento> sugestoesTratamento = new ArrayList<>();
	
	@OneToMany(mappedBy = "produto")	
	private List<ProdutoSugestaoServico> sugestoesServico = new ArrayList<>();
	

	public Produto() {

	}

	public Produto(Integer id, String codigoSistemaErp, String descricao, String un, ProdutoTipo produtoTipo,
			ProdutoMarca produtoMarca, ProdutoMaterial produtoMaterial, BigDecimal precoCusto, BigDecimal precoVenda, Fornecedor fornecedor) {
		this.id = id;
		this.codigoSistemaErp = codigoSistemaErp;
		this.descricao = descricao;
		this.un = un;
		this.produtoTipo = produtoTipo;
		this.produtoMarca = produtoMarca;
		this.produtoMaterial = produtoMaterial;		
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.fornecedor = fornecedor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoSistemaErp() {
		return codigoSistemaErp;
	}

	public void setCodigoSistemaErp(String codigoSistemaErp) {
		this.codigoSistemaErp = codigoSistemaErp;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUn() {
		return un;
	}

	public void setUn(String un) {
		this.un = un;
	}

	public ProdutoTipo getProdutoTipo() {
		return produtoTipo;
	}

	public void setProdutoTipo(ProdutoTipo produtoTipo) {
		this.produtoTipo = produtoTipo;
	}

	public ProdutoMarca getProdutoMarca() {
		return produtoMarca;
	}

	public void setProdutoMarca(ProdutoMarca produtoMarca) {
		this.produtoMarca = produtoMarca;
	}
	

	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public ProdutoMaterial getProdutoMaterial() {
		return produtoMaterial;
	}

	public void setProdutoMaterial(ProdutoMaterial produtoMaterial) {
		this.produtoMaterial = produtoMaterial;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
