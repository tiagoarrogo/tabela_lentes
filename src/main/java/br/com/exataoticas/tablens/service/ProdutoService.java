package br.com.exataoticas.tablens.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exataoticas.tablens.domain.Produto;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.repository.ProdutoRepository;

@SuppressWarnings("serial")
@Service
public class ProdutoService implements Serializable {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public void salvarProduto(Produto produto) throws ValidateException{

		produtoRepository.save(produto);

	}

	
	public Optional<Produto> buscarProduto(Integer idProduto) {

		Optional<Produto> produto = produtoRepository.findById(idProduto);

		return produto;
	}

	public List<Produto> listarProdutos() {
		List<Produto> produtos = produtoRepository.findAll();
		return produtos;
	}

	public void deletarProduto(Integer idProduto) throws ErrorException {

		try {
			Produto produto = buscarProduto(idProduto).get();
			produtoRepository.delete(produto);
		} catch (Exception e) {
			throw new ErrorException("Erro ao deletar o produto, verifique as dependências!");

		}

	}

}
