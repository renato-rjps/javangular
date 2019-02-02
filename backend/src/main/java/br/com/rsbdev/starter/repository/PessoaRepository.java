package br.com.rsbdev.starter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rsbdev.starter.model.Pessoa;

/**
 * Repository to manipulate Pessoa data.
 * 
 * @author Renato
 *
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	/**
	 * Find Pessoa by name
	 * 
	 * @param name
	 * 
	 * @return an {@link Pessoa}
	 */
	Optional<Pessoa> findByName(String name);
}