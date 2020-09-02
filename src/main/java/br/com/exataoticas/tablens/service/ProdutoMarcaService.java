package br.com.exataoticas.tablens.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exataoticas.tablens.domain.ProdutoMarca;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.repository.ProdutoMarcaRepository;

@SuppressWarnings("serial")
@Service
public class ProdutoMarcaService implements Serializable {

	@Autowired
	private ProdutoMarcaRepository produtoMarcaRepository;

	@Transactional
	public void salvarProdutoMarca(ProdutoMarca produtoMarca) throws ValidateException{

		if (existeProdutoMarcaCadastrado(produtoMarca.getDescricao(), produtoMarca.getId())) {
			throw new ValidateException("Tipo de Produto já cadastrado!");
		}

		produtoMarcaRepository.save(produtoMarca);

	}

	private boolean existeProdutoMarcaCadastrado(String produtoMarca, Integer id) {

		ProdutoMarca produto = produtoMarcaRepository.findByDescricao(produtoMarca);

		if (produto == null) {
			if (id == null) {
				return false;
			}
		}

		return true;
	}

	public Optional<ProdutoMarca> buscarProdutoMarca(Integer idProdutoMarca) {

		Optional<ProdutoMarca> produtoMarca = produtoMarcaRepository.findById(idProdutoMarca);

		return produtoMarca;
	}

	public List<ProdutoMarca> listarProdutosMarca() {
		List<ProdutoMarca> produtosMarca = produtoMarcaRepository.findAll();
		return produtosMarca;
	}

	public void deletarProdutoMarca(Integer idProdutoMarca) throws ErrorException {

		try {
			ProdutoMarca produtoMarca = buscarProdutoMarca(idProdutoMarca).get();
			produtoMarcaRepository.delete(produtoMarca);
		} catch (Exception e) {
			throw new ErrorException("Erro ao deletar a Marca de produto, verifique as dependências!");

		}

	}

}
