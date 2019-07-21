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
import br.com.ricardo.tron.model.Fornecedor;
import br.com.ricardo.tron.repository.Fornecedores;
import br.com.ricardo.tron.repository.filter.FornecedorFilter;
import br.com.ricardo.tron.service.CadastroFornecedorService;
import br.com.ricardo.tron.service.exception.ImpossivelExcluirEntidadeException;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Controller
@RequestMapping("/fornecedores")
public class FornecedoresController {

	@Autowired
	private CadastroFornecedorService cadastroFornecedorService;
	
	@Autowired
	private Fornecedores fornecedores;

		
	@RequestMapping("/novo")
	public ModelAndView novo(Fornecedor fornecedor) {
		return new ModelAndView("fornecedor/CadastroFornecedor");
	}
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Fornecedor fornecedor, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(fornecedor);
		}
		
		cadastroFornecedorService.salvar(fornecedor);
		attributes.addFlashAttribute("mensagem", "Fornecedor salvo com sucesso!");
		return new ModelAndView("redirect:/fornecedores");
	}
	
	@GetMapping
	public ModelAndView pesquisar(FornecedorFilter fornecedorFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("fornecedor/PesquisaFornecedores");
		
		PageWrapper<Fornecedor> paginaWrapper = new PageWrapper<>(fornecedores.filtrar(fornecedorFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@Secured("ROLE_EXCLUIR_FORNECEDOR")
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Fornecedor fornecedor) {
		try {
			cadastroFornecedorService.excluir(fornecedor);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@Secured("ROLE_ALTERAR_FORNECEDOR")
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Fornecedor fornecedor) {
		ModelAndView mv = novo(fornecedor);
		mv.addObject(fornecedor);
		return mv;
		
	}		
	
}
