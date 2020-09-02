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

import br.com.exataoticas.tablens.domain.ProdutoTipo;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.service.ProdutoTipoService;

@Controller
@RequestMapping(path = "/produtoTipo")
public class ProdutoTipoController {
	
	@Autowired
	private ProdutoTipoService produtoTipoService;
	
	@RequestMapping(path = "/cadastro")
	public String produtoView(Model model) {
		model.addAttribute("produtoTipo", new ProdutoTipo());
		ControllerHelper.listaPerfis(model);
		return "produtoTipo-cadastro";
	}
	
	@GetMapping(path = "/listar")
	public String listarProdutoTipo(Model model) {
		
		List<ProdutoTipo> produtosTipo = produtoTipoService.listarProdutosTipo();
		model.addAttribute("produtosTipo", produtosTipo);
		return "produtoTipo-listagem";
		
	}
	
	
	@PostMapping(path = "/salvar")
	public String salvarProdutoTipo(@ModelAttribute("produtoTipo") @Valid ProdutoTipo produtoTipo, Errors errors, Model model) {
		
		if(!errors.hasErrors()) {
			
			try {
				produtoTipoService.salvarProdutoTipo(produtoTipo);
				model.addAttribute("produtoTipo", new ProdutoTipo());
				model.addAttribute("msg", "Cadastro efetuado com sucesso!");
				
			} catch (ValidateException e) {
				errors.rejectValue("descricao", null, e.getMessage());				
			}
			
		}
		
		return "produtoTipo-cadastro";
		
	}
	
	@RequestMapping(path = "/editar")
	public String editarProdutoTipo(@RequestParam("idProdutoTipo") Integer idProdutoTipo, Model model) {
		
		Optional<ProdutoTipo> produtoTipo = produtoTipoService.buscarProdutoTipo(idProdutoTipo);
		model.addAttribute("produtoTipo", produtoTipo);
		return "produtoTipo-cadastro";
	}
	
	@RequestMapping(path = "/deletar")
	public String deletarProdutoTipo(@RequestParam("idProdutoTipo") Integer idProdutoTipo, Model model) {
		
		try {
			produtoTipoService.deletarProdutoTipo(idProdutoTipo);
			List<ProdutoTipo> produtosTipo = produtoTipoService.listarProdutosTipo();
			model.addAttribute("produtosTipo", produtosTipo);
		} catch (ErrorException e) {
			model.addAttribute("msgError", e.getMessage());
		}
		return "produtoTipo-listagem";
	}
	

}
