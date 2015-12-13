package gl8080.lifegame.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import gl8080.lifegame.logic.definition.GameDefinition;
import gl8080.lifegame.logic.definition.GameDefinitionRepository;
import gl8080.lifegame.logic.exception.NotFoundEntityException;

@ApplicationScoped
public class JpaGameDefinitionRepository implements GameDefinitionRepository {
    
    @PersistenceContext(unitName="LifeGameUnit")
    private EntityManager em;

    @Override
    public void register(GameDefinition gameDefinition) {
        this.em.persist(gameDefinition);
    }

    @Override
    public GameDefinition search(long id) {
        GameDefinition gameDefinition = this.em.find(GameDefinition.class, id);
        
        if (gameDefinition == null) {
            throw new NotFoundEntityException(id);
        }
        
        return gameDefinition;
    }

    @Override
    public GameDefinition searchWithLock(long id) {
        GameDefinition gameDefinition = this.em.find(GameDefinition.class, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        
        if (gameDefinition == null) {
            throw new NotFoundEntityException(id);
        }
        
        return gameDefinition;
    }

    @Override
    public void remove(GameDefinition gameDefinition) {
        this.em.remove(gameDefinition);
    }
}
