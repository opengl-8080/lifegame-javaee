package gl8080.lifegame.application.definition;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import gl8080.lifegame.logic.definition.GameDefinition;
import gl8080.lifegame.logic.definition.GameDefinitionRepository;

@Stateless
public class RemoveGameDefinitionService {
    @Inject
    private transient Logger logger;
    
    @Inject
    private GameDefinitionRepository gameDefinitionRepository;
    
    public void remove(long id) {
        logger.info("remove game definition (id={})", id);
        
        GameDefinition gameDefinition = this.gameDefinitionRepository.search(id);
        
        this.gameDefinitionRepository.remove(gameDefinition);
    }
}
