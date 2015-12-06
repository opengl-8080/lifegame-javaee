package gl8080.lifegame.logic;

import java.util.Optional;

public interface GameRepository {

    void register(Game game);
    Optional<Game> search(long id);
}
