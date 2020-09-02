package br.com.exataoticas.tablens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.exataoticas.tablens.domain.ProdutoSugestaoTratamento;

@Repository
public interface ProdutoSugestaoTratamentoRepository extends JpaRepository<ProdutoSugestaoTratamento, Integer> {
	
	public List<ProdutoSugestaoTratamento>  findByProdutoId(Integer id);
	

}
