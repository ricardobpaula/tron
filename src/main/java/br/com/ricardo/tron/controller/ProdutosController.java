package br.com.ricardo.tron.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ricardo.tron.controller.page.PageWrapper;
import br.com.ricardo.tron.model.Produto;
import br.com.ricardo.tron.repository.Fornecedores;
import br.com.ricardo.tron.repository.Produtos;
import br.com.ricardo.tron.repository.filter.ProdutoFilter;
import br.com.ricardo.tron.service.CadastroProdutoService;
import br.com.ricardo.tron.service.exception.CodigoProdutoJaCadastradoException;
import br.com.ricardo.tron.service.exception.ImpossivelExcluirEntidadeException;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Controller
@RequestMapping("/produtos")
public class ProdutosController {


	@Autowired
	private Fornecedores fornecedores;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private Produtos produtos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView("produto/CadastroProduto");
		mv.addObject("fornecedores", fornecedores.findAll()); 

		return mv;
	}	
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Produto produto, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(produto);
		}
		
		//chamada do metodo de calculo do preço de venda do produto
		produto.setPrecoVenda(produto.valor());
		
		try {
			cadastroProdutoService.salvar(produto);
		}catch (CodigoProdutoJaCadastradoException e) {
			result.rejectValue("codigo", e.getMessage(), e.getMessage());
			return novo(produto);
		}
		
		attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
		return new ModelAndView("redirect:/produtos");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ProdutoFilter produtoFilter, BindingResult result, 
			@PageableDefault(size =5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("produto/PesquisaProdutos");
		mv.addObject("fornecedores", fornecedores.findAll()); 
		
		
	
		
		PageWrapper<Produto> paginaWrapper = new PageWrapper<>(produtos.filtrar(produtoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		

		return mv;
	}

	@Secured("ROLE_EXCLUIR_PRODUTO")
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Produto produto) {
		try {
			cadastroProdutoService.excluir(produto);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@Secured("ROLE_ALTERAR_PRODUTO")
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Produto produto) {
		ModelAndView mv = novo(produto);
		mv.addObject(produto);
		return mv;
		
	}
	
	
	
}
