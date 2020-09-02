package br.com.exataoticas.tablens.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exataoticas.tablens.domain.ProdutoServico;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.repository.ProdutoServicoRepository;

@SuppressWarnings("serial")
@Service
public class ProdutoServicoService implements Serializable {
	
	@Autowired
	private ProdutoServicoRepository produtoServicoRepository;
	
	@Transactional
	public void salvarProdutoServico(ProdutoServico ProdutoServico) throws ValidateException{
		
		produtoServicoRepository.save(ProdutoServico);

	}
	
	public Optional<ProdutoServico> buscarProdutoServico(Integer idProdutoServico) {

		Optional<ProdutoServico> ProdutoServico = produtoServicoRepository.findById(idProdutoServico);

		return ProdutoServico;
	}

	public List<ProdutoServico> listarProdutosServico() {
		List<ProdutoServico> produtosServico = produtoServicoRepository.findAll();
		return produtosServico;
	}

	public void deletarProdutoServico(Integer idProdutoServico) throws ErrorException {

		try {
			ProdutoServico ProdutoServico = buscarProdutoServico(idProdutoServico).get();
			produtoServicoRepository.delete(ProdutoServico);
		} catch (Exception e) {
			throw new ErrorException("Erro ao deletar o serviço, verifique as dependências!");

		}

	}

	
}
