package gl8080.lifegame.application;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import gl8080.lifegame.logic.Game;
import gl8080.lifegame.logic.GameRepository;
import gl8080.lifegame.logic.definition.GameDefinition;
import gl8080.lifegame.logic.definition.GameDefinitionRepository;

@Stateless
public class RegisterGameService {
    @Inject
    private transient Logger logger;
    
    @Inject
    private GameDefinitionRepository gameDefinitionRepository;
    @Inject
    private GameRepository gameRepository;
    
    public Game register(long gameDefinitionId) {
        logger.info("save game (gameDefinitionId={})", gameDefinitionId);
        
        GameDefinition gameDefinition = this.gameDefinitionRepository.search(gameDefinitionId);
        
        Game game = new Game(gameDefinition);
        this.gameRepository.register(game);
        
        return game;
    }
}
