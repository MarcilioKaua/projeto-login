package br.ifpe.web2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String exibirRegistro(Contato contato, Model model) {
		return "registro";
	}
	
	@GetMapping("/index")
	public String exibirIndex() {
		return "index";
	}
	
	@GetMapping("/esqueceu-senha")
	public String exibirEsqueceuSenha() {
		return "esqueceu-senha";
	}
	
	@GetMapping("/novaSenha")
	public String exibirNovaSenha(Contato contato, Model model) {
		model.addAttribute("lista", this.contatoDAO.findAll());
		return "nova-senha";
	}
	
	@PostMapping("/recuperandoSenha")
	public String recuperandoSenha(String email, Model model) {
		model.addAttribute("contato", this.contatoDAO.findByEmail(email));
		return "redirect:/novaSenha";
	}
	
	@PostMapping("/registrando")
	public String registrar(Contato contato, Model model) {
		this.contatoDAO.save(contato);
		return "redirect:/login";
	}
	
	@GetMapping("/editarContato")
	public String editarContato(String email, Model model) {
		model.addAttribute("contato", this.contatoDAO.findById(email));
		return "registro";
	}
	
	@GetMapping("/removerContato")
	public String removerContato(String email) {
		contatoDAO.deleteById(email);
		return "redirect:/novaSenha";
	}
	
	@PostMapping("/logando")
	public String logando(Contato contato, HttpSession session) {
		contato = this.contatoDAO.findByEmailAndSenha(contato.getEmail(), contato.getSenha());
		if(contato != null);
			//Guardar sessão o objeto usuario
			
			session.setAttribute("usuarioLogado", contato);
			return "redirect:/index";
		} 
	
	
	@PostMapping("/sair")
	public String sair(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
