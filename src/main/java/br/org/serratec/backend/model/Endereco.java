package br.org.serratec.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Long id;
	
	@NotBlank(message = "O cep não pode ser nulo!")
	@Size (max = 9, message = "O tamanho do campo cep não pode ser superior a 9 caracteres!")
	@Column(length = 9, nullable = false)
	private String cep;
	
	@NotBlank(message = "A rua não pode ser nula!")
	@Size (max = 100, message = "O tamanho do campo rua não pode ser superior a 100 caracteres!")
	@Column(name = "rua", length = 100, nullable = false)
	private String logradouro;
	
	
	@NotBlank(message = "O bairro não pode ser nulo!")
	@Size (max = 50, message = "O tamanho do campo bairro não pode ser superior a 50 caracteres!")
	@Column(length = 50, nullable = false)
	private String bairro;
	
	@Size (max = 30, message = "O tamanho do campo cidade não pode ser superior a 30 caracteres!")
	@Column(name = "cidade", length = 30)
	private String localidade;
	
	@Size (max = 2, message = "O tamanho do campo estado não pode ser superior a 2 caracteres!")
	@Column(name = "estado", length = 2)
	private String uf;
	
	private Long ibge;
	
	@JsonIgnore
	@OneToMany(mappedBy = "endereco")
	private List <Cliente> cliente;
	
	
	
	public Endereco() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Long getIbge() {
		return ibge;
	}
	public void setIbge(Long ibge) {
		this.ibge = ibge;
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
		Endereco other = (Endereco) obj;
		if (id == null) 
			if (other.id != null)
				return false;
		else if (!id.equals(other.id))
			return false;
		return true;
	}
}
