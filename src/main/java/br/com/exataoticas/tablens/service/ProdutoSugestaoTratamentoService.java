package br.com.exataoticas.tablens.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exataoticas.tablens.domain.ProdutoSugestaoTratamento;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.repository.ProdutoSugestaoTratamentoRepository;

@SuppressWarnings("serial")
@Service
public class ProdutoSugestaoTratamentoService implements Serializable {
	
	@Autowired
	private ProdutoSugestaoTratamentoRepository produtoSugestaoTratamentoRepository;
	
	@Transactional
	public void salvarProdutoSugestaoTratamento(ProdutoSugestaoTratamento ProdutoSugestaoTratamento) throws ValidateException{
		
		produtoSugestaoTratamentoRepository.save(ProdutoSugestaoTratamento);

	}

	
	public Optional<ProdutoSugestaoTratamento> buscarProdutoSugestaoTratamento(Integer idProdutoSugestaoTratamento) {

		Optional<ProdutoSugestaoTratamento> ProdutoSugestaoTratamento = produtoSugestaoTratamentoRepository.findById(idProdutoSugestaoTratamento);

		return ProdutoSugestaoTratamento;
	}

	public List<ProdutoSugestaoTratamento> listarProdutosSugestaoTratamento(Integer idProduto) {
		List<ProdutoSugestaoTratamento> produtosSugestao = produtoSugestaoTratamentoRepository.findByProdutoId(idProduto);
		return produtosSugestao;
	}

	public void deletarProdutoSugestaoTratamento(Integer idSugestao) throws ErrorException {

		try {
			ProdutoSugestaoTratamento ProdutoSugestaoTratamento = buscarProdutoSugestaoTratamento(idSugestao).get();
			produtoSugestaoTratamentoRepository.delete(ProdutoSugestaoTratamento);
		} catch (Exception e) {
			throw new ErrorException("Erro ao deletar a sugestão de tratamento, verifique as dependências!");

		}

	}

	
}
