package br.com.exataoticas.tablens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.exataoticas.tablens.domain.ProdutoTipo;

@Repository
public interface ProdutoTipoRepository extends JpaRepository<ProdutoTipo, Integer> {
	
	public ProdutoTipo findByDescricao(String descricao);

}
