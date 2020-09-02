package br.com.exataoticas.tablens.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exataoticas.tablens.domain.Funcionario;
import br.com.exataoticas.tablens.repository.FuncionarioRepository;

@Service
public class ValidaUsuarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
		
	
	public boolean usuarioCadastrado(String email) {
		
	  Funcionario funcionario =	funcionarioRepository.findByEmail(email);
	  
	  if(funcionario != null) {
		  return true;
	  }
				
	  return false;
	}

}
