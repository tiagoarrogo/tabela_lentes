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

import br.com.exataoticas.tablens.domain.ProdutoMarca;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.service.ProdutoMarcaService;

@Controller
@RequestMapping(path = "/produtoMarca")
public class ProdutoMarcaController {
	
	@Autowired
	private ProdutoMarcaService produtoMarcaService;
	
	@RequestMapping(path = "/cadastro")
	public String produtoView(Model model) {
		model.addAttribute("produtoMarca", new ProdutoMarca());
		ControllerHelper.listaPerfis(model);
		return "produtoMarca-cadastro";
	}
	
	@GetMapping(path = "/listar")
	public String listarProdutoMarca(Model model) {
		
		List<ProdutoMarca> produtosMarca = produtoMarcaService.listarProdutosMarca();
		model.addAttribute("produtosMarca", produtosMarca);
		return "produtoMarca-listagem";
		
	}
	
	
	@PostMapping(path = "/salvar")
	public String salvarProdutoMarca(@ModelAttribute("produtoMarca") @Valid ProdutoMarca produtoMarca, Errors errors, Model model) throws ValidateException {
		
		if(!errors.hasErrors()) {
			
			try {
				produtoMarcaService.salvarProdutoMarca(produtoMarca);
				model.addAttribute("produtoMarca", new ProdutoMarca());
				model.addAttribute("msg", "Cadastro efetuado com sucesso!");
			} catch (ValidateException e) {
				errors.rejectValue("descricao", null, e.getMessage());
			}
			
		}
		
		return "produtoMarca-cadastro";
		
	}
	
	@RequestMapping(path = "/editar")
	public String editarProdutoMarca(@RequestParam("idProdutoMarca") Integer idProdutoMarca, Model model) {
		
		Optional<ProdutoMarca> produtoMarca = produtoMarcaService.buscarProdutoMarca(idProdutoMarca);
		model.addAttribute("produtoMarca", produtoMarca);
		return "produtoMarca-cadastro";
	}
	
	@RequestMapping(path = "/deletar")
	public String deletarProdutoMarca(@RequestParam("idProdutoMarca") Integer idProdutoMarca, Model model) {
		
		try {
			produtoMarcaService.deletarProdutoMarca(idProdutoMarca);
			List<ProdutoMarca> produtosMarca = produtoMarcaService.listarProdutosMarca();
			model.addAttribute("produtosMarca", produtosMarca);
		} catch (ErrorException e) {
			model.addAttribute("msgError", e.getMessage());
		}
		return "produtoMarca-listagem";
	}
	

}
