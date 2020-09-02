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

import br.com.exataoticas.tablens.domain.ProdutoMaterial;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.service.ProdutoMaterialService;

@Controller
@RequestMapping(path = "/produtoMaterial")
public class ProdutoMaterialController {
	
	@Autowired
	private ProdutoMaterialService produtoMaterialService;
	
	@RequestMapping(path = "/cadastro")
	public String produtoView(Model model) {
		model.addAttribute("produtoMaterial", new ProdutoMaterial());
		ControllerHelper.listaPerfis(model);
		return "produtoMaterial-cadastro";
	}
	
	@GetMapping(path = "/listar")
	public String listarProdutoMaterial(Model model) {
		
		List<ProdutoMaterial> produtosMaterial = produtoMaterialService.listarProdutosMaterial();
		model.addAttribute("produtosMaterial", produtosMaterial);
		return "produtoMaterial-listagem";
		
	}
	
	
	@PostMapping(path = "/salvar")
	public String salvarProdutoMaterial(@ModelAttribute("produtoMaterial") @Valid ProdutoMaterial produtoMaterial, Errors errors, Model model) throws ValidateException {
		
		if(!errors.hasErrors()) {
			
			try {
				produtoMaterialService.salvarProdutoMaterial(produtoMaterial);
				model.addAttribute("produtoMaterial", new ProdutoMaterial());
				model.addAttribute("msg", "Cadastro efetuado com sucesso!");
			} catch (ValidateException e) {
				errors.rejectValue("descricao", null, e.getMessage());
			}
			
		}
		
		return "produtoMaterial-cadastro";
		
	}
	
	@RequestMapping(path = "/editar")
	public String editarProdutoMaterial(@RequestParam("idProdutoMaterial") Integer idProdutoMaterial, Model model) {
		
		Optional<ProdutoMaterial> produtoMaterial = produtoMaterialService.buscarProdutoMaterial(idProdutoMaterial);
		model.addAttribute("produtoMaterial", produtoMaterial);
		return "produtoMaterial-cadastro";
	}
	
	@RequestMapping(path = "/deletar")
	public String deletarProdutoMaterial(@RequestParam("idProdutoMaterial") Integer idProdutoMaterial, Model model) {
		
		try {
			produtoMaterialService.deletarProdutoMaterial(idProdutoMaterial);
			List<ProdutoMaterial> produtosMaterial = produtoMaterialService.listarProdutosMaterial();
			model.addAttribute("produtosMaterial", produtosMaterial);
		} catch (ErrorException e) {
			model.addAttribute("msgError", e.getMessage());
		}
		return "produtoMaterial-listagem";
	}
	

}
