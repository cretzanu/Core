package eu.cec.digit.apps.meta.processes.crud;

import java.util.List;

import com.business.apps.core.data.repo.Repository;

import eu.cec.digit.apps.meta.generics.messages.crud.ReadByNamedQueryMsg;
import eu.cec.digit.apps.meta.generics.processes.GenericMessageHandler;

public class ReadByNamedQueryHandler<R> implements GenericMessageHandler<ReadByNamedQueryMsg, List<R>>{
	Repository repository;

	
	
	public ReadByNamedQueryHandler(Repository repository) {
		super();
		this.repository = repository;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	@Override
	public List<R> handleMessage(ReadByNamedQueryMsg message) {
		
		return this.repository.readDataByNamedQuery(message.getQueryName(), message.getParams(),Object.class);
		
	}

	
	

}
