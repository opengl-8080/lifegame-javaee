package gl8080.lifegame.logic;

import java.util.Optional;

/**
 * ゲームの永続化を行うリポジトリ。
 */
public interface GameRepository {

    /**
     * ゲームを登録する。
     * @param game 登録するゲーム
     */
    void register(Game game);
    
    /**
     * 指定した ID のゲームを検索する。
     * @param id ID
     * @return 検索結果
     */
    Optional<Game> search(long id);
}
