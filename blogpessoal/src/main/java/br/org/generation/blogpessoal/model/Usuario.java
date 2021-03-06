package br.org.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "Nome é obrigatório !!!")
	private String nome;

	@ApiModelProperty(example = "email@email.com.br")
	@NotNull(message = "Usuário é obrigatório !!!")
	@Email(message = "Usuário deve ter um email válido !!!")
	private String usuario;

	/**
	 * A anotação @Size está definida apenas com o valor min porque ao criptografar
	 * a senha a mesma terá uma tamanho muito maior (em numero de caracteres) do que
	 * a senha não ciptografada.
	 * 
	 * Exemplo: admin123 -> 8 caracteres admin123 criptografado -> 60 caracteres
	 * 
	 * A anotação @NotBlank indica que o atributo não deve ser nulo e/ou conter
	 * espaços em branco.
	 */
	@NotBlank(message = "Senha é obrigatório !!!")
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres !!!")
	private String senha;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;

	// Primeiro método Construtor
	public Usuario(long id, String nome, String usuario, String senha) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}

	// Segundo método Contrutor
	public Usuario() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

}
