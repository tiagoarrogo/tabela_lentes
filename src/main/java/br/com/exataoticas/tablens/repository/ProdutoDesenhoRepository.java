package br.com.exataoticas.tablens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.exataoticas.tablens.domain.ProdutoDesenho;

@Repository
public interface ProdutoDesenhoRepository extends JpaRepository<ProdutoDesenho, Integer> {

}
