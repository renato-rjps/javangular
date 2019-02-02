package br.com.rsbdev.starter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "primary_key", sequenceName = "pessoa_id_seq", allocationSize = 1)
public class Pessoa extends BaseEntity {

	@Column
	@NotNull
	@Size(min = 4, max = 255)
	private String name;

}
