package br.com.exataoticas.tablens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.exataoticas.tablens.domain.ProdutoServico;

@Repository
public interface ProdutoServicoRepository extends JpaRepository<ProdutoServico, Integer> {
	
	public ProdutoServico findByDescricao(String descricao);
	
}
