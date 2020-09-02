package br.com.exataoticas.tablens.controller;


import org.springframework.ui.Model;
import br.com.exataoticas.tablens.enums.Perfil;


public class ControllerHelper {

	public static void listaPerfis(Model model) {
		model.addAttribute("perfis", Perfil.values());
	}

	

}
