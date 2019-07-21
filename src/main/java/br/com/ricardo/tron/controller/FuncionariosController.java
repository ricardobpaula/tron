package br.com.ricardo.tron.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.ricardo.tron.model.Funcionario;
import br.com.ricardo.tron.service.CadastroFuncionarioService;
import br.com.ricardo.tron.service.exception.ImpossivelExcluirEntidadeException;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Controller
@RequestMapping("/funcionarios")
public class FuncionariosController {
	
	@Autowired
	private CadastroFuncionarioService cadastroFuncionarioService;
	
	
	@RequestMapping("/novo")
	public ModelAndView novo(Funcionario funcionario) {
		return new ModelAndView("funcionario/CadastroFuncionario");
	}
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Funcionario funcionario, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(funcionario);
		}
		
		cadastroFuncionarioService.salvar(funcionario);
		attributes.addFlashAttribute("mensagem", "Funcionario salvo com sucesso!");
		return new ModelAndView("redirect:/funcionarios/novo");
	}
	
	//pesquisa
	
	
	@Secured("ROLE_EXCLUIR_FUNCIONARIO")
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Funcionario funcionario) {
		try {
			cadastroFuncionarioService.excluir(funcionario);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	
	
	@Secured("ROLE_ALTERAR_FUNCIONARIO")
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Funcionario funcionario) {
		ModelAndView mv = novo(funcionario);
		mv.addObject(funcionario);
		return mv;
		
	}
	
	
}



