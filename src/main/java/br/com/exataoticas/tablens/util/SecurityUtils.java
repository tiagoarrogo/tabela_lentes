package br.com.exataoticas.tablens.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.exataoticas.tablens.domain.Funcionario;
import br.com.exataoticas.tablens.security.Loggeduser;

public class SecurityUtils {
	
	public static Loggeduser loggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		//Autenticacao sem logar
		if (authentication instanceof AnonymousAuthenticationToken) {
			return null;
		}
		
		return (Loggeduser) authentication.getPrincipal();
	}
	
	public static Funcionario loggedFuncionario() {
		Loggeduser loggedUser = loggedUser();
		
		if(loggedUser == null) {
			throw new IllegalStateException("Não existe usuário logado!");
		}
		
		if(!(loggedUser.getFuncionario() instanceof Funcionario)) {
			throw new IllegalStateException("O usuário logado não é um funcionário!");
		}
		
		return (Funcionario) loggedUser.getFuncionario();
	}
}
