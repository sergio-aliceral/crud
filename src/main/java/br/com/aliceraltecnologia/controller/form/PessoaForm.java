package br.com.aliceraltecnologia.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.aliceraltecnologia.model.Pessoa;

public class PessoaForm {

	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pessoa converte() {
		return new Pessoa(this.nome);
	}

}
