package gl8080.lifegame.application;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import gl8080.lifegame.logic.Game;
import gl8080.lifegame.logic.GameRepository;

@Stateless
public class RemoveGameService {
    @Inject
    private Logger logger;
    @Inject
    private GameRepository gameRepository;
    
    public void remove(long id) {
        logger.info("remove game (id={})", id);
        
        Game game = this.gameRepository.search(id).orElseThrow(RuntimeException::new);
        
        this.gameRepository.remove(game);
    }
}
