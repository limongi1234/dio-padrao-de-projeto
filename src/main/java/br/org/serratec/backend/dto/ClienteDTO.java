package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Cliente;

public class ClienteDTO {
	
	private Long id;
	private String nome_usuario, email;
	
	public ClienteDTO() {
		super();
	}


	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome_usuario = cliente.getNome_usuario();
		this.email = cliente.getEmail();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome_usuario() {
		return nome_usuario;
	}


	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
}
