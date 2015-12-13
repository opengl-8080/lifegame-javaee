package gl8080.lifegame.application;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import gl8080.lifegame.logic.Game;
import gl8080.lifegame.logic.GameRepository;
import gl8080.lifegame.web.resource.LifeGameDto;

@Stateless
public class NextStepService {
    @Inject
    private transient Logger logger;
    @Inject
    private GameRepository repository;
    
    public LifeGameDto next(long id) {
        logger.info("next step game (id={})", id);
        
        Game game = this.repository.searchWithLock(id);
        game.nextStep();

        return LifeGameDto.of(game);
    }
}
