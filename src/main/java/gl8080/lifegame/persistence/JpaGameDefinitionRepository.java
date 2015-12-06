package gl8080.lifegame.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gl8080.lifegame.logic.GameDefinition;
import gl8080.lifegame.logic.GameDefinitionRepository;

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
        return this.em.find(GameDefinition.class, id);
    }
}
