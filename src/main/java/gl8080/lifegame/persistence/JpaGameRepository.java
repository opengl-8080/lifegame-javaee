package gl8080.lifegame.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import gl8080.lifegame.logic.Game;
import gl8080.lifegame.logic.GameRepository;
import gl8080.lifegame.logic.exception.NotFoundEntityException;

@ApplicationScoped
public class JpaGameRepository implements GameRepository {
    
    @PersistenceContext(unitName="LifeGameUnit")
    private EntityManager em;
    
    @Override
    public void register(Game game) {
        this.em.persist(game);
    }

    @Override
    public Game search(long id) {
        return this.searchWithLock(id, LockModeType.NONE);
    }

    @Override
    public Game searchWithLock(long id) {
        return this.searchWithLock(id, LockModeType.PESSIMISTIC_WRITE);
    }
    
    public Game searchWithLock(long id, LockModeType type) {
        Game game = this.em.find(Game.class, id, type);
        
        if (game == null) {
            throw new NotFoundEntityException(id);
        }
        
        return game;
    }

    @Override
    public void remove(Game game) {
        this.em.remove(game);
    }
}
