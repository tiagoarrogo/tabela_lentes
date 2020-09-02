package br.com.exataoticas.tablens.enums;


public enum Perfil {
	
	ADMIN(1, "ADMIN"),
	USER(2, "USER");
	
	private int cod;
	private String descricao;

	Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
		
	
public static Perfil toEnum(String descricao) {
		
		if(descricao == null) {
			
			return null;
		}
		
		for(Perfil x : Perfil.values()) {
			
			if(descricao.equals(x.getDescricao())) {
				
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + descricao);
	}

}
