package br.com.exataoticas.tablens.controller;

import java.util.List;

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


import br.com.exataoticas.tablens.domain.Produto;
import br.com.exataoticas.tablens.domain.ProdutoSugestaoServico;
import br.com.exataoticas.tablens.domain.ProdutoServico;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.service.ProdutoService;
import br.com.exataoticas.tablens.service.ProdutoSugestaoServicoService;
import br.com.exataoticas.tablens.service.ProdutoServicoService;

@Controller
@RequestMapping(path = "/produtoSugestaoServico")
public class ProdutoSugestaoServicoController {
	
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ProdutoServicoService produtoServicoService;
	
	@Autowired
	private ProdutoSugestaoServicoService produtoSugestaoServicoService;
	
	private Integer idProduto;
	
	@RequestMapping(path="/cadastro")
	private String viewSugestao(@RequestParam("idProduto") Integer idProdutoView, Model model) {
		
		idProduto = idProdutoView;
		Produto produto = produtoService.buscarProduto(idProduto).get();
		ProdutoSugestaoServico produtoSugestao = new ProdutoSugestaoServico();
		produtoSugestao.setProduto(produto);
		List<ProdutoServico> produtosServico = produtoServicoService.listarProdutosServico();
		model.addAttribute("produtoSugestaoServico", produtoSugestao);
		model.addAttribute("produtosServico", produtosServico);
		
		return "produtoSugestaoServico-cadastro";
	}
	
	@PostMapping(path = "/salvar")
	public String salvarProdutoSugestaoServico(@ModelAttribute("produtoSugestaoServico") @Valid ProdutoSugestaoServico produtoSugestaoServico, Errors errors, Model model)
			throws ValidateException {

		if (!errors.hasErrors()) {

			try {
				produtoSugestaoServicoService.salvarProdutoSugestaoServico(produtoSugestaoServico);
				Produto produto = produtoService.buscarProduto(idProduto).get();
				ProdutoSugestaoServico produtoSugestao = new ProdutoSugestaoServico();
				produtoSugestao.setProduto(produto);
				List<ProdutoServico> produtosServico = produtoServicoService.listarProdutosServico();
				model.addAttribute("produtoSugestaoServico", produtoSugestao);
				model.addAttribute("produtosServico", produtosServico);
				
				model.addAttribute("msg", "Cadastro efetuado com sucesso!");
			} catch (ValidateException e) {
				errors.rejectValue("tratamento", null, e.getMessage());
			}

		}

		return "produtoSugestaoServico-cadastro";

	}
	
	@RequestMapping(path = "/deletar")
	public String deletarProduto(@RequestParam(name ="idSugestao") Integer idSugestao, Model model) {

		try {
			produtoSugestaoServicoService.deletarProdutoSugestaoServico(idSugestao);
		}catch (ErrorException e) {
			model.addAttribute("msgError", e.getMessage());
		}
		
		
		return "produtoSugestaoServico-listagem";
	}
	
	@GetMapping(path = "/listar")
	public String listarProduto(Model model) {

		List<ProdutoSugestaoServico> produtosSugestaoServico = produtoSugestaoServicoService.listarProdutosSugestaoServico(idProduto);
		model.addAttribute("produtosSugestaoServico", produtosSugestaoServico);
		return "produtoSugestaoServico-listagem";

	}

}
