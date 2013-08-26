package eu.cec.digit.apps.meta.processes.crud;

import com.business.apps.core.data.repo.Repository;
import com.business.apps.core.data.search.criteria.SearchCriteria;

import eu.cec.digit.apps.meta.messages.AppMessage;
import eu.cec.digit.apps.meta.messages.crud.request.Create;
import eu.cec.digit.apps.meta.messages.crud.request.Delete;
import eu.cec.digit.apps.meta.messages.crud.request.ReadAll;
import eu.cec.digit.apps.meta.messages.crud.request.ReadById;
import eu.cec.digit.apps.meta.messages.crud.request.Save;
import eu.cec.digit.apps.meta.messages.crud.request.Update;
import eu.cec.digit.apps.meta.processes.DefaultMessageHandler;

public class DefaultPersistenceMessageHandler extends DefaultMessageHandler{
	private Repository repository;

	
	public DefaultPersistenceMessageHandler(Repository repository) {
		super();
		this.repository = repository;
	}

	public DefaultPersistenceMessageHandler() {
		
	}


	@SuppressWarnings("unchecked")
	@Override
	protected Object process(AppMessage message) {
		if (message instanceof Save)
			if (((Save) message).getTargetEntity().getId()==null)
				return this.getRepository().create(((Save) message).getTargetEntity());
			else
				return this.getRepository().update(((Save) message).getTargetEntity());
		else if (message instanceof Update)
				return this.getRepository().update(((Update) message).getTargetEntity());
		else if (message instanceof Create)
			return this.getRepository().create(((Create) message).getTargetEntity());	
		else if(message instanceof Delete)
			this.getRepository().delete(((Delete) message).getTargetEntity());
		else if(message instanceof ReadAll)
			return this.getRepository().findObjectsAll(((ReadAll)message).getEntityClass());
		else if(message instanceof ReadById)
			return this.getRepository().findObjectById(((ReadById)message).getEntityClass(),((ReadById)message).getId() );
		else if(message instanceof SearchCriteria)
			return this.getRepository().search(((SearchCriteria)message));
		else
			throw new IllegalArgumentException("This message "+message.getClass() +" is not for me..."+this.getClass());
		return null;
	}
	
	
	
	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}
}
