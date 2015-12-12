package gl8080.lifegame.logic.definition;

import java.util.Optional;

public interface GameDefinitionRepository {
    
    void register(GameDefinition gameDefinition);
    Optional<GameDefinition> search(long id);
    void remove(GameDefinition gameDefinition);
}
