package br.com.rsbdev.starter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstração de Rest Controller que define a inclusão do log como uma
 * propriedade default da classe.
 * 
 * @author Renato
 *
 */
public abstract class AbstractRestController {

	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

}
