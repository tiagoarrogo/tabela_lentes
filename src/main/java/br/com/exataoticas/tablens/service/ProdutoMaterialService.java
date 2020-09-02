package br.com.exataoticas.tablens.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exataoticas.tablens.domain.ProdutoMaterial;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.repository.ProdutoMaterialRepository;

@SuppressWarnings("serial")
@Service
public class ProdutoMaterialService implements Serializable {

	@Autowired
	private ProdutoMaterialRepository produtoMaterialRepository;

	@Transactional
	public void salvarProdutoMaterial(ProdutoMaterial produtoMaterial) throws ValidateException{

		if (existeProdutoMaterialCadastrado(produtoMaterial.getDescricao(), produtoMaterial.getId())) {
			throw new ValidateException("Material já cadastrado!");
		}

		produtoMaterialRepository.save(produtoMaterial);

	}

	private boolean existeProdutoMaterialCadastrado(String produtoMaterial, Integer id) {

		ProdutoMaterial produto = produtoMaterialRepository.findByDescricao(produtoMaterial);

		if (produto == null) {
			if (id == null) {
				return false;
			}
		}

		return true;
	}

	public Optional<ProdutoMaterial> buscarProdutoMaterial(Integer idProdutoMaterial) {

		Optional<ProdutoMaterial> produtoMaterial = produtoMaterialRepository.findById(idProdutoMaterial);

		return produtoMaterial;
	}

	public List<ProdutoMaterial> listarProdutosMaterial() {
		List<ProdutoMaterial> produtosMaterial = produtoMaterialRepository.findAll();
		return produtosMaterial;
	}

	public void deletarProdutoMaterial(Integer idProdutoMaterial) throws ErrorException {

		try {
			ProdutoMaterial produtoMaterial = buscarProdutoMaterial(idProdutoMaterial).get();
			produtoMaterialRepository.delete(produtoMaterial);
		} catch (Exception e) {
			throw new ErrorException("Erro ao deletar o Material de produto, verifique as dependências!");

		}

	}

}
