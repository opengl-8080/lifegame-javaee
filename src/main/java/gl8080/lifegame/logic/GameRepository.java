package gl8080.lifegame.logic;

public interface GameRepository {

    void register(Game game);
    Game search(long id);
}
