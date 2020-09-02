package br.com.exataoticas.tablens.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exataoticas.tablens.domain.Fornecedor;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.repository.FornecedorRepository;

@SuppressWarnings("serial")
@Service
public class FornecedorService implements Serializable {

	@Autowired
	private FornecedorRepository FornecedorRepository;

	@Transactional
	public void salvarFornecedor(Fornecedor Fornecedor) throws ValidateException{

		if (existeFornecedorCadastrado(Fornecedor.getNome(), Fornecedor.getId())) {
			throw new ValidateException("Fornecedor já cadastrado!");
		}

		FornecedorRepository.save(Fornecedor);

	}

	private boolean existeFornecedorCadastrado(String Fornecedor, Integer id) {

		Fornecedor fornecedor = FornecedorRepository.findByNome(Fornecedor);

		if (fornecedor == null) {
			if (id == null) {
				return false;
			}
		}

		return true;
	}

	public Optional<Fornecedor> buscarFornecedor(Integer idFornecedor) {

		Optional<Fornecedor> Fornecedor = FornecedorRepository.findById(idFornecedor);

		return Fornecedor;
	}

	public List<Fornecedor> listarFornecedor() {
		List<Fornecedor> fornecedor = FornecedorRepository.findAll();
		return fornecedor;
	}

	public void deletarFornecedor(Integer idFornecedor) throws ErrorException {

		try {
			Fornecedor Fornecedor = buscarFornecedor(idFornecedor).get();
			FornecedorRepository.delete(Fornecedor);
		} catch (Exception e) {
			throw new ErrorException("Erro ao deletar o Fornecedor, verifique as dependências!");

		}

	}

}
