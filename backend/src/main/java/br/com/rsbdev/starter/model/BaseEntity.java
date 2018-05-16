package br.com.rsbdev.starter.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe base para todas as entidades, definindo id do tipo {@link Long} que
 * utiliza sequencia própria, por tabela, ao invés de compartilhar o
 * {@code hibernate_sequence}.
 * <p>
 * Cada subclasse deve adicionar uma anotação na classe, informando o nome da
 * sequencia da tabela em questão. Ex.
 * 
 * <pre>
 * &#64;Entity
 * &#64;SequenceGenerator(name="primary_key", sequenceName="minha_tabela_id_seq", allocationSize=1)
 * public class MinhaTabela extends BaseEntity {...}
 * </pre>
 * 
 */
@MappedSuperclass
public abstract class BaseEntity implements Persistable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_key")
	private Long id;

	/**
	 * Retorna o id da entidade.
	 * <p>
	 * Caso a entidade seja nova, retorna {@code null}.
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * Informa manualmente o id da entidade.
	 * <p>
	 * User o método com moderação. Quem deverá definir o id é o sistema de
	 * banco de dados.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retorna {@code true} caso a entidade seja nova e não esteja persistida no
	 * Banco.
	 */
	@Override
	@Transient
	@JsonIgnore
	public boolean isNew() {
		return null == getId();
	}

	@Override
	public String toString() {
		return String.format("Entidade %s[id=%s]", this.getClass()
				.getSimpleName(), getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj || null == this.getId()) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (!getClass().equals(obj.getClass())) {
			return false;
		}

		BaseEntity that = (BaseEntity) obj;
		return this.getId()
				.equals(that.getId());
	}

	@Override
	public int hashCode() {
		int hashCode = 17;

		hashCode += null == getId() ? 0 : getId().hashCode() * 31;

		return hashCode;
	}
}
