package br.com.exataoticas.tablens.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.exataoticas.tablens.domain.Funcionario;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.service.FuncionarioService;


@Controller
@RequestMapping(path = "/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@RequestMapping(path = "/cadastro")
	public String usuario(Model model) {
		
		model.addAttribute("funcionario", new Funcionario());
		ControllerHelper.listaPerfis(model);
		return "funcionario-cadastro";
	}
	
	@GetMapping(path = "/listar")
	public String listarFuncionario(Model model) {
		
		List<Funcionario> funcionarios = funcionarioService.listarFuncionario();
		model.addAttribute("funcionarios", funcionarios);
		
		return "funcionario-listagem";
	}
	
	@PostMapping(path = "/salvar")
	public String salvarFuncionario(@ModelAttribute("funcionario") @Valid Funcionario funcionario, Errors errors, Model model) {
		
		if(!errors.hasErrors()) {
			
			try {
				funcionarioService.salvarFuncionario(funcionario);
				model.addAttribute("funcionario", new Funcionario());
				model.addAttribute("msg", "Cadastro efetuado com sucesso!");
				
			} catch (ValidateException e) {
				errors.rejectValue("email", null, e.getMessage());				
			}
			
		}
		ControllerHelper.listaPerfis(model);
		return "funcionario-cadastro";
	}

	@GetMapping(path = "/editar")
	public String editarFuncionario(@RequestParam("idFuncionario") Integer idFuncionario, Model model) {
		
		Optional<Funcionario> funcionario = funcionarioService.buscarFuncionario(idFuncionario);
		model.addAttribute("funcionario", funcionario);
		ControllerHelper.listaPerfis(model);
		return "funcionario-Cadastro";
		
	}
	@GetMapping(path ="/deletar" )
	public String deletarFuncionario(@RequestParam("idFuncionario") Integer idFuncionario, Model model) {
		
		try {
			funcionarioService.deletarFuncionario(idFuncionario);
			List<Funcionario> funcionarios = funcionarioService.listarFuncionario();
			model.addAttribute("funcionarios", funcionarios);
		} catch (ErrorException e) {
			model.addAttribute("msgError", e.getMessage());
		}
		
		return "funcionario-listagem";
	}
	
}
