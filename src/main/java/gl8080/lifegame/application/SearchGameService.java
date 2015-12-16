package gl8080.lifegame.application;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import gl8080.lifegame.logic.Game;
import gl8080.lifegame.logic.GameRepository;

@Stateless
public class SearchGameService {
    @Inject
    private transient Logger logger;
    @Inject
    private GameRepository repository;
    
    public Game search(long id) {
        logger.info("search game (id={})", id);
        
        Game game = this.repository.search(id);
        
        return game;
    }
}
