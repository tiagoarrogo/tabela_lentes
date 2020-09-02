package br.com.exataoticas.tablens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.exataoticas.tablens.domain.ProdutoSugestaoServico;

@Repository
public interface ProdutoSugestaoServicoRepository extends JpaRepository<ProdutoSugestaoServico, Integer> {
	
	public List<ProdutoSugestaoServico>  findByProdutoId(Integer id);
	

}
