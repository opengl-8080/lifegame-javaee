package gl8080.lifegame.logic;

public interface GameDefinitionRepository {
    
    void register(GameDefinition gameDefinition);
    GameDefinition search(long id);
}
