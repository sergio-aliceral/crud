package br.com.aliceraltecnologia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aliceraltecnologia.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	public Page<Pessoa> findByNome(String nome, Pageable pageable);

}
