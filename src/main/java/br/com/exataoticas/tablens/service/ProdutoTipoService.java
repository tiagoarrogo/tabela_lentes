package br.com.exataoticas.tablens.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exataoticas.tablens.domain.ProdutoTipo;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.repository.ProdutoTipoRepository;

@SuppressWarnings("serial")
@Service
public class ProdutoTipoService implements Serializable {
	
	@Autowired
	private ProdutoTipoRepository produtoTipoRepository;
	
	@Transactional
	public void salvarProdutoTipo(ProdutoTipo produtoTipo) throws ValidateException{

		if (existeProdutoTipoCadastrado(produtoTipo.getDescricao(), produtoTipo.getId())) {
			throw new ValidateException("Tipo de Produto já cadastrado!");
		}

		produtoTipoRepository.save(produtoTipo);

	}

	private boolean existeProdutoTipoCadastrado(String produtoTipo, Integer id) {

		ProdutoTipo produto = produtoTipoRepository.findByDescricao(produtoTipo);

		if (produto == null) {
			if (id == null) {
				return false;
			}
		}

		return true;
	}

	public Optional<ProdutoTipo> buscarProdutoTipo(Integer idProdutoTipo) {

		Optional<ProdutoTipo> produtoTipo = produtoTipoRepository.findById(idProdutoTipo);

		return produtoTipo;
	}

	public List<ProdutoTipo> listarProdutosTipo() {
		List<ProdutoTipo> produtosTipo = produtoTipoRepository.findAll();
		return produtosTipo;
	}

	public void deletarProdutoTipo(Integer idProdutoTipo) throws ErrorException {

		try {
			ProdutoTipo produtoTipo = buscarProdutoTipo(idProdutoTipo).get();
			produtoTipoRepository.delete(produtoTipo);
		} catch (Exception e) {
			throw new ErrorException("Erro ao deletar a Tipo de produto, verifique as dependências!");

		}

	}

	
}
