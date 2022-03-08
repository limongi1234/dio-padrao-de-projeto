package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.model.Produto;

public class ProdutoInserirDTO {
   
	private Long id, codigo;
	private String nome, descricao;
	private float valor_unitario;
	private Categoria categoria;
	
	public ProdutoInserirDTO() {
		super();
	}
	
	public ProdutoInserirDTO(Produto produto) {
		super();
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.codigo = produto.getCodigo();
		this.valor_unitario = produto.getValor_unitario();
		this.categoria = produto.getCategoria();
	}

	public Categoria getCategoria() {
		return categoria;
	}

   public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

    public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public float getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(float valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
