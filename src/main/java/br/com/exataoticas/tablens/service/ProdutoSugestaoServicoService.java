package br.com.exataoticas.tablens.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exataoticas.tablens.domain.ProdutoSugestaoServico;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.repository.ProdutoSugestaoServicoRepository;

@SuppressWarnings("serial")
@Service
public class ProdutoSugestaoServicoService implements Serializable {
	
	@Autowired
	private ProdutoSugestaoServicoRepository produtoSugestaoServicoRepository;
	
	@Transactional
	public void salvarProdutoSugestaoServico(ProdutoSugestaoServico ProdutoSugestaoServico) throws ValidateException{
		
		produtoSugestaoServicoRepository.save(ProdutoSugestaoServico);

	}

	
	public Optional<ProdutoSugestaoServico> buscarProdutoSugestaoServico(Integer idProdutoSugestaoServico) {

		Optional<ProdutoSugestaoServico> ProdutoSugestaoServico = produtoSugestaoServicoRepository.findById(idProdutoSugestaoServico);

		return ProdutoSugestaoServico;
	}

	public List<ProdutoSugestaoServico> listarProdutosSugestaoServico(Integer idProduto) {
		List<ProdutoSugestaoServico> produtosSugestao = produtoSugestaoServicoRepository.findByProdutoId(idProduto);
		return produtosSugestao;
	}

	public void deletarProdutoSugestaoServico(Integer idSugestao) throws ErrorException {

		try {
			ProdutoSugestaoServico ProdutoSugestaoServico = buscarProdutoSugestaoServico(idSugestao).get();
			produtoSugestaoServicoRepository.delete(ProdutoSugestaoServico);
		} catch (Exception e) {
			throw new ErrorException("Erro ao deletar a sugestão de serviço, verifique as dependências!");

		}

	}

	
}
