package gl8080.lifegame.application;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import gl8080.lifegame.logic.GameDefinitionRepository;

@Stateless
public class RemoveGameDefinitionService {
    @Inject
    private Logger logger;
    
    @Inject
    private GameDefinitionRepository gameDefinitionRepository;
    
    public void remove(long id) {
        logger.info("remove game definition : {}", id);
        
        this.gameDefinitionRepository
            .search(id)
            .ifPresent(this.gameDefinitionRepository::remove);
    }
}
