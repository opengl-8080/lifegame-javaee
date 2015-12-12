package gl8080.lifegame.logic;

import java.util.Map;

public interface LifeGame {
    
    /**
     * このゲームの ID を取得します。
     * @return ID
     */
    Long getId();
    
    /**
     * このゲームのサイズを取得します。
     * @return サイズ
     */
    int getSize();
    
    /**
     * このゲームが持つ全てのセルを取得します。
     * <p>
     * このメソッドが返すマップは、このオブジェクトが持つマップのコピーです。<br>
     * ここで取得したマップのエントリを削除するなどしても、このオブジェクトが持つオリジナルのマップには影響を与えません。
     * 
     * @return 全てのセル
     */
    Map<Position, ? extends LifeGameCell> getCells();
}
