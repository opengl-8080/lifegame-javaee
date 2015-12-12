package gl8080.lifegame.logic.definition;

import java.util.Optional;

/**
 * ゲーム定義の永続化を行うリポジトリ。
 */
public interface GameDefinitionRepository {
    
    /**
     * ゲーム定義を登録する。
     * @param gameDefinition 登録するゲーム定義
     */
    void register(GameDefinition gameDefinition);
    
    /**
     * 指定した ID のゲーム定義を検索する。
     * @param id ID
     * @return ゲーム定義
     */
    Optional<GameDefinition> search(long id);
    
    /**
     * 指定したゲーム定義を削除する。
     * @param gameDefinition 削除するゲーム定義
     */
    void remove(GameDefinition gameDefinition);
}
