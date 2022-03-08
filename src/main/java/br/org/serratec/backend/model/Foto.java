package br.org.serratec.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Foto 
{
    @Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_foto")
	private Long id;

	private byte[] dados;
	private String tipo, nome;

	@OneToOne
	@JoinColumn (name = "id_cliente")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn (name = "id_produto")
	private Produto produto;

	public Foto() 
	{
		super();
	}

	public Long getId() 
	{
		return id;
	}

	public void setId (Long id) 
	{
		this.id = id;
	}

	public byte[] getDados() 
	{
		return dados;
	}

	public void setDados (byte[] dados) 
	{
		this.dados = dados;
	}

	public String getTipo() 
	{
		return tipo;
	}

	public void setTipo (String tipo)
	{
		this.tipo = tipo;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	public Cliente getCliente() 
	{
		return cliente;
	}

	public void setFuncionario (Cliente cliente)
	{
		this.cliente = cliente;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Foto other = (Foto) obj;
		if (id == null) 
			if (other.id != null)
				return false;
		 else if (!id.equals(other.id))
			return false;
		return true;
	}
}

