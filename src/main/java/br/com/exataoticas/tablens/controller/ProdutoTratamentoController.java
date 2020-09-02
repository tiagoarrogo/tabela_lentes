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

import br.com.exataoticas.tablens.domain.ProdutoTratamento;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.service.ProdutoTratamentoService;

@Controller
@RequestMapping(path = "/produtoTratamento")
public class ProdutoTratamentoController {
	
	@Autowired
	private ProdutoTratamentoService produtoTratamentoService;
	
	@RequestMapping(path = "/cadastro")
	public String produtoView(Model model) {
		model.addAttribute("produtoTratamento", new ProdutoTratamento());
		ControllerHelper.listaPerfis(model);
		return "produtoTratamento-cadastro";
	}
	
	@GetMapping(path = "/listar")
	public String listarProdutoTratamento(Model model) {
		
		List<ProdutoTratamento> produtosTratamento = produtoTratamentoService.listarProdutosTratamento();
		model.addAttribute("produtosTratamento", produtosTratamento);
		return "ProdutoTratamento-listagem";
		
	}
	
	
	@PostMapping(path = "/salvar")
	public String salvarProdutoTratamento(@ModelAttribute("produtoTratamento") @Valid ProdutoTratamento produtoTratamento, Errors errors, Model model) {
		
		if(!errors.hasErrors()) {
			
			try {
				produtoTratamentoService.salvarProdutoTratamento(produtoTratamento);
				model.addAttribute("produtoTratamento", new ProdutoTratamento());
				model.addAttribute("msg", "Cadastro efetuado com sucesso!");
				
			} catch (ValidateException e) {
				errors.rejectValue("descricao", null, e.getMessage());				
			}
			
		}
		
		return "produtoTratamento-cadastro";
		
	}
	
	@RequestMapping(path = "/editar")
	public String editarProdutoTratamento(@RequestParam("idProdutoTratamento") Integer idProdutoTratamento, Model model) {
		
		Optional<ProdutoTratamento> produtoTratamento = produtoTratamentoService.buscarProdutoTratamento(idProdutoTratamento);
		model.addAttribute("produtoTratamento", produtoTratamento);
		return "ProdutoTratamento-cadastro";
	}
	
	@RequestMapping(path = "/deletar")
	public String deletarProdutoTratamento(@RequestParam("idProdutoTratamento") Integer idProdutoTratamento, Model model) {
		
		try {
			produtoTratamentoService.deletarProdutoTratamento(idProdutoTratamento);
			List<ProdutoTratamento> produtosTratamento = produtoTratamentoService.listarProdutosTratamento();
			model.addAttribute("produtosTratamento", produtosTratamento);
		} catch (ErrorException e) {
			model.addAttribute("msgError", e.getMessage());
		}
		return "produtoTratamento-listagem";
	}
	

}
