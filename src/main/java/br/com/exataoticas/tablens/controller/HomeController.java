package br.com.exataoticas.tablens.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.exataoticas.tablens.util.SecurityUtils;

@Controller
public class HomeController {
	
	@RequestMapping(path = "/home")
	public String menu(Model model) {
		model.addAttribute("usuarioLogado", SecurityUtils.loggedFuncionario().getNome());
		return "layout";
	}

}
