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

import br.com.exataoticas.tablens.domain.ProdutoServico;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.service.ProdutoServicoService;

@Controller
@RequestMapping(path = "/produtoServico")
public class ProdutoServicoController {
	
	@Autowired
	private ProdutoServicoService produtoServicoService;
	
	@RequestMapping(path = "/cadastro")
	public String produtoView(Model model) {
		model.addAttribute("produtoServico", new ProdutoServico());
		ControllerHelper.listaPerfis(model);
		return "produtoServico-cadastro";
	}
	
	@GetMapping(path = "/listar")
	public String listarProdutoServico(Model model) {
		
		List<ProdutoServico> produtosServico = produtoServicoService.listarProdutosServico();
		model.addAttribute("produtosServico", produtosServico);
		return "ProdutoServico-listagem";
		
	}
	
	
	@PostMapping(path = "/salvar")
	public String salvarProdutoServico(@ModelAttribute("produtoServico") @Valid ProdutoServico produtoServico, Errors errors, Model model) {
		
		if(!errors.hasErrors()) {
			
			try {
				produtoServicoService.salvarProdutoServico(produtoServico);
				model.addAttribute("produtoServico", new ProdutoServico());
				model.addAttribute("msg", "Cadastro efetuado com sucesso!");
				
			} catch (ValidateException e) {
				errors.rejectValue("descricao", null, e.getMessage());				
			}
			
		}
		
		return "produtoServico-cadastro";
		
	}
	
	@RequestMapping(path = "/editar")
	public String editarProdutoServico(@RequestParam("idProdutoServico") Integer idProdutoServico, Model model) {
		
		Optional<ProdutoServico> produtoServico = produtoServicoService.buscarProdutoServico(idProdutoServico);
		model.addAttribute("produtoServico", produtoServico);
		return "ProdutoServico-cadastro";
	}
	
	@RequestMapping(path = "/deletar")
	public String deletarProdutoServico(@RequestParam("idProdutoServico") Integer idProdutoServico, Model model) {
		
		try {
			produtoServicoService.deletarProdutoServico(idProdutoServico);
			List<ProdutoServico> produtosServico = produtoServicoService.listarProdutosServico();
			model.addAttribute("produtosServico", produtosServico);
		} catch (ErrorException e) {
			model.addAttribute("msgError", e.getMessage());
		}
		return "produtoServico-listagem";
	}
	

}
