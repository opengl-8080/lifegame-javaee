package gl8080.lifegame.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gl8080.lifegame.logic.Game;
import gl8080.lifegame.logic.GameRepository;

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
        return this.em.find(Game.class, id);
    }
}