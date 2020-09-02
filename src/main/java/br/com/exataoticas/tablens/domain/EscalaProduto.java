package br.com.exataoticas.tablens.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@SuppressWarnings("serial")
@Entity
public class EscalaProduto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	private BigDecimal esfericoInicial;
	
	private BigDecimal esfericoFinal;
	
	private BigDecimal cilindricoFinal;	
	
	private BigDecimal adicaoInicial;
	
	private BigDecimal adicaoFinal;
	
	private BigDecimal diametro;
	
	private BigDecimal indice;	
	
	private BigDecimal alturaMinima;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getEsfericoInicial() {
		return esfericoInicial;
	}

	public void setEsfericoInicial(BigDecimal esfericoInicial) {
		this.esfericoInicial = esfericoInicial;
	}

	public BigDecimal getEsfericoFinal() {
		return esfericoFinal;
	}

	public void setEsfericoFinal(BigDecimal esfericoFinal) {
		this.esfericoFinal = esfericoFinal;
	}

	public BigDecimal getCilindricoFinal() {
		return cilindricoFinal;
	}

	public void setCilindricoFinal(BigDecimal cilindricoFinal) {
		this.cilindricoFinal = cilindricoFinal;
	}

	public BigDecimal getAdicaoInicial() {
		return adicaoInicial;
	}

	public void setAdicaoInicial(BigDecimal adicaoInicial) {
		this.adicaoInicial = adicaoInicial;
	}

	public BigDecimal getAdicaoFinal() {
		return adicaoFinal;
	}

	public void setAdicaoFinal(BigDecimal adicaoFinal) {
		this.adicaoFinal = adicaoFinal;
	}

	public BigDecimal getDiametro() {
		return diametro;
	}

	public void setDiametro(BigDecimal diametro) {
		this.diametro = diametro;
	}

	public BigDecimal getIndice() {
		return indice;
	}

	public void setIndice(BigDecimal indice) {
		this.indice = indice;
	}

	public BigDecimal getAlturaMinima() {
		return alturaMinima;
	}

	public void setAlturaMinima(BigDecimal alturaMinima) {
		this.alturaMinima = alturaMinima;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adicaoFinal == null) ? 0 : adicaoFinal.hashCode());
		result = prime * result + ((adicaoInicial == null) ? 0 : adicaoInicial.hashCode());
		result = prime * result + ((alturaMinima == null) ? 0 : alturaMinima.hashCode());
		result = prime * result + ((cilindricoFinal == null) ? 0 : cilindricoFinal.hashCode());
		result = prime * result + ((diametro == null) ? 0 : diametro.hashCode());
		result = prime * result + ((esfericoFinal == null) ? 0 : esfericoFinal.hashCode());
		result = prime * result + ((esfericoInicial == null) ? 0 : esfericoInicial.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((indice == null) ? 0 : indice.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		EscalaProduto other = (EscalaProduto) obj;
		if (adicaoFinal == null) {
			if (other.adicaoFinal != null)
				return false;
		} else if (!adicaoFinal.equals(other.adicaoFinal))
			return false;
		if (adicaoInicial == null) {
			if (other.adicaoInicial != null)
				return false;
		} else if (!adicaoInicial.equals(other.adicaoInicial))
			return false;
		if (alturaMinima == null) {
			if (other.alturaMinima != null)
				return false;
		} else if (!alturaMinima.equals(other.alturaMinima))
			return false;
		if (cilindricoFinal == null) {
			if (other.cilindricoFinal != null)
				return false;
		} else if (!cilindricoFinal.equals(other.cilindricoFinal))
			return false;
		if (diametro == null) {
			if (other.diametro != null)
				return false;
		} else if (!diametro.equals(other.diametro))
			return false;
		if (esfericoFinal == null) {
			if (other.esfericoFinal != null)
				return false;
		} else if (!esfericoFinal.equals(other.esfericoFinal))
			return false;
		if (esfericoInicial == null) {
			if (other.esfericoInicial != null)
				return false;
		} else if (!esfericoInicial.equals(other.esfericoInicial))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (indice == null) {
			if (other.indice != null)
				return false;
		} else if (!indice.equals(other.indice))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
	

	
	
		
}
