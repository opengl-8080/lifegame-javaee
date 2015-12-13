package gl8080.lifegame.application.definition;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import gl8080.lifegame.logic.definition.GameDefinition;
import gl8080.lifegame.logic.definition.GameDefinitionRepository;

@Stateless
public class RegisterGameDefinitionService {
    @Inject
    private transient Logger logger;
    
    @Inject
    private GameDefinitionRepository gameDefinitionRepository;
    
    public GameDefinition register(int size) {
        logger.info("register game definition (size={})", size);
        GameDefinition gameDefinition = new GameDefinition(size);
        this.gameDefinitionRepository.register(gameDefinition);
        return gameDefinition;
    }
}
