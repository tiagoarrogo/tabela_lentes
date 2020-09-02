package br.com.exataoticas.tablens.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exataoticas.tablens.domain.Funcionario;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	@Autowired
	private ValidaUsuarioService validaUsuarioService;
	
	@Transactional
	public void salvarFuncionario(Funcionario funcionario) throws ValidateException {
		
		if(funcionario.getId() == null) {
		
			if(validaUsuarioService.usuarioCadastrado(funcionario.getEmail())) {
				
				throw new ValidateException("Já existe um funcionário cadastrado com esse e-mail!");
				
			}
		}
		funcionario.encrptPassword();
		funcionarioRepository.save(funcionario);
		
	}

	public List<Funcionario> listarFuncionario() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		return funcionarios;
	}

	public Optional<Funcionario> buscarFuncionario(Integer idFuncionario) {
		
		Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
		return funcionario;
	}

	public void deletarFuncionario(Integer idFuncionario) throws ErrorException {
		
		try {
			Optional<Funcionario> funcionario = buscarFuncionario(idFuncionario);
			funcionarioRepository.delete(funcionario.get());
		} catch (Exception e) {
			throw new ErrorException("Não foi possível deletar o usuário!");
		}
		
	}

}
