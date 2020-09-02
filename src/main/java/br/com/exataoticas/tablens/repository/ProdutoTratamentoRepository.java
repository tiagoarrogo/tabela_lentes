package br.com.exataoticas.tablens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.exataoticas.tablens.domain.ProdutoTratamento;

@Repository
public interface ProdutoTratamentoRepository extends JpaRepository<ProdutoTratamento, Integer> {
	
	public ProdutoTratamento findByDescricao(String descricao);

}
