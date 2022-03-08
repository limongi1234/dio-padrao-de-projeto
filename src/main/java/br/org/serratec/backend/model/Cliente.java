package br.org.serratec.backend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long id;
	
	@Email(message = "email inválido!")
	@Size (max = 30, message = "O tamanho do campo email não pode ser superior a 30 caracteres!")
	@Column(length = 30, nullable = false)
	private String email;
	
	@NotBlank(message = "O nome não pode ser nulo!")
	@Size (max = 20, message = "O tamanho do campo nome_usuario não pode ser superior a 20 caracteres!")
	@Column(length = 20, nullable = false)
	private String nome_usuario;
	
	@NotBlank(message = "O nome não pode ser nulo!")
	@Size (max = 60, message = "O tamanho do campo nome_completo não pode ser superior a 60 caracteres!")
	@Column(length = 60, nullable = false)
	private String nome_completo;
	
	@NotBlank(message = "A senha não pode ser nula!")
	@Size (max = 255, message = "O tamanho do campo senha não pode ser superior a 255 caracteres!")
	@Column(length = 255, nullable = false)
	private String senha;
	
	@CPF(message = "CPF inválido!")
	@Size (max = 14, message = "O campo CPF deve conter 14 caracteres!")
	@Column(length = 14, nullable = false)
	private String cpf;
	
	@NotBlank(message = "O número não pode ser nulo!")
	@Column
	private Integer numero;
	
	@Size (max = 20, message = "O campo complemento não pode ser superior a 20 caracteres!")
	@Column
	private String complemento;
	
	@Size (max = 11, message = "Informe o número de telefone com  DDD!")
	@Column(length = 11, nullable = false)
	private String telefone;
	
	@Future(message = "Data Inválida!")
	@Column(name = "data_nasc")
	private LocalDate dataNascimento;
	
	@ManyToOne
	@JoinColumn(name = "cep")
	private Endereco endereco;
	
	public Cliente() {
		super();
	}

	public Cliente(Long id,	String email, String nome_usuario, String nome_completo, String senha, String cpf,	
			Integer numero, String complemento,	String telefone, LocalDate dataNascimento, Endereco endereco) {
		super();
		this.id = id;
		this.email = email;
		this.nome_usuario = nome_usuario;
		this.nome_completo = nome_completo;
		this.senha = senha;
		this.cpf = cpf;
		this.numero = numero;
		this.complemento = complemento;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
	}

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	public String getNome_completo() {
		return nome_completo;
	}

	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}	
	
	@Override
	public String toString() {
		return "Cliente [email = " + email + ", nome_usuario = " + nome_usuario + ", senha = " + senha + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) 
			if (other.id != null)
				return false;
		else if (!id.equals(other.id))
			return false;
		return true;
	}
}
