package br.com.exataoticas.tablens.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.exataoticas.tablens.domain.Funcionario;
import br.com.exataoticas.tablens.repository.FuncionarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Funcionario funcionario = funcionarioRepository.findByEmail(username);
		
		if(funcionario == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new Loggeduser(funcionario);
	}

	
}
