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
import br.com.exataoticas.tablens.domain.ProdutoSugestaoTratamento;
import br.com.exataoticas.tablens.domain.ProdutoTratamento;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.service.ProdutoService;
import br.com.exataoticas.tablens.service.ProdutoSugestaoTratamentoService;
import br.com.exataoticas.tablens.service.ProdutoTratamentoService;

@Controller
@RequestMapping(path = "/produtoSugestaoTratamento")
public class ProdutoSugestaoTratamentoController {
	
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ProdutoTratamentoService produtoTratamentoService;
	
	@Autowired
	private ProdutoSugestaoTratamentoService produtoSugestaoTratamentoService;
	
	private Integer idProduto;
	
	@RequestMapping(path="/cadastro")
	private String viewSugestao(@RequestParam("idProduto") Integer idProdutoView, Model model) {
		
		idProduto = idProdutoView;
		Produto produto = produtoService.buscarProduto(idProduto).get();
		ProdutoSugestaoTratamento produtoSugestao = new ProdutoSugestaoTratamento();
		produtoSugestao.setProduto(produto);
		List<ProdutoTratamento> produtosTratamento = produtoTratamentoService.listarProdutosTratamento();
		model.addAttribute("produtoSugestaoTratamento", produtoSugestao);
		model.addAttribute("produtosTratamento", produtosTratamento);
		
		return "produtoSugestaoTratamento-cadastro";
	}
	
	@PostMapping(path = "/salvar")
	public String salvarProdutoSugestaoTratamento(@ModelAttribute("produtoSugestaoTratamento") @Valid ProdutoSugestaoTratamento produtoSugestaoTratamento, Errors errors, Model model)
			throws ValidateException {

		if (!errors.hasErrors()) {

			try {
				produtoSugestaoTratamentoService.salvarProdutoSugestaoTratamento(produtoSugestaoTratamento);
				Produto produto = produtoService.buscarProduto(idProduto).get();
				ProdutoSugestaoTratamento produtoSugestao = new ProdutoSugestaoTratamento();
				produtoSugestao.setProduto(produto);
				List<ProdutoTratamento> produtosTratamento = produtoTratamentoService.listarProdutosTratamento();
				model.addAttribute("produtoSugestaoTratamento", produtoSugestao);
				model.addAttribute("produtosTratamento", produtosTratamento);
				
				model.addAttribute("msg", "Cadastro efetuado com sucesso!");
			} catch (ValidateException e) {
				errors.rejectValue("tratamento", null, e.getMessage());
			}

		}

		return "produtoSugestaoTratamento-cadastro";

	}
	
	@RequestMapping(path = "/deletar")
	public String deletarProduto(@RequestParam(name ="idSugestao") Integer idSugestao, Model model) {

		try {
			produtoSugestaoTratamentoService.deletarProdutoSugestaoTratamento(idSugestao);
		}catch (ErrorException e) {
			model.addAttribute("msgError", e.getMessage());
		}
		
		
		return "produtoSugestaoTratamento-listagem";
	}
	
	@GetMapping(path = "/listar")
	public String listarProduto(Model model) {

		List<ProdutoSugestaoTratamento> produtosSugestaoTratamento = produtoSugestaoTratamentoService.listarProdutosSugestaoTratamento(idProduto);
		model.addAttribute("produtosSugestaoTratamento", produtosSugestaoTratamento);
		return "produtoSugestaoTratamento-listagem";

	}

}
