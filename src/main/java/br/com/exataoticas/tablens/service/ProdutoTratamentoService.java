package br.com.exataoticas.tablens.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exataoticas.tablens.domain.ProdutoTratamento;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.repository.ProdutoTratamentoRepository;

@SuppressWarnings("serial")
@Service
public class ProdutoTratamentoService implements Serializable {
	
	@Autowired
	private ProdutoTratamentoRepository produtoTratamentoRepository;
	
	@Transactional
	public void salvarProdutoTratamento(ProdutoTratamento ProdutoTratamento) throws ValidateException{
		
		produtoTratamentoRepository.save(ProdutoTratamento);

	}

	
	public Optional<ProdutoTratamento> buscarProdutoTratamento(Integer idProdutoTratamento) {

		Optional<ProdutoTratamento> ProdutoTratamento = produtoTratamentoRepository.findById(idProdutoTratamento);

		return ProdutoTratamento;
	}

	public List<ProdutoTratamento> listarProdutosTratamento() {
		List<ProdutoTratamento> produtosTipo = produtoTratamentoRepository.findAll();
		return produtosTipo;
	}

	public void deletarProdutoTratamento(Integer idProdutoTratamento) throws ErrorException {

		try {
			ProdutoTratamento ProdutoTratamento = buscarProdutoTratamento(idProdutoTratamento).get();
			produtoTratamentoRepository.delete(ProdutoTratamento);
		} catch (Exception e) {
			throw new ErrorException("Erro ao deletar o tratamento, verifique as dependências!");

		}

	}

	
}
