package br.ifpe.web2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.ifpe.web2.model.Contato;
import br.ifpe.web2.model.ContatoDAO;

@Controller
public class ProjetoLoginController {
	
	private List<Contato> contatos = new ArrayList<>();
	@Autowired
	private ContatoDAO contatoDAO;
	
	@GetMapping("/login")
	public String exibirLogin() {
		return "login";
	}
	
	@GetMapping("/registro")
	public String exibirRegistro(Contato contato) {
		return "registro";
	}
	
	@GetMapping("/index")
	public String exibirIndex() {
		return "index";
	}
	
	@PostMapping("/registrando")
	public String registrar(Contato contato) {
		this.contatoDAO.save(contato);
		return "redirect:/login";
	}
	
	@PostMapping("/logando")
	public String logando(Contato contato, HttpSession session) {
		if(contato.getEmail().equals("admin") && contato.getSenha().equals("1234")) {
			contato.setNome("Administrador");
			//Guardar sessão o objeto usuario
			contato.setNome("Administrador");
			session.setAttribute("usuarioLogado", contato);
			return "redirect:/index";
		} else {
			return "redirect:/login";
		}
	}
	
	@PostMapping("/sair")
	public String sair(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
