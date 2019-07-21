package br.com.ricardo.tron.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank(message= "Codigo Obrigatório")
	private String codigo;
	
	@NotBlank(message = "Nome é Obrigatório")
	@Size(min = 1, max = 50, message = "O nome deve ter entre 1 e 50 Caracteres")
	private String nome;

	@NotBlank(message = "Descrição é Obrigatória")
	private String descricao;
	
	
	@NotNull(message = "Lucro é Obrigatório")
	private BigDecimal lucro;
	
	@NotNull(message = "Preço é Obrigatório")
	@DecimalMin(value = "0.50", message = "O valor do produto deve ser maior que R$0,50")
	@DecimalMax(value = "9999999.99", message = "O valor do produto deve ser menor que R$9.999.999,99")
	private BigDecimal preco;
	
	@Column(name = "preco_venda")
	private BigDecimal precoVenda;
	
	
	@Column(name = "qnt_estoque")	
	private Integer qntEstoque;
	
	@NotNull(message = "O Fornecedor é obrigatório")
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;
	
	//Metodo para calcular o preço de venda do produto
	public BigDecimal valor() {
		BigDecimal in = new BigDecimal(100);
		BigDecimal out = new BigDecimal(0);

		out = this.preco.multiply(lucro);
		out = out.divide(in);
		out = out.add(preco);
		
		return out;
	}
	
	@PrePersist
	@PreUpdate	
	private void prePersistUpdate() {
		codigo = codigo.toUpperCase();
	}
	
	public boolean isNovo() {
		
		return id == null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getLucro() {
		return lucro;
	}

	public void setLucro(BigDecimal lucro) {
		this.lucro = lucro;
	}

	

	public Integer getQntEstoque() {
		return qntEstoque;
	}

	public void setQntEstoque(Integer qntEstoque) {
		this.qntEstoque = qntEstoque;
	}
	

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lucro == null) ? 0 : lucro.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((precoVenda == null) ? 0 : precoVenda.hashCode());
		result = prime * result + ((qntEstoque == null) ? 0 : qntEstoque.hashCode());
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
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lucro == null) {
			if (other.lucro != null)
				return false;
		} else if (!lucro.equals(other.lucro))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (precoVenda == null) {
			if (other.precoVenda != null)
				return false;
		} else if (!precoVenda.equals(other.precoVenda))
			return false;
		if (qntEstoque == null) {
			if (other.qntEstoque != null)
				return false;
		} else if (!qntEstoque.equals(other.qntEstoque))
			return false;
		return true;
	}

	
	
	
}
