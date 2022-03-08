package br.org.serratec.backend.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table (name = "produto")
public class Produto {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_produto")
	private Long id;
	
	@Column(name = "codigo_produto")
	private Long codigo;
	
	@Column (name = "descricao", length = 100, nullable = false)
	private String descricao;
	
    @Column
	private float valor_unitario;

	@Column (name = "data_cadastro")
	private LocalDate data_Cadastro;
	
	@Column (name = "nome", length = 30, nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	private String uri;
	
	private int quantidade_estoque;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId (Long id) 
	{
		this.id = id;
	}

	public String getDescricao() 
	{
		return descricao;
	}

	public LocalDate getDataCadastro() 
	{
		return data_Cadastro;
	}

	public void setDataCadastro (LocalDate dataCadastro) 
	{
		this.data_Cadastro = dataCadastro;
	}

	public float getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(float valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public LocalDate getData_Cadastro() {
		return data_Cadastro;
	}

	public void setData_Cadastro(LocalDate data_Cadastro) {
		this.data_Cadastro = data_Cadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getQuantidade_estoque() {
		return quantidade_estoque;
	}

	public void setQuantidade_estoque(int quantidade_estoque) {
		this.quantidade_estoque = quantidade_estoque;
	}

	public Produto(Long id, Long codigo, String descricao, float valor_unitario, LocalDate data_Cadastro, String nome,
			Categoria categoria, String uri, int quantidade_estoque) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor_unitario = valor_unitario;
		this.data_Cadastro = data_Cadastro;
		this.nome = nome;
		this.categoria = categoria;
		this.uri = uri;
		this.quantidade_estoque = quantidade_estoque;
	}

	public Produto() {
		super();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Produto other = (Produto) obj;
		if (codigo == null) 
			if (other.codigo != null)
				return false;
		else if (!codigo.equals(other.codigo))
			return false;
		if (id == null) 
			if (other.id != null)
				return false;
		 else if (!id.equals(other.id))
			return false;
		return true;
	}
}