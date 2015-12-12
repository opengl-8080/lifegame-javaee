package gl8080.lifegame.application;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import gl8080.lifegame.logic.GameDefinition;
import gl8080.lifegame.logic.GameDefinitionRepository;

@Stateless
public class RegisterGameDefinitionService {
    @Inject
    private Logger logger;
    
    @Inject
    private GameDefinitionRepository gameDefinitionRepository;
    
    public GameDefinition register(int size) {
        logger.info("register game definition : {}", size);
        GameDefinition gameDefinition = new GameDefinition(size);
        this.gameDefinitionRepository.register(gameDefinition);
        return gameDefinition;
    }
}
