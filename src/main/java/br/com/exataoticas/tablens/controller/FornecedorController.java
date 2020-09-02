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

import br.com.exataoticas.tablens.domain.Fornecedor;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.service.FornecedorService;

@Controller
@RequestMapping(path = "/fornecedor")
public class FornecedorController {
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@RequestMapping(path = "/cadastro")
	public String produtoView(Model model) {
		model.addAttribute("fornecedor", new Fornecedor());
		ControllerHelper.listaPerfis(model);
		return "fornecedor-cadastro";
	}
	
	@GetMapping(path = "/listar")
	public String listarFornecedor(Model model) {
		
		List<Fornecedor> fornecedores = fornecedorService.listarFornecedor();
		model.addAttribute("fornecedores", fornecedores);
		return "fornecedor-listagem";
		
	}
	
	
	@PostMapping(path = "/salvar")
	public String salvarFornecedor(@ModelAttribute("fornecedor") @Valid Fornecedor fornecedor, Errors errors, Model model) throws ValidateException {
		
		if(!errors.hasErrors()) {
			
			try {
				fornecedorService.salvarFornecedor(fornecedor);
				model.addAttribute("fornecedor", new Fornecedor());
				model.addAttribute("msg", "Cadastro efetuado com sucesso!");
			} catch (ValidateException e) {
				errors.rejectValue("nome", null, e.getMessage());
			}
			
		}
		
		return "fornecedor-cadastro";
		
	}
	
	@RequestMapping(path = "/editar")
	public String editarFornecedor(@RequestParam("idFornecedor") Integer idFornecedor, Model model) {
		
		Optional<Fornecedor> fornecedor = fornecedorService.buscarFornecedor(idFornecedor);
		model.addAttribute("fornecedor", fornecedor);
		return "fornecedor-cadastro";
	}
	
	@RequestMapping(path = "/deletar")
	public String deletarFornecedor(@RequestParam("idFornecedor") Integer idFornecedor, Model model) {
		
		try {
			fornecedorService.deletarFornecedor(idFornecedor);
			List<Fornecedor> fornecedores = fornecedorService.listarFornecedor();
			model.addAttribute("fornecedores", fornecedores);
		} catch (ErrorException e) {
			model.addAttribute("msgError", e.getMessage());
		}
		return "fornecedor-listagem";
	}
	

}
