package br.com.exataoticas.tablens.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.exataoticas.tablens.domain.Funcionario;
import br.com.exataoticas.tablens.enums.Perfil;

@SuppressWarnings("serial")
public class Loggeduser implements UserDetails {
	
	private Funcionario funcionario;
	private Perfil perfil;
	private Collection<? extends GrantedAuthority> roles;
	
	public Loggeduser(Funcionario funcionario) {
		this.funcionario = funcionario;
		this.perfil = Perfil.toEnum(funcionario.getPerfil());
				
		Set<Perfil> perfis = new HashSet<>();
		
		perfis.add(this.perfil);
		
		this.roles = perfis.stream().map(x -> new SimpleGrantedAuthority("ROLE_" + x.getDescricao())).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {				
		
		return roles;
	}

	@Override
	public String getPassword() {
		
		return funcionario.getSenha();
	}

	@Override
	public String getUsername() {
		
		return funcionario.getEmail();
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public Perfil getPerfil() {
		return perfil;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	

}
