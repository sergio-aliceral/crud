package br.com.aliceraltecnologia.controller.dto;

import org.springframework.data.domain.Page;

import br.com.aliceraltecnologia.model.Pessoa;

public class PessoaDto {

	private Long id;

	private String nome;

	public PessoaDto(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public static Page<PessoaDto> parse(Page<Pessoa> pessoas) {
		return pessoas.map(PessoaDto::new);
	}

}
