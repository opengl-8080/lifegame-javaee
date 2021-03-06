package gl8080.lifegame.application.definition;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import gl8080.lifegame.logic.definition.GameDefinition;
import gl8080.lifegame.logic.definition.GameDefinitionRepository;

@Stateless
public class SearchGameDefinitionService {
    @Inject
    private transient Logger logger;
    @Inject
    private GameDefinitionRepository repository;
    
    public GameDefinition search(long id) {
        logger.debug("search game definition (id={})", id);
        
        GameDefinition gameDefinition = this.repository.search(id);
        
        return gameDefinition;
    }
}
