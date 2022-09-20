package br.ifpe.web2.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoDAO extends JpaRepository<Contato, String> {
	
	public Contato findByEmailAndSenha(String email, String senha);
	
	public Contato findByEmail(String email);
}


