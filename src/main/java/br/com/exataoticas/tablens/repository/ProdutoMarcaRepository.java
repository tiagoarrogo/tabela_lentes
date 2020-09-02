package br.com.exataoticas.tablens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.exataoticas.tablens.domain.ProdutoMarca;

@Repository
public interface ProdutoMarcaRepository extends JpaRepository<ProdutoMarca, Integer> {

	public ProdutoMarca findByDescricao(String descricao);
	
}
