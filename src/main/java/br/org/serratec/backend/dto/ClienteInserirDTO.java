package br.org.serratec.backend.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;

public class ClienteInserirDTO {
	
	
	@Email(message = "email inválido!")
	@Size (max = 30, message = "O tamanho do campo email não pode ser superior a 30 caracteres!")
	private String email;
	
	@NotBlank(message = "O nome não pode ser nulo!")
	@Size (max = 20, message = "O tamanho do campo nome_usuario não pode ser superior a 20 caracteres!")
	private String nome_usuario;
	@NotBlank(message = "A senha não pode ser nula!")
	@Size (max = 255, message = "O tamanho do campo senha não pode ser superior a 255 caracteres!")
	private String senha;
	
	@Future(message = "Data Inválida!")
	private LocalDate dataNascimento;
	
	@NotNull
	private Endereco endereco;
	
	public ClienteInserirDTO(Cliente cliente) {
		super();
		this.nome_usuario = cliente.getNome_usuario();
		this.email = cliente.getEmail();
		this.senha = cliente.getSenha();
		this.dataNascimento = cliente.getDataNascimento();
		this.endereco = cliente.getEndereco();
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}




}
