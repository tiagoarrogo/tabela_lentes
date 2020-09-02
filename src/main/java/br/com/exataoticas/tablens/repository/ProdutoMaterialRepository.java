package br.com.exataoticas.tablens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.exataoticas.tablens.domain.ProdutoMaterial;

@Repository
public interface ProdutoMaterialRepository extends JpaRepository<ProdutoMaterial, Integer> {
	
	public ProdutoMaterial findByDescricao(String descricao);

}
