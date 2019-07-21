package br.com.ricardo.tron.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.ricardo.tron.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import br.com.ricardo.tron.thymeleaf.processor.MenuAttributeTagProcessor;
import br.com.ricardo.tron.thymeleaf.processor.MessageElementTagProcessor;
import br.com.ricardo.tron.thymeleaf.processor.OrderElementTagProcessor;
import br.com.ricardo.tron.thymeleaf.processor.PaginationElementTagProcessor;

public class TronDialect extends AbstractProcessorDialect {

	public TronDialect() {
		super("Ricardo Tron", "tron", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}