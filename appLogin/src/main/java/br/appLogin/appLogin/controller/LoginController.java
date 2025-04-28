package br.appLogin.appLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping ("/")
	public String paginaAcademia() {
		return "index";
	}
	
	@PostMapping("/logar")
	public String loginUsuario(Usuario usuario, Model model, HttpServletResponse response) {
	    // Verifica se é o administrador
		
		Usuario usuarioLogado = usuarioRepository.login(usuario.getEmail(), usuario.getSenha());
		
		if(usuarioLogado.getNome().equals("Administrador")) {
			return "paginaAdministrador";
		}
		
		if(usuarioLogado != null) {
			return "paginaUsuario";
		}
		
		model.addAttribute("erro", "Usuário inválido!");
	    return "login";
	}


	
	@GetMapping("/cadastro")
	public String cadastro() {
		return "cadastro";
	}
	
	@RequestMapping(value="/cadastro", method = RequestMethod.POST)
	public String cadastroUsuario(@Valid Usuario usuario, BindingResult result) {
		
		if(result.hasErrors()) {
			return "redirect:/cadastro";
		}
		
		usuarioRepository.save(usuario);
		
		return "redirect:/login";
	}

}
