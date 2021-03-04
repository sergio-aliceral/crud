package br.com.aliceraltecnologia.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.aliceraltecnologia.controller.dto.PessoaDto;
import br.com.aliceraltecnologia.controller.form.PessoaForm;
import br.com.aliceraltecnologia.model.Pessoa;
import br.com.aliceraltecnologia.repository.PessoaRepository;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository repository;

	@GetMapping
	public Page<PessoaDto> lista(@RequestParam(required = false) String nome, @PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
		Page<Pessoa> pessoas = null;

		if (nome == null)
			pessoas = repository.findAll(pageable);

		if (nome != null)
			pessoas = repository.findByNome(nome, pageable);

		return PessoaDto.parse(pessoas);
	}

	@PostMapping
	public ResponseEntity<PessoaDto> salva(@RequestBody @Valid PessoaForm form, UriComponentsBuilder uriComponentsBuilder) {
		Pessoa pessoa = form.converte();
		repository.save(pessoa);

		URI uri = uriComponentsBuilder.path("/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(new PessoaDto(pessoa));
	}

	@GetMapping("{id}")
	public PessoaDto busca(@PathVariable Long id) {
		return repository
				.findById(id)
				.map(PessoaDto::new)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PutMapping("{id}")
	public PessoaDto atualiza(@PathVariable Long id, @RequestBody @Valid PessoaForm form) {

		return repository.findById(id).map(pessoa -> {
			pessoa.setNome(form.getNome());
			repository.save(pessoa);
			return new PessoaDto(pessoa);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void exclui(@PathVariable Long id) {
		Pessoa pessoa = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		repository.delete(pessoa);
	}

}
