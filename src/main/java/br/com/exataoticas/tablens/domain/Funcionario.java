package br.com.exataoticas.tablens.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity

public class Funcionario extends Usuario {
	
	@NotBlank(message = "O perfil deve ser preenchido!")
	private String perfil;

	public Funcionario() {
		super();		
	}	
	
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}	
	
	

}
