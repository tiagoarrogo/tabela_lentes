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
import br.com.exataoticas.tablens.domain.Produto;
import br.com.exataoticas.tablens.domain.ProdutoMarca;
import br.com.exataoticas.tablens.domain.ProdutoMaterial;
import br.com.exataoticas.tablens.domain.ProdutoServico;
import br.com.exataoticas.tablens.domain.ProdutoTipo;
import br.com.exataoticas.tablens.domain.ProdutoTratamento;
import br.com.exataoticas.tablens.exception.ErrorException;
import br.com.exataoticas.tablens.exception.ValidateException;
import br.com.exataoticas.tablens.service.FornecedorService;
import br.com.exataoticas.tablens.service.ProdutoMarcaService;
import br.com.exataoticas.tablens.service.ProdutoMaterialService;
import br.com.exataoticas.tablens.service.ProdutoService;
import br.com.exataoticas.tablens.service.ProdutoServicoService;
import br.com.exataoticas.tablens.service.ProdutoTipoService;
import br.com.exataoticas.tablens.service.ProdutoTratamentoService;

@Controller
@RequestMapping(path = "/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ProdutoTipoService produtoTipoService;
	@Autowired
	private ProdutoMarcaService produtoMarcaService;
	@Autowired
	private ProdutoMaterialService produtoMaterialService;
	@Autowired
	private ProdutoTratamentoService produtoTratamentoService;
	@Autowired
	private ProdutoServicoService produtoServicoService;
	@Autowired
	private FornecedorService fornecedorService;

	@RequestMapping(path = "/cadastro")
	public String produtoView(Model model) {
		model.addAttribute("produto", new Produto());
		listarAtributosProduto(model);
		return "produto-cadastro";
	}

	@GetMapping(path = "/listar")
	public String listarProduto(Model model) {

		List<Produto> produtos = produtoService.listarProdutos();
		model.addAttribute("produtos", produtos);
		return "produto-listagem";

	}

	@PostMapping(path = "/salvar")
	public String salvarProduto(@ModelAttribute("produto") @Valid Produto produto, Errors errors, Model model)
			throws ValidateException {

		if (!errors.hasErrors()) {

			try {
				produtoService.salvarProduto(produto);
				model.addAttribute("produto", new Produto());
				listarAtributosProduto(model);
				model.addAttribute("msg", "Cadastro efetuado com sucesso!");
			} catch (ValidateException e) {
				errors.rejectValue("descricao", null, e.getMessage());
			}

		}

		return "produto-cadastro";

	}

	@RequestMapping(path = "/editar")
	public String editarProduto(@RequestParam("idProduto") Integer idProduto, Model model) {

		Optional<Produto> produto = produtoService.buscarProduto(idProduto);
		listarAtributosProduto(model);
		model.addAttribute("produto", produto);
		return "produto-cadastro";
	}

	@RequestMapping(path = "/deletar")
	public String deletarProduto(@RequestParam("idProduto") Integer idProduto, Model model) {

		try {
			produtoService.deletarProduto(idProduto);
			List<Produto> produtos = produtoService.listarProdutos();
			model.addAttribute("produtos", produtos);
		} catch (ErrorException e) {
			model.addAttribute("msgError", e.getMessage());
		}
		return "produto-listagem";
	}

	public void listarProdutoTipo(Model model) {

		List<ProdutoTipo> produtosTipo = produtoTipoService.listarProdutosTipo();
		model.addAttribute("produtosTipo", produtosTipo);

	}

	public void listarProdutoMarca(Model model) {

		List<ProdutoMarca> produtosMarca = produtoMarcaService.listarProdutosMarca();
		model.addAttribute("produtosMarca", produtosMarca);

	}

	public void listarProdutoMaterial(Model model) {

		List<ProdutoMaterial> produtosMaterial = produtoMaterialService.listarProdutosMaterial();
		model.addAttribute("produtosMaterial", produtosMaterial);

	}

	public void listarProdutoTratamento(Model model) {

		List<ProdutoTratamento> produtosTratamento = produtoTratamentoService.listarProdutosTratamento();
		model.addAttribute("produtosTratamento", produtosTratamento);

	}

	public void listarProdutoServico(Model model) {

		List<ProdutoServico> produtosServico = produtoServicoService.listarProdutosServico();
		model.addAttribute("produtosServico", produtosServico);

	}

	public void listarFornecedor(Model model) {

		List<Fornecedor> fornecedores = fornecedorService.listarFornecedor();
		model.addAttribute("fornecedores", fornecedores);

	}

	public void listarAtributosProduto(Model model) {
		listarProdutoTipo(model);
		listarProdutoMarca(model);
		listarProdutoMaterial(model);
		listarProdutoTratamento(model);
		listarProdutoServico(model);
		listarFornecedor(model);

	}
}
